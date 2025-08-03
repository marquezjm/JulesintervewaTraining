package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciService {

    /**
     * Calcula el n-ésimo número de Fibonacci de forma recursiva.
     * Esta solución es elegante pero ineficiente para números grandes (O(2^n))
     * debido a los cálculos repetidos.
     *
     * @param n El índice en la secuencia (base 0).
     * @return El número de Fibonacci en la posición n.
     */
    public long fibonacciRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El índice no puede ser negativo.");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Calcula el n-ésimo número de Fibonacci de forma iterativa.
     * Esta solución es mucho más eficiente (O(n)) y no causa StackOverflowError
     * para números grandes.
     *
     * @param n El índice en la secuencia (base 0).
     * @return El número de Fibonacci en la posición n.
     */
    public long fibonacciIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El índice no puede ser negativo.");
        }
        if (n <= 1) {
            return n;
        }
        long fib = 1;
        long prevFib = 1;

        for (int i = 2; i < n; i++) {
            long temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }
}
