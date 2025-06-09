package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaResponseDTO;
import com.ocupacional.soc.Entities.BibliografiaEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.BibliografiaMapper;
import com.ocupacional.soc.Repositories.BibliografiaRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.BibliografiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibliografiaServiceImpl implements BibliografiaService {

    private final BibliografiaRepository repository;
    private final BibliografiaMapper mapper;

    @Override
    public Page<BibliografiaResponseDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null) ? "" : search;
        return repository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(searchTerm, searchTerm, pageable)
                .map(mapper::toDto);
    }

    @Override
    public BibliografiaResponseDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Bibliografia não encontrada com ID: " + id));
    }

    @Override
    public BibliografiaResponseDTO create(BibliografiaRequestDTO dto) {
        BibliografiaEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public BibliografiaResponseDTO update(Long id, BibliografiaRequestDTO dto) {
        BibliografiaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bibliografia não encontrada com ID: " + id));
        mapper.updateEntityFromDto(dto, entity);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Bibliografia não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}