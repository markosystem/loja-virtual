package com.capitani.brasilprev.lojavirtual.service;

import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PurchaseOrderService {
    Page<PurchaseOrder> getAll(Pageable page);

    Page<PurchaseOrder> findByOrderNumber(String orderNumber, Pageable page);

    Optional<PurchaseOrder> findById(long id);

    PurchaseOrder save(PurchaseOrder purchaseOrder);

    PurchaseOrder canceled(PurchaseOrder purchaseOrder);

    Integer findOrderNumberByClient(Long idClient);
}