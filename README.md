# 🏦 Microservicio de Catálogo de Productos - Cuenta BCP

Este microservicio ha sido desarrollado como parte del proceso de selección de **NTT DATA** para la cuenta del **Banco de Crédito del Perú (BCP)**. Implementa un catálogo de productos modular, escalable y de alta disponibilidad, siguiendo las mejores prácticas de ingeniería de software.

---

### 📐 Arquitectura y Principios de Diseño

El sistema está estructurado bajo los lineamientos de **Arquitectura Hexagonal (Ports & Adapters)**, garantizando un desacoplamiento absoluto entre las reglas de negocio y los componentes de infraestructura.

*   **Core Inmutable (Domain):** Diseñado con **Records nativos de Java**, asegurando la inmutabilidad de las entidades de negocio. Está 100% libre de frameworks y librerías de terceros (incluyendo Lombok), cumpliendo con el purismo arquitectónico.
*   **SOLID & Clean Code:**
    *   **Inversión de Dependencias (D):** Los casos de uso de la aplicación interactúan únicamente con abstracciones (`ProductRepositoryPort`).
    *   **Responsabilidad Única (S):** Separación estricta entre el payload de entrada HTTP (`CreateProductRequest`), la entidad de persistencia mutable de Hibernate (`ProductEntity`) y el modelo de negocio (`Product`), conectados de forma segura mediante mappers dedicados.
*   **API First:** Diseño de endpoints REST versionados semánticamente, con uso de plurales y soporte nativo para **paginación y ordenamiento**, indispensables para el rendimiento de la banca.

---

### 🛠️ Stack Tecnológico

*   **Lenguaje:** Java 17 / 21 (Uso de Records nativos).
*   **Framework Principal:** Spring Boot 3.x (Spring Web, Spring Data JPA).
*   **Seguridad:** Spring Security (Arquitectura Stateless configurada).
*   **Base de Datos:** H2 Database (Motor relacional en memoria para ejecución inmediata).
*   **Pruebas:** JUnit 5 y Mockito (Aislamiento y simulación de componentes mediante Mocks).

---

### 🚀 Instrucciones de Ejecución Rápida

El proyecto cuenta con un componente de **Data Seeding** (`DataSeedConfig`) que inicializa automáticamente la base de datos en memoria con productos de prueba al encender el servidor.

1.  Abrir el proyecto en **IntelliJ IDEA**.
2.  Ejecutar la clase principal que contiene la anotación `@SpringBootApplication`.
3.  El servidor levantará en el puerto estándar `8080`.

#### Endpoints Disponibles (Probar en Postman):

*   **Consulta del Catálogo (Público):**
    `GET http://localhost:8080/api/v1/products?page=0&size=10`
*   **Simulación de Registro (Protegido por Spring Security):**
    `POST http://localhost:8080/api/v1/products`
    *(Devuelve HTTP 403 Forbidden de forma correcta por políticas de seguridad integradas).*

---

### 🧪 Ejecución de Pruebas Unitarias

La lógica del caso de uso principal (`GetCatalogUseCase`) cuenta con cobertura de pruebas automatizadas aisladas con Mockito. Para ejecutarlas:
*   Correr el comando de Maven: `mvn test` o ejecutar la clase `GetCatalogUseCaseTest` directamente desde el entorno IDE.
