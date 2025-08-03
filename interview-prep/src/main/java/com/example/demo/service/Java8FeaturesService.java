package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Java8FeaturesService {

    /**
     * Utiliza la API de Streams para filtrar empleados por salario.
     *
     * Conceptos de Java 8 demostrados:
     * - stream(): Convierte la lista en un stream.
     * - filter(): Un operador intermedio que filtra elementos basado en un predicado (lambda).
     * - map(): Un operador intermedio que transforma cada elemento.
     * - collect(): Un operador terminal que recolecta los resultados en una nueva lista.
     * - Lambda expressions: (employee -> ...), una forma concisa de implementar interfaces funcionales.
     *
     * @param employees La lista de todos los empleados.
     * @param salaryThreshold El salario mínimo para filtrar.
     * @return Una lista de nombres de empleados que ganan más que el umbral.
     */
    public List<String> filterEmployeesBySalary(List<Employee> employees, double salaryThreshold) {
        return employees.stream()
                        .filter(employee -> employee.getSalary() > salaryThreshold)
                        .map(Employee::getName) // Usa una referencia a método, aún más conciso.
                        .collect(Collectors.toList());
    }

    /**
     * Busca un empleado por su nombre y devuelve un Optional.
     *
     * Concepto de Java 8 demostrado:
     * - Optional: Un contenedor que puede o no contener un valor no nulo. Ayuda a evitar NullPointerExceptions.
     *   `Optional.ofNullable()` crea un Optional vacío si el resultado es nulo, o un Optional con el valor si no lo es.
     *
     * @param employees La lista de empleados.
     * @param name El nombre del empleado a buscar.
     * @return Un Optional que contiene al empleado si se encuentra, o un Optional vacío si no.
     */
    public Optional<Employee> findEmployeeByName(List<Employee> employees, String name) {
        return employees.stream()
                        .filter(employee -> employee.getName().equalsIgnoreCase(name))
                        .findFirst(); // findFirst() devuelve un Optional.
    }
}
