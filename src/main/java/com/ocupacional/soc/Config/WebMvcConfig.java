package com.ocupacional.soc.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir.pgr-capas}")
    private String uploadDirPgrCapas;


    @Value("${file.upload-dir}")
    private String uploadDirCertificados;

    @Value("${file.upload-dir.logos}")
    private String uploadDirLogos;

    @Value("${file.upload-dir.ltcat-capas}")
    private String uploadDirLtcatCapas;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia a URL /uploads/certificados-aparelhos/** para o diretório físico
        String certificadosPath = Paths.get(uploadDirCertificados).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/certificados-aparelhos/**")
                .addResourceLocations("file:" + certificadosPath + "/");

        // Mapeia a URL /logos/** para o diretório físico de logos
        String logosPath = Paths.get(uploadDirLogos).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/logos/**")
                .addResourceLocations("file:" + logosPath + "/");

        // Mapeia a URL /uploads/pgr-capas/** para o diretório de capas do PGR
        String pgrCapasPath = Paths.get(uploadDirPgrCapas).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/pgr-capas/**")
                .addResourceLocations("file:" + pgrCapasPath + "/");

        String ltcatCapasPath = Paths.get(uploadDirLtcatCapas).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/capa-ltcat/**")
                .addResourceLocations("file:" + ltcatCapasPath + "/");
    }
}
