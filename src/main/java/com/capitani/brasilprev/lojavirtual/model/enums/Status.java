package com.capitani.brasilprev.lojavirtual.model.enums;

public enum Status {
    OPEN("Aberto"), CANCELED("Canelado"), COMPLETED("Completado");
    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
