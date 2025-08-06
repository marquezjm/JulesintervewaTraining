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
     * @return Una cadena de redirección si el código es válido, o el nombre de la vista de error si no lo es.
     */
    @GetMapping("/{shortCode}")
    public String redirectToOriginalUrl(@PathVariable String shortCode, Model model) {
        return urlShortenerService.getOriginalUrl(shortCode)
                .map(url -> {
                    // Añadimos "http://" si no está presente para asegurar una redirección válida.
                    String finalUrl = url.startsWith("http://") || url.startsWith("https://") ? url : "http://" + url;
                    // El prefijo "redirect:" le indica a Spring MVC que realice una redirección.
                    return "redirect:" + finalUrl;
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "La URL acortada '" + shortCode + "' no existe o no es válida.");
                    return "apps/shortener"; // Muestra la página principal con un mensaje de error.
                });
    }
}
