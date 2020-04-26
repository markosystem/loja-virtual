package com.capitani.brasilprev.lojavirtual.factory;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.User;

public class ClientFactory {
    public static Client initialize() {
        return new Client();
    }

    public static Client initialize(String name, String email, String document) {
        Client model = initialize();
        model.setName(name);
        model.setEmail(email);
        model.setDocument(document);
        return model;
    }

    public static Client initialize(String name, String email, String document, User userCreated, User userUpdated) {
        Client model = initialize();
        model.setName(name);
        model.setEmail(email);
        model.setDocument(document);
        model.setUserCreated(userCreated);
        model.setUserUpdated(userUpdated);
        return model;
    }


}
