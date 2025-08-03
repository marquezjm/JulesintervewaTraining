package com.example.demo.oop;

/**
 * Clase abstracta que representa un Vehículo.
 *
 * Conceptos de POO demostrados:
 * - Abstracción: Define una plantilla para clases de vehículos. No se puede instanciar
 *   directamente un 'Vehicle', solo sus subclases concretas. Define qué debe hacer
 *   un vehículo (tener un nombre, hacer un sonido) sin decir cómo.
 * - Encapsulamiento: Los campos (name) son privados y se accede a ellos a través de métodos públicos (getters).
 */
public abstract class Vehicle {

    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Método abstracto.
     * Cada subclase de Vehicle DEBE proporcionar su propia implementación de este método.
     * Esto garantiza que todo vehículo sepa "hacer un sonido".
     */
    public abstract String makeSound();

    /**
     * Método concreto en una clase abstracta.
     * Proporciona una funcionalidad común que todas las subclases heredan.
     */
    public String getInfo() {
        return "Este vehículo es un " + name + ".";
    }
}
