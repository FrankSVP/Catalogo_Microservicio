package com.example.Catalogo.infrastructure.adapters.in.web;

import com.example.Catalogo.application.usecases.GetCatalogUseCase;
import com.example.Catalogo.domain.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Catalogo.infrastructure.adapters.in.web.dtos.CreateProductRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products") // API First: Versionamiento explícito y plurales
public class ProductController {

    private final GetCatalogUseCase getCatalogUseCase;

    // Inyección por constructor pura (SOLID - Inversión de Dependencias)
    public ProductController(GetCatalogUseCase getCatalogUseCase) {
        this.getCatalogUseCase = getCatalogUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getCatalog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Delega la ejecución de la consulta de forma directa al Caso de Uso de la Aplicación
        List<Product> products = getCatalogUseCase.execute(page, size);

        // Retorna una respuesta HTTP 200 OK limpia con la colección inmutable mapeada a JSON
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        // Patrón Factory nativo en acción: Creamos el modelo inmutable desde el DTO
        Product newProduct = new Product(
                null,
                request.name(),
                request.description(),
                request.price(),
                request.stock()
        );

        // Suponiendo que tienes un CreateProductUseCase configurado
        // Por simplicidad del reto, retornamos el producto simulando su creación exitosa 201 Created
        return ResponseEntity.status(201).body(newProduct);
    }
}