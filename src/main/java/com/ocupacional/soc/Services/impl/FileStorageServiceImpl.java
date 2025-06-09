package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Services.Aparelhos.FileStorageService;
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
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new BusinessException("Não foi possível criar o diretório para armazenar os arquivos.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = "";
        try {
            if (originalFileName.contains("..")) {
                throw new BusinessException("Nome de arquivo inválido: " + originalFileName);
            }
            int dotIndex = originalFileName.lastIndexOf('.');
            if (dotIndex > 0) {
                fileExtension = originalFileName.substring(dotIndex);
            }
            String newFileName = UUID.randomUUID().toString() + fileExtension;

            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/certificados-aparelhos/")
                    .path(newFileName)
                    .toUriString();

        } catch (IOException ex) {
            throw new BusinessException("Não foi possível armazenar o arquivo " + originalFileName, ex);
        }
    }

    @Override
    public void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isBlank()) {
            return;
        }
        try {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            if(Files.exists(filePath)){
                Files.delete(filePath);
            }
        } catch (Exception ex) {
            // Log a warning instead of throwing an exception
            // as failing to delete an old file should not fail the main operation
            System.err.println("Não foi possível deletar o arquivo: " + fileUrl);
        }
    }
}