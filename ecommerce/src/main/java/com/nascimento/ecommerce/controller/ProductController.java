package com.nascimento.ecommerce.controller;

import com.nascimento.ecommerce.dto.ProductCreateDTO;
import com.nascimento.ecommerce.dto.ProductResponseDTO;
import com.nascimento.ecommerce.dto.ProductListDTO;
import com.nascimento.ecommerce.dto.UpdateProductDTO;
import com.nascimento.ecommerce.model.Product;
import com.nascimento.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponseDTO> insert(@Valid @RequestBody ProductCreateDTO dto, UriComponentsBuilder UriBuilder) {
        var product = new Product(dto);
        repository.save(product);
        var uri = UriBuilder.path("/products{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductResponseDTO(product));
    }

    @GetMapping
    public ResponseEntity<Page<ProductListDTO>> findAll(@PageableDefault(size = 10, sort = {"name"})Pageable pageable) {
        var page = repository.findAll(pageable).map(ProductListDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProductResponseDTO> update(@RequestBody @Valid UpdateProductDTO dto) {
        var product = repository.getReferenceById(dto.id());
        product.updateInformation(dto);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.noContent().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
