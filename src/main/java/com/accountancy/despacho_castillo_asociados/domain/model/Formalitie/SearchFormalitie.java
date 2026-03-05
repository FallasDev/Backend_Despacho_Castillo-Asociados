package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import java.time.LocalDateTime;
import java.util.Date;

public class SearchFormalitie {

    private String serviceName;
    private String clientName;
    private String userName;
    private Integer stateId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SearchFormalitie(String serviceName, String clientName, String userName, Integer stateId, LocalDateTime startDate, LocalDateTime endDate) {
        this.serviceName = serviceName;
        this.clientName = clientName;
        this.userName = userName;
        this.stateId = stateId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SearchFormalitie() {
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SearchFormalitie{" +
                "serviceName='" + serviceName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", userName='" + userName + '\'' +
                ", stateId=" + stateId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
