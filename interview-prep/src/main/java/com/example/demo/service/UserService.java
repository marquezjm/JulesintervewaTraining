package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Servicio para gestionar las operaciones CRUD de Usuarios.
 *
 * Conceptos de Spring Boot demostrados:
 * - @Service: Anotación que marca la clase como un componente de servicio en la capa de negocio.
 *   Spring la detectará y creará un bean para inyectar en otras partes de la aplicación (como en el controlador).
 */
@Service
public class UserService {

    // Usamos una lista en memoria para simular una base de datos.
    private final List<User> userList = new ArrayList<>();
    // Un generador de IDs atómico para asegurar IDs únicos en un entorno concurrente.
    private final AtomicLong counter = new AtomicLong();

    // Bloque de inicialización para tener algunos datos de ejemplo.
    public UserService() {
        userList.add(new User(counter.incrementAndGet(), "Alice", "alice@example.com"));
        userList.add(new User(counter.incrementAndGet(), "Bob", "bob@example.com"));
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public Optional<User> getUserById(long id) {
        return userList.stream()
                       .filter(user -> user.getId() == id)
                       .findFirst();
    }

    public User createUser(User user) {
        user.setId(counter.incrementAndGet());
        userList.add(user);
        return user;
    }

    public Optional<User> updateUser(long id, User userDetails) {
        return getUserById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return user;
        });
    }

    public boolean deleteUser(long id) {
        return userList.removeIf(user -> user.getId() == id);
    }
}
