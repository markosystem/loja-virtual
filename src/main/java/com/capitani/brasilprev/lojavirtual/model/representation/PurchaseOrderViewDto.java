package com.capitani.brasilprev.lojavirtual.model.representation;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.model.enums.Status;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PurchaseOrderViewDto {
    private Long id;

    private Integer orderNumber;

    private Status status;

    @Column(scale = 2, precision = 11)
    private BigDecimal amount;

    @ManyToOne
    private ClientViewDto client;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductViewDto> productList;

    private Date created;

    private Date updated;

    private User userCreated;

    private User userUpdate;

    public PurchaseOrderViewDto(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.orderNumber = purchaseOrder.getOrderNumber();
        this.status = purchaseOrder.getStatus();
        this.amount = purchaseOrder.getAmount();
        this.client = new ClientViewDto(purchaseOrder.getClient());
        this.productList = purchaseOrder.getProductList().stream().map(ProductViewDto::new).collect(Collectors.toList());
        this.created = purchaseOrder.getCreated();
        this.updated = purchaseOrder.getUpdated();
        this.userCreated = purchaseOrder.getUserCreated();
        this.userUpdate = purchaseOrder.getUserUpdated();
    }

    public static Page<PurchaseOrderViewDto> converter(Page<PurchaseOrder> purchaseOrderPage) {
        return purchaseOrderPage.map(PurchaseOrderViewDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ClientViewDto getClient() {
        return client;
    }

    public void setClient(ClientViewDto client) {
        this.client = client;
    }

    public List<ProductViewDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductViewDto> productList) {
        this.productList = productList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public User getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(User userUpdate) {
        this.userUpdate = userUpdate;
    }
}
