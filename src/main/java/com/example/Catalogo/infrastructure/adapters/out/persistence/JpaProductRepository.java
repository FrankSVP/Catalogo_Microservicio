package com.example.Catalogo.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    // Hereda de JpaRepository mapeando la entidad mutable (ProductEntity) y su tipo de ID (Long)
}
