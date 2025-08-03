package com.example.demo.oop;

/**
 * Clase concreta que representa una Motocicleta.
 *
 * Conceptos de POO demostrados:
 * - Herencia: Extiende la clase 'Vehicle'.
 * - Polimorfismo (Subtyping): Un objeto 'Motorcycle' es también un 'Vehicle'.
 */
public class Motorcycle extends Vehicle {

    public Motorcycle() {
        super("Motocicleta"); // Llama al constructor de la clase padre.
    }

    /**
     * Implementación del método abstracto de la clase padre.
     * @return El sonido específico que hace una motocicleta.
     */
    @Override
    public String makeSound() {
        return "¡Ñiaaaun!";
    }
}
