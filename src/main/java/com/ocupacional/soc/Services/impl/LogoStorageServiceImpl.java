package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Services.Cadastros.LogoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class LogoStorageServiceImpl implements LogoStorageService {

    private final Path logoStorageLocation;

    public LogoStorageServiceImpl(@Value("${file.upload-dir.logos}") String uploadDirLogos) {
        this.logoStorageLocation = Paths.get(uploadDirLogos).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.logoStorageLocation);
        } catch (Exception ex) {
            throw new BusinessException("Não foi possível criar o diretório para armazenar as logos.", ex);
        }
    }

    @Override
    public String storeLogo(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException("O arquivo enviado está vazio ou é nulo.");
        }

        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (originalFileName.contains("..")) {
                throw new BusinessException(STR."Nome de arquivo inválido: \{originalFileName}");
            }

            // Gera um nome de arquivo único para evitar conflitos e problemas de segurança
            String fileExtension = "";
            int dotIndex = originalFileName.lastIndexOf('.');
            if (dotIndex >= 0) {
                fileExtension = originalFileName.substring(dotIndex);
            }
            String newFileName = UUID.randomUUID().toString() + fileExtension;

            // Copia o arquivo para o diretório de logos
            Path targetLocation = this.logoStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return STR."/logos/\{newFileName}";


        } catch (IOException ex) {
            throw new BusinessException(STR."Não foi possível armazenar a logo \{originalFileName}", ex);
        }
    }

    @Override
    public String getLogoUrl(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return null;
        }
        return STR."/logos/\{fileName}";
    }


}
