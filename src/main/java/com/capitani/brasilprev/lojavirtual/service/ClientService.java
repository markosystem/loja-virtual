package com.capitani.brasilprev.lojavirtual.service;

import com.capitani.brasilprev.lojavirtual.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {
    Page<Client> getAll(Pageable page);

    Page<Client> findByDocument(String document, Pageable page);

    Optional<Client> findById(long id);

    Client save(Client product);

    void delete(Client product);
}