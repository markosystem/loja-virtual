package com.capitani.brasilprev.lojavirtual.repository;

import com.capitani.brasilprev.lojavirtual.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findByName(String name, Pageable pageable);
}
