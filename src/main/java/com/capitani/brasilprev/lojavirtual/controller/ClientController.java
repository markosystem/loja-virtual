package com.capitani.brasilprev.lojavirtual.controller;

import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.representation.ClientFormDto;
import com.capitani.brasilprev.lojavirtual.model.representation.ClientViewDto;
import com.capitani.brasilprev.lojavirtual.service.ClientService;
import com.capitani.brasilprev.lojavirtual.util.Pagination;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("v1/clientes")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping()
    @Cacheable(value = "clients_cache")
    @ApiOperation(value = "Lista todos os clintes registrados.")
    public Page<ClientViewDto> all(@RequestParam(required = false) String document,
                                   @RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size,
                                   @RequestParam(required = false, defaultValue = "ASC") String order,
                                   @RequestParam(required = false, defaultValue = "name") String sort) {
        Pageable pageable = Pagination.get(page, size, order, sort);
        Page<Client> data = (document == null || document.equals("") ? service.getAll(pageable) : service.findByDocument(document, pageable));
        return ClientViewDto.converter(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtém um cliente a partir do seu identificador numérico.")
    public ResponseEntity<ClientViewDto> single(@PathVariable("id") Long id) {
        Optional<Client> clientOptional = service.findById(id);
        return clientOptional.map(client -> ResponseEntity.ok(new ClientViewDto(client))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @CacheEvict(value = "clients_cache", allEntries = true)
    @ApiOperation(value = "Cria um novo cliente.")
    public ResponseEntity<ClientViewDto> create(@RequestBody @Valid ClientFormDto form, UriComponentsBuilder uriBuilder) {
        Client client = form.converter();
        service.save(client);
        URI uri = uriBuilder.path("v1/clientes/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientViewDto(client));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "clients_cache", allEntries = true)
    @ApiOperation(value = "Atualiza um cliente existente, informando o identificador e o objeto 'body' na requisição.")
    public ResponseEntity<ClientViewDto> update(@PathVariable("id") Long id, @RequestBody @Valid ClientFormDto form) {
        Optional<Client> clientOptional = service.findById(id);
        if (clientOptional.isPresent()) {
            Client client = form.update(clientOptional.get());
            return ResponseEntity.ok(new ClientViewDto(client));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "clients_cache", allEntries = true)
    @ApiOperation(value = "Exclui um cliente existente com o seu identificador numérico.")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        Optional<Client> clientOptional = service.findById(id);
        if (clientOptional.isPresent()) {
            service.delete(clientOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
