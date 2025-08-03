package com.example.demo.controller;

import com.example.demo.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/algorithms")
public class SortingController {

    private final SortingService sortingService;

    @Autowired
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @GetMapping("/sorting")
    public String showSortingForm() {
        return "algorithms/sorting";
    }

    @PostMapping("/sorting/sort")
    public String sortNumbers(@RequestParam(name = "numbers", defaultValue = "") String numbersStr, Model model) {
        if (numbersStr.trim().isEmpty()) {
            model.addAttribute("error", "Por favor, introduce una lista de números.");
            return "algorithms/sorting";
        }

        List<Integer> numbers;
        try {
            numbers = Arrays.stream(numbersStr.split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            model.addAttribute("error", "La lista contiene valores no numéricos. Asegúrate de que sean números separados por comas.");
            model.addAttribute("originalNumbers", numbersStr);
            return "algorithms/sorting";
        }

        // Ordenar con Bubble Sort
        long startTimeBubble = System.nanoTime();
        List<Integer> bubbleSorted = sortingService.bubbleSort(numbers);
        long timeBubble = System.nanoTime() - startTimeBubble;

        // Ordenar con el sort de Java
        long startTimeJava = System.nanoTime();
        List<Integer> javaSorted = sortingService.javaSort(numbers);
        long timeJava = System.nanoTime() - startTimeJava;

        model.addAttribute("originalNumbers", numbersStr);
        model.addAttribute("bubbleSortedList", bubbleSorted);
        model.addAttribute("timeBubble", timeBubble);
        model.addAttribute("javaSortedList", javaSorted);
        model.addAttribute("timeJava", timeJava);

        return "algorithms/sorting";
    }
}
