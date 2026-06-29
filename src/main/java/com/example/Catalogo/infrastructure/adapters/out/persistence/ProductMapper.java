package com.example.Catalogo.infrastructure.adapters.out.persistence;
import com.example.Catalogo.domain.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock()
        );
    }

    public ProductEntity toEntity(Product domain) {
        if (domain == null) return null;
        return new ProductEntity(
                domain.id(), // Al ser un Record, se accede como metodo sin la palabra "get"
                domain.name(),
                domain.description(),
                domain.price(),
                domain.stock()
        );
    }
}
