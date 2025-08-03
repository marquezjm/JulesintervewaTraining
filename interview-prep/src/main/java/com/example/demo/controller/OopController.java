package com.example.demo.controller;

import com.example.demo.oop.Car;
import com.example.demo.oop.Motorcycle;
import com.example.demo.oop.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/oop")
public class OopController {

    @GetMapping("/demo")
    public String showOopDemo(Model model) {
        // Creamos una lista de 'Vehicle'.
        List<Vehicle> vehicles = new ArrayList<>();

        // Agregamos instancias de las clases concretas.
        vehicles.add(new Car());
        vehicles.add(new Motorcycle());

        // Aquí se demuestra el POLIMORFISMO.
        // Iteramos sobre una lista de 'Vehicle', pero en tiempo de ejecución,
        // Java invoca el método 'makeSound()' de la clase real del objeto (Car o Motorcycle).
        // El código no necesita saber el tipo específico de vehículo, solo que es un 'Vehicle'.
        List<String> vehicleInfos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleInfos.add(vehicle.getInfo() + " Su sonido es: " + vehicle.makeSound());
        }

        // Pasamos la lista de información a la vista.
        model.addAttribute("vehicleInfos", vehicleInfos);

        return "oop/demo";
    }
}
