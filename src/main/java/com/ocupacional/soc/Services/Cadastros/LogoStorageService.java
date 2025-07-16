package com.ocupacional.soc.Services.Cadastros;

import org.springframework.web.multipart.MultipartFile;

public interface LogoStorageService {

    String storeLogo(MultipartFile file);
    String getLogoUrl(String fileName);
}
