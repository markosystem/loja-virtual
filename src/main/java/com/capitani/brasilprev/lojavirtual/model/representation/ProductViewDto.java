package com.capitani.brasilprev.lojavirtual.model.representation;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.User;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

public class ProductViewDto {
    private Long id;

    private String name;

    private BigDecimal value;

    private Date created;

    private Date updated;

    private User userCreated;

    private User userUpdate;

    public ProductViewDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.value = product.getValue();
        this.created = product.getCreated();
        this.updated = product.getUpdated();
        this.userCreated = product.getUserCreated();
        this.userUpdate = product.getUserUpdated();
    }

    public static Page<ProductViewDto> converter(Page<Product> productPage) {
        return productPage.map(ProductViewDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
