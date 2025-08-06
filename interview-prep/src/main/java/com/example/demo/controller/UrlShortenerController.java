package com.example.demo.controller;

import com.example.demo.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("url") String originalUrl, Model model, HttpServletRequest request) {
        String shortCode = urlShortenerService.shortenUrl(originalUrl);

        // Construimos la URL completa para mostrarla al usuario
        String shortenedUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/" + shortCode;

        model.addAttribute("originalUrl", originalUrl);
        model.addAttribute("shortenedUrl", shortenedUrl);

        return "apps/shortener"; // Vuelve a mostrar la página del acortador con el resultado.
    }

    /**
     * Endpoint de redirección.
     * @param shortCode El código corto extraído de la URL.
     * @return Un RedirectView que le dice al navegador que vaya a la URL original,
     *         o una vista de error si el código no se encuentra.
     */
    @GetMapping("/{shortCode}")
    public Object redirectToOriginalUrl(@PathVariable String shortCode, Model model) {
        return urlShortenerService.getOriginalUrl(shortCode)
            .map(url -> {
                // Añadimos "http://" si no está presente para asegurar una redirección válida.
                String finalUrl = url.startsWith("http://") || url.startsWith("https://") ? url : "http://" + url;
                return new RedirectView(finalUrl);
            })
            .orElseGet(() -> {
                model.addAttribute("error", "La URL acortada no existe.");
                return "apps/shortener"; // Muestra la página principal con un mensaje de error.
            });
    }
}
