package com.capitani.brasilprev.lojavirtual.controller;

import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.representation.ProductFormDto;
import com.capitani.brasilprev.lojavirtual.model.representation.ProductViewDto;
import com.capitani.brasilprev.lojavirtual.service.ProductService;
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
import java.util.function.Function;

@RestController
@RequestMapping("v1/produtos")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping()
    @Cacheable(value = "products_cache")
    @ApiOperation(value = "Lista todos os produtos registrados.")
    public Page<ProductViewDto> all(@RequestParam(required = false) String search,
                                    @RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false, defaultValue = "ASC") String order,
                                    @RequestParam(required = false, defaultValue = "name") String sort) {
        Pageable pageable = Pagination.get(page, size, order, sort);
        Page<Product> data = (search == null || search.equals("") ? service.getAll(pageable) : service.findByName(search, pageable));
        return ProductViewDto.converter(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtém um produto a partir do seu identificador numérico.")
    public ResponseEntity<ProductViewDto> single(@PathVariable("id") Long id) {
        Optional<Product> productOptional = service.findById(id);
        return null;//productOptional.map(product -> ResponseEntity.ok(new ProductViewDto(product))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @CacheEvict(value = "products_cache", allEntries = true)
    @ApiOperation(value = "Cria um novo produto.")
    public ResponseEntity<ProductViewDto> create(@RequestBody @Valid ProductFormDto form, UriComponentsBuilder uriBuilder) {
        Product product = form.converter();
        service.save(product);
        URI uri = uriBuilder.path("v1/produtos/{id}").buildAndExpand(product.getId()).toUri();
        return null;//ResponseEntity.created(uri).body(new ProductViewDto(product));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "products_cache", allEntries = true)
    @ApiOperation(value = "Atualiza um produto existente, informando o identificador e o objeto 'body' na requisição.")
    public ResponseEntity<ProductViewDto> update(@PathVariable("id") Long id, @RequestBody @Valid ProductFormDto form) {
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent()) {

            Product product = form.update(productOptional.get());
            return null;//ResponseEntity.ok(new ProductViewDto(product));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "products_cache", allEntries = true)
    @ApiOperation(value = "Exclui um produto existente com o seu identificador numérico.")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            service.delete(productOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
