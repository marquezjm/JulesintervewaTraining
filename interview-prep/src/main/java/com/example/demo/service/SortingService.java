package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SortingService {

    /**
     * Ordena una lista de números usando el algoritmo Bubble Sort.
     * Es un algoritmo simple pero ineficiente (O(n^2)), útil para demostrar
     * conceptos básicos de ordenamiento.
     *
     * @param numbers La lista de números a ordenar.
     * @return Una nueva lista con los números ordenados.
     */
    public List<Integer> bubbleSort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }
        // Creamos una copia para no modificar la lista original.
        List<Integer> sortedList = new ArrayList<>(numbers);
        int n = sortedList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedList.get(j) > sortedList.get(j + 1)) {
                    // Intercambiamos los elementos
                    int temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                    swapped = true;
                }
            }
            // Si no hubo intercambios en una pasada, la lista ya está ordenada.
            if (!swapped) break;
        }
        return sortedList;
    }

    /**
     * Ordena una lista de números usando el método optimizado de la librería estándar de Java.
     * Utiliza un algoritmo eficiente (Timsort, una mezcla de Merge Sort e Insertion Sort)
     * con una complejidad promedio de O(n log n).
     *
     * @param numbers La lista de números a ordenar.
     * @return Una nueva lista con los números ordenados.
     */
    public List<Integer> javaSort(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> sortedList = new ArrayList<>(numbers);
        Collections.sort(sortedList);
        return sortedList;
    }
}
