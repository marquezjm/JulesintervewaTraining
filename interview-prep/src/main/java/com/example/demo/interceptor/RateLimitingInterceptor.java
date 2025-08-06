package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS = 10; // Máximo 10 peticiones...
    private static final long TIME_WINDOW_SECONDS = 60; // ...por cada 60 segundos.

    // Usamos dos mapas para almacenar los contadores y los tiempos de inicio de la ventana.
    // Key: IP del cliente
    private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStartTime = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = getClientIp(request);

        // Obtenemos el tiempo de inicio de la ventana para esta IP, o lo creamos si no existe.
        long startTime = windowStartTime.computeIfAbsent(clientIp, k -> System.currentTimeMillis());

        // Obtenemos el contador de peticiones, o lo inicializamos en 0.
        int count = requestCounts.computeIfAbsent(clientIp, k -> 0);

        long currentTime = System.currentTimeMillis();
        long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);

        // Si la ventana de tiempo ha expirado, reseteamos.
        if (elapsedTime >= TIME_WINDOW_SECONDS) {
            windowStartTime.put(clientIp, currentTime);
            requestCounts.put(clientIp, 1);
            return true;
        }

        // Si se supera el límite de peticiones...
        if (count >= MAX_REQUESTS) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Error: Demasiadas peticiones. Límite de " + MAX_REQUESTS + " peticiones cada " + TIME_WINDOW_SECONDS + " segundos.");
            return false; // Bloqueamos la petición.
        }

        // Incrementamos el contador y permitimos que la petición continúe.
        requestCounts.put(clientIp, count + 1);
        return true;
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
