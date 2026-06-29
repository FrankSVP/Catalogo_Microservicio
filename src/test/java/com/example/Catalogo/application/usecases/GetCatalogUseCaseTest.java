package com.example.Catalogo.application.usecases;

import com.example.Catalogo.domain.models.Product;
import com.example.Catalogo.domain.ports.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCatalogUseCaseTest {

    private ProductRepositoryPort productRepositoryPortMock;
    private GetCatalogUseCase getCatalogUseCase;

    @BeforeEach
    void setUp() {
        // 1. Creamos un "simulador" (mock) del puerto de dominio
        this.productRepositoryPortMock = Mockito.mock(ProductRepositoryPort.class);
        // 2. Inyectamos manualmente el simulador en nuestro caso de uso (Clean Code)
        this.getCatalogUseCase = new GetCatalogUseCase(productRepositoryPortMock);
    }

    @Test
    @DisplayName("Debería retornar la lista de productos paginada correctamente")
    void shouldReturnCatalogSuccessfully() {
        // ARRANGE (Preparar el escenario)
        List<Product> mockProducts = List.of(
                new Product(1L, "Laptop", "Gamer", new BigDecimal("4000"), 10),
                new Product(2L, "Mouse", "Gamer", new BigDecimal("150"), 20)
        );
        // Le decimos al mock: "Cuando te pidan la página 0 con tamaño 10, devuelve esta lista fija"
        when(productRepositoryPortMock.findAll(0, 10)).thenReturn(mockProducts);

        // ACT (Ejecutar la acción a probar)
        List<Product> result = getCatalogUseCase.execute(0, 10);

        // ASSERT (Verificar que los resultados sean los esperados - Las afirmaciones)
        assertNotNull(result, "La lista resultante no debería ser nula");
        assertEquals(2, result.size(), "El tamaño de la lista debería ser 2");
        assertEquals("Laptop", result.get(0).name(), "El primer producto debería ser 'Laptop'");

        // Verificamos que el caso de uso realmente haya llamado al repositorio exactamente una vez
        verify(productRepositoryPortMock, times(1)).findAll(0, 10);
    }

    @Test
    @DisplayName("Debería corregir la paginación a valores por defecto si recibe números negativos o en cero")
    void shouldFixPaginationWhenInputIsInvalid() {
        // ARRANGE
        // Si el usuario manda página -5 y tamaño -20, el caso de uso debe cambiarlo a 0 y 10
        when(productRepositoryPortMock.findAll(0, 10)).thenReturn(List.of());

        // ACT
        List<Product> result = getCatalogUseCase.execute(-5, -20);

        // ASSERT
        assertNotNull(result);
        // Verificamos que la interfaz del puerto haya recibido los valores corregidos (0 y 10) y no los negativos
        verify(productRepositoryPortMock, times(1)).findAll(0, 10);
    }
}
