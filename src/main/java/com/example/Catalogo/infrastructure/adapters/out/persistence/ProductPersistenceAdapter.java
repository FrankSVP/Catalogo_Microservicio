package com.example.Catalogo.infrastructure.adapters.out.persistence;

import com.example.Catalogo.domain.models.Product;
import com.example.Catalogo.domain.ports.ProductRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    // Inyeccion por constructor pura
    public ProductPersistenceAdapter(JpaProductRepository jpaProductRepository, ProductMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll(int page, int size) {
        // Ejecuta la consulta paginada de Spring Data, extrae el contenido,
        // mapea cada ProductEntity a Product (Dominio) y lo devuelve como lista.
        return jpaProductRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(productMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        // Convierte el dominio inmutable a entidad mutable para que Hibernate pueda guardarlo
        ProductEntity entityToSave = productMapper.toEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(entityToSave);
        // Devuelve el resultado guardado de vuelta al formato inmutable del dominio
        return productMapper.toDomain(savedEntity);
    }
}
