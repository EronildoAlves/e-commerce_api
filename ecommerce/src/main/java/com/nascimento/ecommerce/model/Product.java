package com.nascimento.ecommerce.model;

import com.nascimento.ecommerce.dto.ProductCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    public Product(ProductCreateDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
    }
}


