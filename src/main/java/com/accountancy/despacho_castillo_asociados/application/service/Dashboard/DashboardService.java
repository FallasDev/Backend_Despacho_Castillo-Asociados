package com.accountancy.despacho_castillo_asociados.application.service.Dashboard;

import com.accountancy.despacho_castillo_asociados.domain.model.Dashboard.DashboardStats;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Client.JPAClientRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie.JPAFormalitieRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Service.JPAServiceRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.User.JPAUserRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final JPAFormalitieRepository formalitieRepository;
    private final JPAClientRepository clientRepository;
    private final JPAServiceRepository serviceRepository;
    private final JPAUserRepository userRepository;

    public DashboardService(JPAFormalitieRepository formalitieRepository,
                            JPAClientRepository clientRepository,
                            JPAServiceRepository serviceRepository,
                            JPAUserRepository userRepository) {
        this.formalitieRepository = formalitieRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public DashboardStats getStats() {
        DashboardStats stats = new DashboardStats();

        // 1. Card metrics
        stats.setCards(buildCardMetrics());

        // 2. Formalities by state
        stats.setFormalitiesByState(buildFormalitiesByState());

        // 3. Formalities by month (last 6 months)
        stats.setFormalitiesByMonth(buildFormalitiesByMonth());

        // 4. Formalities by service (top 6)
        stats.setFormalitiesByService(buildFormalitiesByService());

        // 5. Recent formalities (last 10)
        stats.setRecentFormalities(buildRecentFormalities());

        // 6. Recent clients (last 5)
        stats.setRecentClients(buildRecentClients());

        // 7. Unattended formalities (PENDING with no user assigned)
        stats.setUnattendedFormalities(buildUnattendedFormalities());

        return stats;
    }

    private DashboardStats.CardMetrics buildCardMetrics() {
        long activeClients = clientRepository.countByIsActiveTrue();
        long totalFormalities = formalitieRepository.countByActiveTrue();
        long pendingFormalities = formalitieRepository.countByStateAndActiveTrue(FormalitiesState.PENDING.getId())
                + formalitieRepository.countByStateAndActiveTrue(FormalitiesState.IN_PROGRESS.getId());
        long completedFormalities = formalitieRepository.countByStateAndActiveTrue(FormalitiesState.COMPLETED.getId());
        long activeServices = serviceRepository.countByActiveTrue();
        long activeUsers = userRepository.countByIsActiveTrue();

        return new DashboardStats.CardMetrics(
                activeClients, totalFormalities, pendingFormalities,
                completedFormalities, activeServices, activeUsers
        );
    }

    private List<DashboardStats.StateCount> buildFormalitiesByState() {
        List<DashboardStats.StateCount> result = new ArrayList<>();
        for (FormalitiesState state : FormalitiesState.values()) {
            long count = formalitieRepository.countByStateAndActiveTrue(state.getId());
            result.add(new DashboardStats.StateCount(state.name(), count));
        }
        return result;
    }

    private List<DashboardStats.MonthCount> buildFormalitiesByMonth() {
        LocalDateTime sixMonthsAgo = LocalDate.now().minusMonths(5).withDayOfMonth(1).atStartOfDay();
        List<Object[]> raw = formalitieRepository.countByMonthSince(sixMonthsAgo);

        // Build a map of year-month -> count
        Map<String, Long> countMap = new LinkedHashMap<>();
        for (Object[] row : raw) {
            int year = ((Number) row[0]).intValue();
            int month = ((Number) row[1]).intValue();
            long count = ((Number) row[2]).longValue();
            countMap.put(year + "-" + month, count);
        }

        // Ensure all 6 months are present (fill gaps with 0)
        List<DashboardStats.MonthCount> result = new ArrayList<>();
        LocalDate now = LocalDate.now();
        String[] spanishMonths = {"", "Ene", "Feb", "Mar", "Abr", "May", "Jun",
                "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        for (int i = 5; i >= 0; i--) {
            LocalDate date = now.minusMonths(i);
            int year = date.getYear();
            int month = date.getMonthValue();
            String key = year + "-" + month;
            long count = countMap.getOrDefault(key, 0L);
            String label = spanishMonths[month] + " " + year;
            result.add(new DashboardStats.MonthCount(year, month, label, count));
        }

        return result;
    }

    private List<DashboardStats.ServiceCount> buildFormalitiesByService() {
        List<Object[]> raw = formalitieRepository.countByService();
        return raw.stream()
                .limit(6)
                .map(row -> new DashboardStats.ServiceCount(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }

    private List<DashboardStats.RecentFormality> buildRecentFormalities() {
        List<FormalitieEntity> recent = formalitieRepository.findTop10ByActiveTrueOrderByCreatedAtDesc();
        return recent.stream().map(e -> {
            String clientName = e.getClient() != null
                    ? e.getClient().getName() + " " + e.getClient().getSurname()
                    : "Sin cliente";
            String serviceName = e.getService() != null
                    ? e.getService().getName()
                    : "Sin servicio";
            String userName = e.getUser() != null
                    ? e.getUser().getName() + " " + e.getUser().getSurname()
                    : "Sin asignar";
            String stateName = FormalitiesState.fromId(e.getState()).name();

            return new DashboardStats.RecentFormality(
                    e.getId(), clientName, serviceName, stateName,
                    e.getCreatedAt().toString(), userName
            );
        }).collect(Collectors.toList());
    }

    private List<DashboardStats.RecentClient> buildRecentClients() {
        List<ClientEntity> recent = clientRepository.findTop5ByIsActiveTrueOrderByCreatedAtDesc();
        return recent.stream().map(c -> new DashboardStats.RecentClient(
                c.getId(), c.getName(), c.getSurname(),
                c.getEmail(), c.getCreatedAt().toString()
        )).collect(Collectors.toList());
    }

    private List<DashboardStats.UnattendedFormality> buildUnattendedFormalities() {
        List<FormalitieEntity> unattended = formalitieRepository
                .findByUserIsNullAndActiveTrueAndStateInOrderByCreatedAtDesc(
                        List.of(FormalitiesState.PENDING.getId(), FormalitiesState.IN_PROGRESS.getId())
                );
        return unattended.stream()
                .limit(10)
                .map(e -> {
                    String clientName = e.getClient() != null
                            ? e.getClient().getName() + " " + e.getClient().getSurname()
                            : "Sin cliente";
                    String serviceName = e.getService() != null
                            ? e.getService().getName()
                            : "Sin servicio";
                    String stateName = FormalitiesState.fromId(e.getState()).name();

                    return new DashboardStats.UnattendedFormality(
                            e.getId(), clientName, serviceName,
                            stateName, e.getCreatedAt().toString()
                    );
                }).collect(Collectors.toList());
    }
}
