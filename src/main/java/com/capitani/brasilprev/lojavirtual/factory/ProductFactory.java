package com.capitani.brasilprev.lojavirtual.factory;

import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.User;

import java.math.BigDecimal;

public class ProductFactory {
    public static Product initialize() {
        return new Product();
    }

    public static Product initialize(String name, BigDecimal value) {
        Product model = initialize();
        model.setName(name);
        model.setValue(value);
        return model;
    }

    public static Product initialize(String name, BigDecimal value, User userCreated, User userUpdated) {
        Product model = initialize();
        model.setName(name);
        model.setValue(value);
        model.setUserCreated(userCreated);
        model.setUserUpdated(userUpdated);
        return model;
    }


}
