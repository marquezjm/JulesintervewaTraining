package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar Usuarios.
 *
 * Conceptos de Spring Boot demostrados:
 * - @RestController: Especialización de @Controller. Indica que todos los métodos devuelven un @ResponseBody,
 *   lo que significa que los objetos devueltos se serializan directamente a JSON/XML en la respuesta HTTP.
 *   Perfecto para APIs REST.
 * - @RequestMapping("/api"): Define un prefijo de ruta para todos los endpoints en este controlador.
 * - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping: Anotaciones para mapear los verbos HTTP a los métodos.
 * - @PathVariable: Vincula una variable de la URL (ej. {id}) a un parámetro del método.
 * - @RequestBody: Deserializa el cuerpo de la solicitud HTTP (ej. un JSON) en un objeto Java.
 * - ResponseEntity: Permite un control total sobre la respuesta HTTP, incluyendo el código de estado,
 *   cabeceras y el cuerpo. Es una buena práctica usarlo en APIs REST.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users - Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET /api/users/{id} - Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok) // Si se encuentra, devuelve 200 OK con el usuario
                .orElse(ResponseEntity.notFound().build()); // Si no, devuelve 404 Not Found
    }

    // POST /api/users - Crear un nuevo usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Devuelve 201 Created
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // PUT /api/users/{id} - Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/users/{id} - Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content si se eliminó
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existía
        }
    }
}
