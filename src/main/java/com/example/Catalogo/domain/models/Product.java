package com.example.Catalogo.domain.models;
import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock
) {
    // Aquí puedes agregar validaciones de negocio nativas si lo deseas
    public Product {
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }
}