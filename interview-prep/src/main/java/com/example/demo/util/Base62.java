package com.example.demo.util;

public class Base62 {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();

    /**
     * Codifica un número a una cadena en Base62.
     * @param number El número a codificar (debe ser positivo).
     * @return La cadena codificada.
     */
    public static String encode(long number) {
        if (number == 0) {
            return String.valueOf(ALPHABET.charAt(0));
        }

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(ALPHABET.charAt((int) (number % BASE)));
            number /= BASE;
        }
        return sb.reverse().toString();
    }

    /**
     * Decodifica una cadena en Base62 a un número.
     * @param str La cadena a decodificar.
     * @return El número decodificado.
     */
    public static long decode(String str) {
        long number = 0;
        for (int i = 0; i < str.length(); i++) {
            number = number * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return number;
    }
}
