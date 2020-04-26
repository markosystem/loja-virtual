package com.capitani.brasilprev.lojavirtual.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Entity
public class Role extends Abstract implements GrantedAuthority {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
