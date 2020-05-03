package com.capitani.brasilprev.lojavirtual.model.representation;

import com.capitani.brasilprev.lojavirtual.factory.ClientFactory;
import com.capitani.brasilprev.lojavirtual.factory.ProductFactory;
import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ClientFormDto {
    @NotBlank(message = "O campo 'Nome' deverar ser informado!")
    @Length(min = 3, max = 100, message = "O campo 'Nome' deverá conter entre 3 e 100 caracteres!")
    private String name;

    @NotBlank(message = "O campo 'Email' deverar ser informado!")
    @Length(min = 3, max = 100, message = "O campo 'Email' deverá conter entre 3 e 100 caracteres!")
    private String email;

    @Length(max = 30, message = "O campo 'Documento' deverá conter no máximo 30 caracteres!")
    private String document;

    public Client converter() {
        Client clientForm = ClientFactory.initialize(this.name, this.email, this.document);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            clientForm.setUserCreated((User) auth.getPrincipal());
        return clientForm;
    }

    public Client update(Client clientFormUp) {
        clientFormUp.setName(this.name);
        clientFormUp.setEmail(this.email);
        clientFormUp.setDocument(this.document);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            clientFormUp.setUserUpdated((User) auth.getPrincipal());
        return clientFormUp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
