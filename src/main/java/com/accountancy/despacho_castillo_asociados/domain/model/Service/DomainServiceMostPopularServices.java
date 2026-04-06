package com.accountancy.despacho_castillo_asociados.domain.model.Service;

public class DomainServiceMostPopularServices {

    private DomainService service;
    private int timesUsed;

    public DomainServiceMostPopularServices() {
    }

    public DomainServiceMostPopularServices(DomainService service, int timesUsed) {
        this.service = service;
        this.timesUsed = timesUsed;
    }

    public DomainService getService() {
        return service;
    }

    public void setService(DomainService service) {
        this.service = service;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

}
