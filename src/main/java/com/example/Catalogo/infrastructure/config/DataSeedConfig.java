package com.example.Catalogo.infrastructure.config;
import com.example.Catalogo.infrastructure.adapters.out.persistence.ProductEntity;
import com.example.Catalogo.infrastructure.adapters.out.persistence.JpaProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
@Configuration
public class DataSeedConfig {

    @Bean
    CommandLineRunner initDatabase(JpaProductRepository repository) {
        return args -> {
            // Inserta registros de prueba mutables directamente en la base de datos de infraestructura
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new ProductEntity(null, "Laptop Gamer", "Procesador de última generación con 16GB RAM", new BigDecimal("4500.00"), 15),
                        new ProductEntity(null, "Teclado Mecánico", "Switches red silenciosos con iluminación RGB", new BigDecimal("250.00"), 30),
                        new ProductEntity(null, "Mouse Inalámbrico", "Sensor óptico de alta precisión ergonómico", new BigDecimal("180.00"), 50)
                ));
                System.out.println("🌱 Base de datos H2 inicializada con productos de prueba de catálogo.");
            }
        };
    }
}
