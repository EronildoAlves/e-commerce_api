package com.nascimento.ecommerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Descrição é obrigatório")
        String description,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
        BigDecimal price) {
}
