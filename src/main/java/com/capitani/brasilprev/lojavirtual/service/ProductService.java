package com.capitani.brasilprev.lojavirtual.service;

import com.capitani.brasilprev.lojavirtual.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> getAll(Pageable page);

    List<Product> getAll();

    Page<Product> findByName(String name, Pageable page);

    Optional<Product> findById(long id);

    Product save(Product product);

    void delete(Product product);
}