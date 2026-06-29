package com.example.Catalogo.infrastructure.config;

import com.example.Catalogo.application.usecases.GetCatalogUseCase;
import com.example.Catalogo.domain.ports.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public GetCatalogUseCase getCatalogUseCase(ProductRepositoryPort productRepositoryPort) {
        return new GetCatalogUseCase(productRepositoryPort);
    }
}
