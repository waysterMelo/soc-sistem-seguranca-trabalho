package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.CboMapper;
import com.ocupacional.soc.Dto.Cadastros.CboRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.CboResponseDTO;
import com.ocupacional.soc.Repositories.Cadastros.CboRepository;
import com.ocupacional.soc.Services.CboService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CboServiceImpl implements CboService {

    private final CboRepository repository;
    private final CboMapper cboMapper;

    @Override
    public CboResponseDTO create(CboRequestDTO dto) {
        var entity = cboMapper.toEntity(dto);
        return cboMapper.toDto(repository.save(entity));
    }

    @Override
    public CboResponseDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CBO não encontrado"));
        return cboMapper.toDto(entity);
    }

    @Override
    public List<CboResponseDTO> findAll() {
        return repository.findAll().stream().map(cboMapper::toDto).toList();
    }

    @Override
    public CboResponseDTO update(Long id, CboRequestDTO dto) {
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CBO não encontrado"));
        cboMapper.updateEntityFromDto(dto, existing);
        return cboMapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
