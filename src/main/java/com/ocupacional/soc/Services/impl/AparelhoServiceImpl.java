package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoRequestDTO;
import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Mapper.AparelhoMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Services.Aparelhos.AparelhoService;
import com.ocupacional.soc.Services.Aparelhos.FileStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AparelhoServiceImpl implements AparelhoService {

    private final AparelhoRepository repository;
    private final AparelhoMapper mapper;
    private final FileStorageService fileStorageService;

    @Override
    public Page<AparelhoResponseDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null) ? "" : search;
        return repository.findByDescricaoContainingIgnoreCaseOrModeloContainingIgnoreCase(searchTerm, searchTerm, pageable)
                .map(mapper::toDto);
    }

    @Override
    public AparelhoResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Aparelho não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public AparelhoResponseDTO create(AparelhoRequestDTO dto, MultipartFile certificado) {
        AparelhoEntity entity = mapper.toEntity(dto);
        if (certificado != null && !certificado.isEmpty()) {
            String fileUrl = fileStorageService.storeFile(certificado);
            entity.setCertificadoUrl(fileUrl);
        }
        AparelhoEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public AparelhoResponseDTO update(Long id, AparelhoRequestDTO dto, MultipartFile certificado) {
        AparelhoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aparelho não encontrado com ID: " + id));

        String oldFileUrl = entity.getCertificadoUrl();
        mapper.updateEntityFromDto(dto, entity);

        if (certificado != null && !certificado.isEmpty()) {
            fileStorageService.deleteFile(oldFileUrl); // Deleta o arquivo antigo
            String newFileUrl = fileStorageService.storeFile(certificado);
            entity.setCertificadoUrl(newFileUrl);
        }

        AparelhoEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AparelhoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aparelho não encontrado com ID: " + id));

        fileStorageService.deleteFile(entity.getCertificadoUrl());
        repository.delete(entity);
    }
}