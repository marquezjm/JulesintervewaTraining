package com.example.demo.controller;

import com.example.demo.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para el ejercicio de Palíndromos.
 *
 * Este controlador maneja las solicitudes web para la página de palíndromos.
 * Utiliza @Controller porque devolveremos vistas HTML (Thymeleaf), no solo datos JSON.
 *
 * Conceptos de Spring Boot demostrados aquí:
 * - @Controller: Marca esta clase como un controlador web de Spring MVC.
 * - @Autowired: Realiza la inyección de dependencias. Spring inyectará una instancia de PalindromeService.
 * - @RequestMapping: Mapea las solicitudes web a los métodos de este controlador.
 * - @GetMapping: Es una abreviatura para @RequestMapping(method = RequestMethod.GET).
 * - @PostMapping: Es una abreviatura para @RequestMapping(method = RequestMethod.POST).
 * - @RequestParam: Vincula un parámetro de la solicitud web a un parámetro del método.
 * - Model: Un objeto para pasar datos desde el controlador a la vista.
 */
@Controller
@RequestMapping("/algorithms")
public class PalindromeController {

    private final PalindromeService palindromeService;

    /**
     * Constructor para inyección de dependencias.
     * Es una buena práctica usar inyección por constructor porque hace que las dependencias sean explícitas y obligatorias.
     * @param palindromeService El servicio que se inyectará.
     */
    @Autowired
    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    /**
     * Muestra la página principal del ejercicio de palíndromos.
     * @return El nombre de la vista de Thymeleaf que se debe renderizar.
     */
    @GetMapping("/palindrome")
    public String showPalindromeForm() {
        return "algorithms/palindrome"; // Servirá el archivo palindrome.html
    }

    /**
     * Procesa la solicitud para verificar si un texto es un palíndromo.
     * @param text El texto enviado desde el formulario.
     * @param model El modelo para pasar datos a la vista.
     * @return El nombre de la vista de Thymeleaf para mostrar el resultado.
     */
    @PostMapping("/palindrome/check")
    public String checkPalindrome(@RequestParam(name = "inputText", required = false) String text, Model model) {
        if (text != null && !text.trim().isEmpty()) {
            boolean isPalindrome = palindromeService.isPalindrome(text);
            model.addAttribute("originalText", text);
            model.addAttribute("isPalindrome", isPalindrome);
        }
        return "algorithms/palindrome"; // Vuelve a mostrar la misma página con el resultado.
    }
}
