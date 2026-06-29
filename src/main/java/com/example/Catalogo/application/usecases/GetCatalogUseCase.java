package com.example.Catalogo.application.usecases;

import com.example.Catalogo.domain.models.Product;
import com.example.Catalogo.domain.ports.ProductRepositoryPort;
import java.util.List;

public class GetCatalogUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    // Inyección por constructor pura (Principio de Inversión de Dependencias)
    public GetCatalogUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> execute(int page, int size) {
        int validatedPage = (page < 0) ? 0 : page;
        int validatedSize = (size <= 0) ? 10 : size;

        return productRepositoryPort.findAll(validatedPage, validatedSize);
    }
}
