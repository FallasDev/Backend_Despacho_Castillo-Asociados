package com.accountancy.despacho_castillo_asociados.domain.model.Dashboard;

import java.util.List;

public class DashboardStats {

    private CardMetrics cards;
    private List<StateCount> formalitiesByState;
    private List<MonthCount> formalitiesByMonth;
    private List<ServiceCount> formalitiesByService;
    private List<RecentFormality> recentFormalities;
    private List<RecentClient> recentClients;
    private List<UnattendedFormality> unattendedFormalities;

    public DashboardStats() {}

    public DashboardStats(CardMetrics cards,
                          List<StateCount> formalitiesByState,
                          List<MonthCount> formalitiesByMonth,
                          List<ServiceCount> formalitiesByService,
                          List<RecentFormality> recentFormalities,
                          List<RecentClient> recentClients,
                          List<UnattendedFormality> unattendedFormalities) {
        this.cards = cards;
        this.formalitiesByState = formalitiesByState;
        this.formalitiesByMonth = formalitiesByMonth;
        this.formalitiesByService = formalitiesByService;
        this.recentFormalities = recentFormalities;
        this.recentClients = recentClients;
        this.unattendedFormalities = unattendedFormalities;
    }

    // Getters & Setters
    public CardMetrics getCards() { return cards; }
    public void setCards(CardMetrics cards) { this.cards = cards; }

    public List<StateCount> getFormalitiesByState() { return formalitiesByState; }
    public void setFormalitiesByState(List<StateCount> formalitiesByState) { this.formalitiesByState = formalitiesByState; }

    public List<MonthCount> getFormalitiesByMonth() { return formalitiesByMonth; }
    public void setFormalitiesByMonth(List<MonthCount> formalitiesByMonth) { this.formalitiesByMonth = formalitiesByMonth; }

    public List<ServiceCount> getFormalitiesByService() { return formalitiesByService; }
    public void setFormalitiesByService(List<ServiceCount> formalitiesByService) { this.formalitiesByService = formalitiesByService; }

    public List<RecentFormality> getRecentFormalities() { return recentFormalities; }
    public void setRecentFormalities(List<RecentFormality> recentFormalities) { this.recentFormalities = recentFormalities; }

    public List<RecentClient> getRecentClients() { return recentClients; }
    public void setRecentClients(List<RecentClient> recentClients) { this.recentClients = recentClients; }

    public List<UnattendedFormality> getUnattendedFormalities() { return unattendedFormalities; }
    public void setUnattendedFormalities(List<UnattendedFormality> unattendedFormalities) { this.unattendedFormalities = unattendedFormalities; }

    // --- Inner DTOs ---

    public static class CardMetrics {
        private long activeClients;
        private long totalFormalities;
        private long pendingFormalities;
        private long completedFormalities;
        private long activeServices;
        private long activeUsers;

        public CardMetrics() {}

        public CardMetrics(long activeClients, long totalFormalities, long pendingFormalities,
                           long completedFormalities, long activeServices, long activeUsers) {
            this.activeClients = activeClients;
            this.totalFormalities = totalFormalities;
            this.pendingFormalities = pendingFormalities;
            this.completedFormalities = completedFormalities;
            this.activeServices = activeServices;
            this.activeUsers = activeUsers;
        }

        public long getActiveClients() { return activeClients; }
        public void setActiveClients(long activeClients) { this.activeClients = activeClients; }
        public long getTotalFormalities() { return totalFormalities; }
        public void setTotalFormalities(long totalFormalities) { this.totalFormalities = totalFormalities; }
        public long getPendingFormalities() { return pendingFormalities; }
        public void setPendingFormalities(long pendingFormalities) { this.pendingFormalities = pendingFormalities; }
        public long getCompletedFormalities() { return completedFormalities; }
        public void setCompletedFormalities(long completedFormalities) { this.completedFormalities = completedFormalities; }
        public long getActiveServices() { return activeServices; }
        public void setActiveServices(long activeServices) { this.activeServices = activeServices; }
        public long getActiveUsers() { return activeUsers; }
        public void setActiveUsers(long activeUsers) { this.activeUsers = activeUsers; }
    }

    public static class StateCount {
        private String state;
        private long count;

        public StateCount() {}
        public StateCount(String state, long count) {
            this.state = state;
            this.count = count;
        }

        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public long getCount() { return count; }
        public void setCount(long count) { this.count = count; }
    }

    public static class MonthCount {
        private int year;
        private int month;
        private String label;
        private long count;

        public MonthCount() {}
        public MonthCount(int year, int month, String label, long count) {
            this.year = year;
            this.month = month;
            this.label = label;
            this.count = count;
        }

        public int getYear() { return year; }
        public void setYear(int year) { this.year = year; }
        public int getMonth() { return month; }
        public void setMonth(int month) { this.month = month; }
        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
        public long getCount() { return count; }
        public void setCount(long count) { this.count = count; }
    }

    public static class ServiceCount {
        private String serviceName;
        private long count;

        public ServiceCount() {}
        public ServiceCount(String serviceName, long count) {
            this.serviceName = serviceName;
            this.count = count;
        }

        public String getServiceName() { return serviceName; }
        public void setServiceName(String serviceName) { this.serviceName = serviceName; }
        public long getCount() { return count; }
        public void setCount(long count) { this.count = count; }
    }

    public static class RecentFormality {
        private int id;
        private String clientName;
        private String serviceName;
        private String state;
        private String applicationDate;
        private String userName;

        public RecentFormality() {}
        public RecentFormality(int id, String clientName, String serviceName,
                               String state, String applicationDate, String userName) {
            this.id = id;
            this.clientName = clientName;
            this.serviceName = serviceName;
            this.state = state;
            this.applicationDate = applicationDate;
            this.userName = userName;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getClientName() { return clientName; }
        public void setClientName(String clientName) { this.clientName = clientName; }
        public String getServiceName() { return serviceName; }
        public void setServiceName(String serviceName) { this.serviceName = serviceName; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getApplicationDate() { return applicationDate; }
        public void setApplicationDate(String applicationDate) { this.applicationDate = applicationDate; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
    }

    public static class RecentClient {
        private int id;
        private String name;
        private String surname;
        private String email;
        private String createdAt;

        public RecentClient() {}
        public RecentClient(int id, String name, String surname, String email, String createdAt) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.createdAt = createdAt;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSurname() { return surname; }
        public void setSurname(String surname) { this.surname = surname; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    }

    public static class UnattendedFormality {
        private int id;
        private String clientName;
        private String serviceName;
        private String state;
        private String applicationDate;

        public UnattendedFormality() {}
        public UnattendedFormality(int id, String clientName, String serviceName,
                                   String state, String applicationDate) {
            this.id = id;
            this.clientName = clientName;
            this.serviceName = serviceName;
            this.state = state;
            this.applicationDate = applicationDate;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getClientName() { return clientName; }
        public void setClientName(String clientName) { this.clientName = clientName; }
        public String getServiceName() { return serviceName; }
        public void setServiceName(String serviceName) { this.serviceName = serviceName; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getApplicationDate() { return applicationDate; }
        public void setApplicationDate(String applicationDate) { this.applicationDate = applicationDate; }
    }
}
