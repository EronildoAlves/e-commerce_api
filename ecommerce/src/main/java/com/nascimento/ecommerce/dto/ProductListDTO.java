package com.nascimento.ecommerce.dto;

import com.nascimento.ecommerce.model.Product;

import java.math.BigDecimal;

public record ProductListDTO(

        Long id,

        String nome,

        String description,

        BigDecimal price) {

    public ProductListDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
