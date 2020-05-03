package com.capitani.brasilprev.lojavirtual.factory;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.model.enums.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseOrderFactory {
    public static PurchaseOrder initialize() {
        return new PurchaseOrder();
    }

    public static PurchaseOrder initialize(Status status, Client client, List<Product> productList, Integer orderNumber) {
        User userAuth = null;
        PurchaseOrder model = initialize();
        model.setOrderNumber(orderNumber);
        model.setStatus(status == null ? Status.OPEN : status);
        model.setAmount(productList == null || productList.size() == 0 ? new BigDecimal(0) : productList.stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add));
        model.setClient(client);
        model.setProductList(productList);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            userAuth = (User) auth.getPrincipal();
            if (model.getId() == null)
                model.setUserCreated(userAuth);
            else
                model.setUserUpdated(userAuth);
        }
        return model;
    }

}
