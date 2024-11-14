package com.akofood.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-origin-frontend-dev}")
    private String allowedOriginFrontendDev;

    @Value("${cors.allowed-origin-backend-dev}")
    private String allowedOriginBackendDev;

    @Value("${cors.allowed-origin-backend-dev-2}")
    private String allowedOriginBackendDev2;

    @Value("${cors.allowed-origin-deploy}")
    private String allowedOriginDeploy;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOriginFrontendDev, allowedOriginBackendDev, allowedOriginBackendDev2, allowedOriginDeploy)
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE");
    }

}
