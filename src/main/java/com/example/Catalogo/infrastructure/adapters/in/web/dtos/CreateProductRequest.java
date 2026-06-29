package com.example.Catalogo.infrastructure.adapters.in.web.dtos;
import java.math.BigDecimal;
public record CreateProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock
) {}
