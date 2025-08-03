package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PalindromeService {

    /**
     * Verifica si un texto es un palíndromo.
     * Ignora espacios en blanco y mayúsculas/minúsculas.
     *
     * Un palíndromo es una palabra o frase que se lee igual de izquierda a derecha que de derecha a izquierda.
     *
     * Ejemplos:
     * "ana" -> true
     * "oso" -> true
     * "reconocer" -> true
     * "anita lava la tina" -> true
     * "hola" -> false
     *
     * @param text El texto a verificar.
     * @return true si es un palíndromo, false en caso contrario.
     */
    public boolean isPalindrome(String text) {
        if (text == null) {
            // Decisión de diseño: ¿un nulo es un palíndromo? Para este ejercicio, consideremos que no.
            return false;
        }
        // Normalizamos el texto: quitamos espacios y lo convertimos a minúsculas.
        String cleanedText = text.replaceAll("\\s+", "").toLowerCase();

        // Un texto vacío puede ser considerado un palíndromo, es una pregunta común en entrevistas.
        if (cleanedText.isEmpty()) {
            return true;
        }

        int left = 0;
        int right = cleanedText.length() - 1;

        while (left < right) {
            if (cleanedText.charAt(left) != cleanedText.charAt(right)) {
                return false; // Si los caracteres no coinciden, no es un palíndromo.
            }
            left++;
            right--;
        }

        return true; // Si el bucle termina, es un palíndromo.
    }
}
