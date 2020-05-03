package com.capitani.brasilprev.lojavirtual.model.representation;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.User;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;

public class ClientViewDto {
    private Long id;

    private String name;

    private String email;

    private String document;

    private Date created;

    private Date updated;

    private User userCreated;

    private User userUpdate;

    public ClientViewDto() {
    }

    public ClientViewDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.document = client.getDocument();
        this.created = client.getCreated();
        this.updated = client.getUpdated();
        this.userCreated = client.getUserCreated();
        this.userUpdate = client.getUserUpdated();
    }

    public static Page<ClientViewDto> converter(Page<Client> clients) {
        return clients.map(ClientViewDto::new);
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
