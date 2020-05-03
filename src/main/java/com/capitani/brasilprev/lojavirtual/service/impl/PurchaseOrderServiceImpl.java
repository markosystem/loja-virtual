package com.capitani.brasilprev.lojavirtual.service.impl;

import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.model.enums.Status;
import com.capitani.brasilprev.lojavirtual.repository.PurchaseOrderRepository;
import com.capitani.brasilprev.lojavirtual.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository repository;

    @Override
    public Page<PurchaseOrder> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Page<PurchaseOrder> findByOrderNumber(String orderNumber, Pageable page) {
        return repository.findByOrderNumber(orderNumber, page);
    }

    @Override
    public Optional<PurchaseOrder> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return repository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder canceled(PurchaseOrder purchaseOrder) {
        purchaseOrder.setStatus(Status.CANCELED);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            purchaseOrder.setUserUpdated((User) auth.getPrincipal());
        return repository.save(purchaseOrder);
    }

    @Override
    public Integer findOrderNumberByClient(Long idClient) {
        Integer orderNumber = repository.findOrderNumberByClient(idClient);
        return orderNumber == null ? Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).concat("1")) : orderNumber + 1;
    }
}
