package com.capitani.brasilprev.lojavirtual.factory;

import com.capitani.brasilprev.lojavirtual.model.User;

public class UserFactory {
    public static User initialize() {
        return new User();
    }

    public static User initialize(String name, String login, String password, String email) {
        User model = initialize();
        model.setName(name);
        model.setLogin(login);
        model.setPassword(password);
        model.setEmail(email);
        return model;
    }

}
