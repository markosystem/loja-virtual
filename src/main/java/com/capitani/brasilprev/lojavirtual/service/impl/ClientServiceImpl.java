package com.capitani.brasilprev.lojavirtual.service.impl;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.repository.ClientRepository;
import com.capitani.brasilprev.lojavirtual.repository.ProductRepository;
import com.capitani.brasilprev.lojavirtual.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;

    @Override
    public Page<Client> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Page<Client> findByDocument(String name, Pageable page) {
        return repository.findByDocument(name, page);
    }

    @Override
    public Optional<Client> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(Client client) {
        repository.delete(client);
    }
}
