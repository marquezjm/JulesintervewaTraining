# Proyecto de Preparación para Entrevistas de Java y Spring Boot

Este proyecto es una herramienta de estudio interactiva diseñada para ayudarte a prepararte para entrevistas técnicas centradas en Java y Spring Boot. Contiene una serie de ejercicios prácticos que cubren desde algoritmos fundamentales hasta conceptos clave de Spring Boot.

## ¿Cómo Empezar?

1.  **Clona el repositorio:**
    ```bash
    git clone <URL-del-repositorio>
    cd interview-prep
    ```

2.  **Ejecuta el proyecto:**
    Puedes ejecutar la aplicación usando el wrapper de Gradle incluido.

    En Linux/macOS:
    ```bash
    ./gradlew bootRun
    ```

    En Windows:
    ```bash
    gradlew.bat bootRun
    ```
    La aplicación estará disponible en `http://localhost:8080`.

3.  **Abre tu navegador:**
    Navega a `http://localhost:8080` para ver el menú principal de ejercicios.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes principales dentro de `src/main/java/com/example/demo`:

-   `controller`: Contiene los controladores de Spring. Los `*Controller` manejan las vistas web (HTML), mientras que el `*RestController` expone una API JSON.
-   `service`: Contiene la lógica de negocio. Los servicios son utilizados por los controladores.
-   `model`: Clases de dominio (POJOs) que representan las entidades de la aplicación (ej. `User`, `Employee`).
-   `algorithms`, `oop`, `java8`: Paquetes que contienen la lógica específica de cada ejercicio.

## Ejercicios Disponibles

### 1. Algoritmos y Estructuras de Datos

Esta sección se centra en problemas clásicos de la informática para evaluar tu lógica de programación.

-   **Verificador de Palíndromos:** Un ejercicio simple para la manipulación de cadenas.
-   **Secuencia de Fibonacci:** Compara una solución recursiva (ineficiente) con una iterativa (eficiente), un punto de discusión común sobre complejidad.
-   **Ordenamiento de Arrays:** Compara un algoritmo de ordenamiento simple (Bubble Sort) con el `Collections.sort()` de Java para discutir sobre eficiencia y el uso de librerías estándar.

### 2. Programación Orientada a Objetos (POO)

Demuestra los pilares de la POO en Java.

-   **Conceptos:** Usa una clase abstracta `Vehicle` y subclases `Car` y `Motorcycle` para demostrar:
    -   **Abstracción:** `Vehicle` define un contrato común.
    -   **Herencia:** Las subclases heredan de `Vehicle`.
    -   **Polimorfismo:** Se trata a los objetos `Car` y `Motorcycle` como `Vehicle`, y se invoca el método `makeSound()` correcto en tiempo de ejecución.
    -   **Encapsulamiento:** Los campos están protegidos.

### 3. Características de Java 8+

Cubre algunas de las características más importantes introducidas a partir de Java 8.

-   **API de Streams:** Demuestra cómo usar `filter`, `map`, y `collect` para procesar colecciones de una manera declarativa y funcional.
-   **Optional:** Muestra cómo usar `Optional` para evitar `NullPointerException` y escribir un código más seguro y expresivo.

### 4. Conceptos Clave de Spring Boot

Se centra en las funcionalidades principales del framework Spring Boot.

-   **API RESTful (CRUD):** Implementa una API REST completa para gestionar "Usuarios".
    -   **`@RestController`:** Devuelve JSON en lugar de vistas HTML.
    -   **Verbos HTTP:** Usa `GET`, `POST`, `PUT`, `DELETE` para las operaciones correspondientes.
    -   **`ResponseEntity`:** Permite un control total sobre la respuesta HTTP (códigos de estado, etc.).
    -   **Inyección de Dependencias:** Se muestra cómo `@Autowired` (o mejor, la inyección por constructor) desacopla los componentes.

## Ejecutando con Docker (Opcional)

Si tienes Docker instalado, puedes construir y ejecutar la aplicación en un contenedor. Esto asegura que se ejecute en un entorno consistente.

1.  **Construye la imagen de Docker:**
    Desde la raíz del proyecto, ejecuta:
    ```bash
    docker build -t interview-prep .
    ```

2.  **Ejecuta el contenedor:**
    Una vez construida la imagen, ejecútala con:
    ```bash
    docker run -p 8080:8080 interview-prep
    ```
    La aplicación estará disponible en `http://localhost:8080` en tu máquina local.

## ¡Buena Suerte en tu Entrevista!

Usa este proyecto para experimentar. Modifica el código, añade nuevos ejercicios o intenta resolver los problemas de una manera diferente. ¡El objetivo es aprender y ganar confianza!

### 5. Ejercicios Prácticos de Entrevista

Esta sección contiene mini-aplicaciones que simulan problemas comunes en entrevistas de trabajo.

-   **Aplicación de Lista de Tareas (To-Do):** Un CRUD completo que demuestra la separación de incumbencias con DTOs, validación de entrada, y una API REST consumida por un frontend de JavaScript simple.
-   **Acortador de URLs:** Un ejercicio clásico de diseño de sistemas que implica redirecciones HTTP y generación de códigos únicos.
-   **Limitador de Tasa (Rate Limiter):** Demuestra cómo usar Interceptores de Spring (`HandlerInterceptor`) para proteger una API contra el abuso, una técnica esencial para la robustez de un sistema.
