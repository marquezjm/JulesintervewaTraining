package com.example.demo.model;

public class Todo {

    private long id;
    private String task;
    private boolean completed;

    public Todo(long id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
