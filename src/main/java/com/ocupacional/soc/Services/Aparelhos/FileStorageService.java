package com.ocupacional.soc.Services.Aparelhos;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    void deleteFile(String fileName);
}