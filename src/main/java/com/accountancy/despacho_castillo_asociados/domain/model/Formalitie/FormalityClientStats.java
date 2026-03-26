package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;

public class FormalityClientStats {

    private Long total;
    private Long pending;
    private Long completed;
    private Client client;

    public FormalityClientStats(Long total, Long pending, Long completed, Client client) {
        this.total = total;
        this.pending = pending;
        this.completed = completed;
        this.client = client;
    }

    public Long getTotal() {
        return total;
    }

    public Long getPending() {
        return pending;
    }

    public Long getCompleted() {
        return completed;
    }

    public Client getClient() {
        return client;
    }
}
