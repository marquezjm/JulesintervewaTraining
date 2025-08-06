package com.example.demo.controller;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    private final TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    /**
     * Crea una nueva tarea.
     * @Valid: Activa la validación para el objeto TodoDTO. Si la validación falla,
     * Spring Boot lanzará una MethodArgumentNotValidException, que por defecto
     * resulta en una respuesta 400 Bad Request.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        return todoService.createTodo(todoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long id, @Valid @RequestBody TodoDTO todoDTO) {
        return todoService.updateTodo(id, todoDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para actualizar solo el estado 'completed' de una tarea.
     * Esto es más eficiente que enviar todo el objeto y es una práctica común en APIs (usando PATCH).
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable long id, @RequestBody Map<String, Boolean> payload) {
        Boolean completed = payload.get("completed");
        if (completed == null) {
            return ResponseEntity.badRequest().build(); // Petición mal formada si no se envía 'completed'
        }
        return todoService.updateTodoStatus(id, completed)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        if (todoService.deleteTodo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
