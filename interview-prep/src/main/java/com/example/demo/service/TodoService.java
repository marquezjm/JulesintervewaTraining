package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final List<Todo> todoList = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public TodoService() {
        // Datos iniciales de ejemplo
        todoList.add(new Todo(counter.incrementAndGet(), "Preparar la presentación para la entrevista", true));
        todoList.add(new Todo(counter.incrementAndGet(), "Revisar conceptos de Spring Boot", false));
    }

    public List<Todo> getAllTodos() {
        return todoList;
    }

    public Todo createTodo(TodoDTO todoDTO) {
        Todo newTodo = new Todo(
            counter.incrementAndGet(),
            todoDTO.getTask(),
            todoDTO.isCompleted()
        );
        todoList.add(newTodo);
        return newTodo;
    }

    public Optional<Todo> updateTodo(long id, TodoDTO todoDTO) {
        return todoList.stream()
            .filter(todo -> todo.getId() == id)
            .findFirst()
            .map(todo -> {
                todo.setTask(todoDTO.getTask());
                todo.setCompleted(todoDTO.isCompleted());
                return todo;
            });
    }

    public Optional<Todo> updateTodoStatus(long id, boolean completed) {
        return todoList.stream()
            .filter(todo -> todo.getId() == id)
            .findFirst()
            .map(todo -> {
                todo.setCompleted(completed);
                return todo;
            });
    }

    public boolean deleteTodo(long id) {
        return todoList.removeIf(todo -> todo.getId() == id);
    }
}
