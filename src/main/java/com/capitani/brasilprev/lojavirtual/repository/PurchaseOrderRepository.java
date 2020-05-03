package com.capitani.brasilprev.lojavirtual.repository;

import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Long> {
    Page<PurchaseOrder> findByOrderNumber(String orderNumber, Pageable pageable);

    @Query(value = "SELECT p.ORDER_NUMBER FROM PURCHASE_ORDER p WHERE p.client_id = ?1", nativeQuery = true)
    Integer findOrderNumberByClient(Long idClient);
}
