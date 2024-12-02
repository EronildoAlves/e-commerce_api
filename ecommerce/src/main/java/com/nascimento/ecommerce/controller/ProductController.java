package com.nascimento.ecommerce.controller;

import com.nascimento.ecommerce.dto.ProductCreateDTO;
import com.nascimento.ecommerce.dto.ProductResponseDTO;
import com.nascimento.ecommerce.model.Product;
import com.nascimento.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

}
