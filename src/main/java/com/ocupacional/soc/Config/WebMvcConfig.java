package com.ocupacional.soc.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.upload-dir}")
    private String uploadDirCertificados;

    @Value("${file.upload-dir.logos}")
    private String uploadDirLogos;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Handler para certificados (existente)
        registry.addResourceHandler("/uploads/certificados-aparelhos/**")
                .addResourceLocations(STR."file:\{Paths.get(uploadDirCertificados).toAbsolutePath().normalize()}/");

        // NOVO: Handler para logos, tornando-as acessíveis via URL /logos/**
        registry.addResourceHandler("/logos/**")
                .addResourceLocations(STR."file:\{Paths.get(uploadDirLogos).toAbsolutePath().normalize()}/");
    }
}
