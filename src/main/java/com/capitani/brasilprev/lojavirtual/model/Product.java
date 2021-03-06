package com.capitani.brasilprev.lojavirtual.model;

import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@ApiIgnore
public class Product extends Abstract {
    private String name;
    @Column(scale = 2, precision = 11)
    private BigDecimal value;

    public Product() {
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", userCreated=" + userCreated +
                ", created=" + created +
                ", userUpdated=" + userUpdated +
                ", updated=" + updated +
                '}';
    }
}
