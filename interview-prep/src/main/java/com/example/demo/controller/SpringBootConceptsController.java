package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springboot-concepts")
public class SpringBootConceptsController {

    @GetMapping("/crud")
    public String showCrudApiPage() {
        return "springboot/crud";
    }
}
