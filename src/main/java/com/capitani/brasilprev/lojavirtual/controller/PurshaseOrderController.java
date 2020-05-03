package com.capitani.brasilprev.lojavirtual.controller;

import com.capitani.brasilprev.lojavirtual.factory.PurchaseOrderFactory;
import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import com.capitani.brasilprev.lojavirtual.model.representation.PurchaseOrderFormDto;
import com.capitani.brasilprev.lojavirtual.model.representation.PurchaseOrderViewDto;
import com.capitani.brasilprev.lojavirtual.service.ClientService;
import com.capitani.brasilprev.lojavirtual.service.ProductService;
import com.capitani.brasilprev.lojavirtual.service.PurchaseOrderService;
import com.capitani.brasilprev.lojavirtual.util.MessageResponse;
import com.capitani.brasilprev.lojavirtual.util.Pagination;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/pedidos")
public class PurshaseOrderController {
    @Autowired
    private PurchaseOrderService service;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @GetMapping()
    @Cacheable(value = "orders_cache_all")
    @ApiOperation(value = "Lista todos os Pedidos registrados pelo o Usuário.")
    @Transactional
    public Page<PurchaseOrderViewDto> all(@RequestParam(required = false) String orderNumber,
                                          @RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "10") int size,
                                          @RequestParam(required = false, defaultValue = "ASC") String order,
                                          @RequestParam(required = false, defaultValue = "orderNumber") String sort) {
        Pageable pageable = Pagination.get(page, size, order, sort);
        Page<PurchaseOrder> data = (orderNumber == null || orderNumber.equals("") ? service.getAll(pageable) : service.findByOrderNumber(orderNumber, pageable));
        return PurchaseOrderViewDto.converter(data);
    }

    @PostMapping
    @Transactional
    @ApiOperation(value = "Cria um novo Pedido.")
    public ResponseEntity<PurchaseOrderViewDto> create(@RequestBody @Valid PurchaseOrderFormDto form, UriComponentsBuilder uriBuilder) {
        Optional<Client> clientOptional = clientService.findById(form.getIdClient());
        if (!clientOptional.isPresent()) {
            return new ResponseEntity(Collections.singleton(new MessageResponse(HttpStatus.NOT_FOUND, "Client not found")), HttpStatus.NOT_FOUND);
        }
        List<Product> productList = productService.findByIds(form.getProductList());
        PurchaseOrder purchaseOrder = PurchaseOrderFactory.initialize(form.getStatus(), clientOptional.get(), productList, service.findOrderNumberByClient(clientOptional.get().getId()));
        service.save(purchaseOrder);
        URI uri = uriBuilder.path("v1/pedidos/{id}").buildAndExpand(purchaseOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(new PurchaseOrderViewDto(purchaseOrder));
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Atualizar um pedido existente com o seu identificador numérico e os dados.")
    public ResponseEntity<PurchaseOrderViewDto> update(@PathVariable("id") Long id, @RequestBody @Valid PurchaseOrderFormDto form, UriComponentsBuilder uriBuilder) {
        Optional<PurchaseOrder> purchaseOrderOptional = service.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            service.canceled(purchaseOrderOptional.get());
            return purchaseOrderOptional.map(purchaseOrder -> ResponseEntity.ok(new PurchaseOrderViewDto(purchaseOrder))).orElseGet(() -> ResponseEntity.badRequest().build());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtém um pedido a partir do seu identificador numérico.")
    @Transactional
    public ResponseEntity<PurchaseOrderViewDto> single(@PathVariable("id") Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = service.findById(id);
        return purchaseOrderOptional.map(purchaseOrder -> ResponseEntity.ok(new PurchaseOrderViewDto(purchaseOrder))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Cancela um pedido existente com o seu identificador numérico.")
    public ResponseEntity<PurchaseOrderViewDto> cancel(@PathVariable("id") Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = service.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            service.canceled(purchaseOrderOptional.get());
            return purchaseOrderOptional.map(purchaseOrder -> ResponseEntity.ok(new PurchaseOrderViewDto(purchaseOrder))).orElseGet(() -> ResponseEntity.badRequest().build());
        }
        return ResponseEntity.notFound().build();
    }
}
