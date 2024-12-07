package com.nascimento.ecommerce.model;

import com.nascimento.ecommerce.dto.ProductCreateDTO;
import com.nascimento.ecommerce.dto.UpdateProductDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    public void updateInformation(@Valid UpdateProductDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
        if (dto.price() != null) {
            this.price = dto.price();
        }
    }
}


