package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) para las operaciones de creación y actualización de Tareas.
 *
 * ¿Por qué usar un DTO?
 * 1.  Separación de incumbencias: El modelo (`Todo`) representa la entidad en nuestra base de datos (o en memoria),
 *     mientras que el DTO representa los datos que se transfieren a través de la red (API).
 * 2.  Seguridad: Evita exponer campos internos del modelo. Por ejemplo, el modelo podría tener un campo
 *     `creationDate` que no queremos que el cliente pueda establecer.
 * 3.  Validación: Permite aplicar reglas de validación específicas para la API que no necesariamente
 *     pertenecen al modelo de dominio.
 */
public class TodoDTO {

    @NotBlank(message = "La descripción de la tarea no puede estar vacía.")
    private String task;

    private boolean completed;

    // Getters and Setters
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
