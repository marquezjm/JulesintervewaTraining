package com.example.demo.service;

import com.example.demo.util.Base62;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {

    // Usamos un mapa concurrente para almacenar los mapeos de forma segura en un entorno multi-hilo.
    // Key: shortCode, Value: originalUrl
    private final Map<String, String> urlStore = new ConcurrentHashMap<>();

    // Un contador para generar IDs únicos para cada URL.
    private final AtomicLong counter = new AtomicLong(10000); // Empezamos desde un número para tener códigos más largos

    /**
     * Crea un código corto para una URL original.
     * @param originalUrl La URL que se va a acortar.
     * @return El código corto generado.
     */
    public String shortenUrl(String originalUrl) {
        long id = counter.incrementAndGet();
        String shortCode = Base62.encode(id);
        urlStore.put(shortCode, originalUrl);
        return shortCode;
    }

    /**
     * Obtiene la URL original a partir de un código corto.
     * @param shortCode El código corto.
     * @return Un Optional que contiene la URL original si se encuentra.
     */
    public Optional<String> getOriginalUrl(String shortCode) {
        return Optional.ofNullable(urlStore.get(shortCode));
    }
}
