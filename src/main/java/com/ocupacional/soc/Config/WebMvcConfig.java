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

    @Value("${file.upload-dir.ltip-capas}")
    private String uploadDirLtipCapas;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia a URL para o diretório físico de certificados
        String certificadosPath = Paths.get(uploadDirCertificados).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/certificados-aparelhos/**", "/api/uploads/certificados-aparelhos/**")
                .addResourceLocations("file:" + certificadosPath + "/");

        // Mapeia a URL para o diretório físico de logos
        String logosPath = Paths.get(uploadDirLogos).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/logos/**", "/api/logos/**")
                .addResourceLocations("file:" + logosPath + "/");

        // Mapeia a URL para o diretório de capas do PGR
        String pgrCapasPath = Paths.get(uploadDirPgrCapas).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/pgr-capas/**", "/api/uploads/pgr-capas/**")
                .addResourceLocations("file:" + pgrCapasPath + "/");

        // Mapeia a URL para o diretório de capas do LTCAT
        String ltcatCapasPath = Paths.get(uploadDirLtcatCapas).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/capa-ltcat/**", "/api/uploads/capa-ltcat/**")
                .addResourceLocations("file:" + ltcatCapasPath + "/");

        String ltipCapasPath = Paths.get(uploadDirLtipCapas).toAbsolutePath().normalize().toString();
        registry.addResourceHandler("/uploads/capa-ltip/**", "/api/uploads/capa-ltip/**")
                .addResourceLocations("file:" + ltipCapasPath + "/");
    }
}
