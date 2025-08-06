package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/limited")
public class LimitedResourceController {

    /**
     * Un endpoint de ejemplo protegido por el RateLimiter.
     * Solo se puede acceder a él un número limitado de veces por minuto.
     */
    @GetMapping("/resource")
    public Map<String, String> getLimitedResource() {
        return Collections.singletonMap("message", "¡Acceso concedido! La hora actual es " + LocalTime.now());
    }
}
