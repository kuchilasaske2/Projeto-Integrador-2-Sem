package br.com.restaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(@NonNull CorsRegistry registry) {

        registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "DELETE");

    }
}
