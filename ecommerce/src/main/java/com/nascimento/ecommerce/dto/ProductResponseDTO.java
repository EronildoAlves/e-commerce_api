package com.nascimento.ecommerce.dto;


import com.nascimento.ecommerce.model.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(

        String name,

        String description,

        BigDecimal price) {

    public ProductResponseDTO(Product product) {
        this(product.getName(), product.getDescription(), product.getPrice());
    }
}
