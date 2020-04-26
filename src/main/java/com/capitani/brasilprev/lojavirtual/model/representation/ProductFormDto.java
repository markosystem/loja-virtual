package com.capitani.brasilprev.lojavirtual.model.representation;

import com.capitani.brasilprev.lojavirtual.factory.ProductFactory;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductFormDto {
    @NotBlank(message = "O campo 'Nome' deverar ser informado!")
    @Length(min = 3, max = 100, message = "O campo 'Nome' deverá conter entre 3 e 100 caracteres!")
    private String name;

    @NotNull(message = "O campo 'Valor' é obrigatório!")
    @Column(scale = 2, precision = 11)
    private BigDecimal value;

    public Product converter() {
        Product productForm = ProductFactory.initialize(this.name, this.value);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            productForm.setUserCreated((User) auth.getPrincipal());
        return productForm;
    }

    public Product update(Product productFormUp) {
        productFormUp.setName(this.name);
        productFormUp.setValue(this.value);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            productFormUp.setUserUpdated((User) auth.getPrincipal());
        return productFormUp;
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
}
