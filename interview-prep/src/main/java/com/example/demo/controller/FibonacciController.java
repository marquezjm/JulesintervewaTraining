package com.example.demo.controller;

import com.example.demo.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/algorithms")
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("/fibonacci")
    public String showFibonacciForm() {
        return "algorithms/fibonacci";
    }

    @PostMapping("/fibonacci/calculate")
    public String calculateFibonacci(@RequestParam(name = "number", defaultValue = "0") int n, Model model) {
        // Para evitar largos tiempos de espera con la versión recursiva, limitamos la entrada.
        if (n > 40) {
            model.addAttribute("error", "Para la demostración recursiva, por favor introduce un número menor o igual a 40.");
            model.addAttribute("n", n);
            return "algorithms/fibonacci";
        }
        if (n < 0) {
            model.addAttribute("error", "Por favor introduce un número no negativo.");
            model.addAttribute("n", n);
            return "algorithms/fibonacci";
        }


        // Calculamos con el método iterativo
        long startTimeIterative = System.nanoTime();
        long resultIterative = fibonacciService.fibonacciIterative(n);
        long timeIterative = System.nanoTime() - startTimeIterative;

        // Calculamos con el método recursivo
        long startTimeRecursive = System.nanoTime();
        long resultRecursive = fibonacciService.fibonacciRecursive(n);
        long timeRecursive = System.nanoTime() - startTimeRecursive;

        model.addAttribute("n", n);
        model.addAttribute("resultIterative", resultIterative);
        model.addAttribute("timeIterative", timeIterative);
        model.addAttribute("resultRecursive", resultRecursive);
        model.addAttribute("timeRecursive", timeRecursive);

        return "algorithms/fibonacci";
    }
}
