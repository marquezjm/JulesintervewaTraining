package com.example.demo.oop;

/**
 * Clase concreta que representa un Coche.
 *
 * Conceptos de POO demostrados:
 * - Herencia: Extiende la clase 'Vehicle', heredando sus atributos y métodos (como getName() y getInfo()).
 * - Polimorfismo (Subtyping): Un objeto 'Car' es también un 'Vehicle'.
 */
public class Car extends Vehicle {

    public Car() {
        super("Coche"); // Llama al constructor de la clase padre.
    }

    /**
     * Implementación del método abstracto de la clase padre.
     * Esto es un ejemplo de Polimorfismo (Overriding).
     * @return El sonido específico que hace un coche.
     */
    @Override
    public String makeSound() {
        return "Brum, brum!";
    }
}
