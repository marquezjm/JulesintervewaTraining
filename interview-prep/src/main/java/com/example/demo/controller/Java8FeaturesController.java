package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.Java8FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/java8")
public class Java8FeaturesController {

    private final Java8FeaturesService java8Service;
    private final List<Employee> employeeList;

    @Autowired
    public Java8FeaturesController(Java8FeaturesService java8Service) {
        this.java8Service = java8Service;
        // Datos de ejemplo para la demostración
        this.employeeList = Arrays.asList(
            new Employee("Ana", "Recursos Humanos", 60000),
            new Employee("Luis", "Ingeniería", 80000),
            new Employee("Pedro", "Ingeniería", 95000),
            new Employee("Marta", "Ventas", 75000),
            new Employee("Juan", "Ventas", 78000)
        );
    }

    @GetMapping("/features")
    public String showJava8FeaturesPage(Model model) {
        model.addAttribute("employees", employeeList);
        return "java8/features";
    }

    @PostMapping("/features/filter")
    public String filterBySalary(@RequestParam(defaultValue = "70000") double salary, Model model) {
        List<String> filteredNames = java8Service.filterEmployeesBySalary(employeeList, salary);
        model.addAttribute("filteredNames", filteredNames);
        model.addAttribute("salary", salary);
        model.addAttribute("employees", employeeList);
        return "java8/features";
    }

    @PostMapping("/features/find")
    public String findByName(@RequestParam(defaultValue = "") String name, Model model) {
        Optional<Employee> foundEmployee = java8Service.findEmployeeByName(employeeList, name);

        // Demostración de cómo usar Optional
        String searchResult = foundEmployee
            .map(emp -> "Empleado encontrado: " + emp.getName() + " - " + emp.getDepartment() + " ($" + emp.getSalary() + ")")
            .orElse("No se encontró ningún empleado con el nombre '" + name + "'.");

        model.addAttribute("searchResult", searchResult);
        model.addAttribute("nameSearched", name);
        model.addAttribute("employees", employeeList);
        return "java8/features";
    }
}
