package com.example.Catalogo.domain.ports;

import com.example.Catalogo.domain.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    List<Product> findAll(int page, int size);
    Optional<Product> findById(Long id);
    Product save(Product product);
}