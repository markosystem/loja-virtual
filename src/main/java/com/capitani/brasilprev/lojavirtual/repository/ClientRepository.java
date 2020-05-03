package com.capitani.brasilprev.lojavirtual.repository;

import com.capitani.brasilprev.lojavirtual.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Page<Client> findByDocument(String document, Pageable pageable);
}
