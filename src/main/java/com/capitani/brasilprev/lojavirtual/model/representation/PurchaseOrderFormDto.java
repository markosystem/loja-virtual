package com.capitani.brasilprev.lojavirtual.model.representation;

import com.capitani.brasilprev.lojavirtual.model.enums.Status;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PurchaseOrderFormDto {

    private Status status;

    @NotNull(message = "O campo 'Cliente' deverar ser informado!")
    @ManyToOne
    private Long idClient;

    private List<Long> productList;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public List<Long> getProductList() {
        return productList;
    }

    public void setProductList(List<Long> productList) {
        this.productList = productList;
    }
}
