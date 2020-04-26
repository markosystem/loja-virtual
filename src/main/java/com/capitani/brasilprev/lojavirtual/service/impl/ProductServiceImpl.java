package com.capitani.brasilprev.lojavirtual.service.impl;

import com.capitani.brasilprev.lojavirtual.controller.UserController;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.repository.ProductRepository;
import com.capitani.brasilprev.lojavirtual.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserController userController;

    @Override
    public Page<Product> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<Product> getAll() {
        Iterable<Product> listIterable = repository.findAll();
        List<Product> list = new ArrayList<>();
        listIterable.forEach(list::add);
        return list;
    }

    @Override
    public Page<Product> findByName(String name, Pageable page) {
        return repository.findByName(name, page);
    }

    @Override
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }
}
