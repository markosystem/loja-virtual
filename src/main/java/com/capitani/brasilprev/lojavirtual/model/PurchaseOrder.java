package com.capitani.brasilprev.lojavirtual.model;

import com.capitani.brasilprev.lojavirtual.model.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class PurchaseOrder extends Abstract {
    private Integer orderNumber;
    private Status status;
    @Column(scale = 2, precision = 11)
    private BigDecimal amount;
    @ManyToOne
    private Client client;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> productList;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
