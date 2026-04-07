package br.edu.utfpr.api_produto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VendaDTO(
        @NotNull
        @Positive
        Integer quantity
) {}
