package com.example.demo.config;

import com.example.demo.interceptor.RateLimitingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RateLimitingInterceptor rateLimitingInterceptor;

    @Autowired
    public WebConfig(RateLimitingInterceptor rateLimitingInterceptor) {
        this.rateLimitingInterceptor = rateLimitingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registramos nuestro interceptor y especificamos a qué rutas se debe aplicar.
        registry.addInterceptor(rateLimitingInterceptor)
                .addPathPatterns("/api/limited/**");
    }
}
