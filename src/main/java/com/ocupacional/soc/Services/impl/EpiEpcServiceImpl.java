package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.EpiDto.EpiEpcRequestDTO;
import com.ocupacional.soc.Dto.EpiDto.EpiEpcResponseDTO;
import com.ocupacional.soc.Entities.Epi.EpiEpcEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.EpiMappers.EpiEpcMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Epi.EpiCategoriaRepository;
import com.ocupacional.soc.Repositories.Epi.EpiEpcRepository;
import com.ocupacional.soc.Repositories.Epi.EpiFabricanteRepository;
import com.ocupacional.soc.Services.EpiService.EpiEpcService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpiEpcServiceImpl implements EpiEpcService {

    private final EpiEpcRepository epiEpcRepository;
    private final EmpresaRepository empresaRepository;
    private final EpiCategoriaRepository categoriaRepository;
    private final EpiFabricanteRepository fabricanteRepository;
    private final EpiEpcMapper mapper;

    @Override
    public Page<EpiEpcResponseDTO> findAll(Pageable pageable, Long empresaId, String search) {
        Page<EpiEpcEntity> page;
        String searchTerm = (search == null) ? "" : search;
        if (empresaId != null) {
            page = epiEpcRepository.findByEmpresaIdAndNomeContainingIgnoreCase(empresaId, searchTerm, pageable);
        } else {
            page = epiEpcRepository.findByNomeContainingIgnoreCase(searchTerm, pageable);
        }
        return page.map(mapper::toDto);
    }

    @Override
    public EpiEpcResponseDTO findById(Long id) {
        return epiEpcRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("EPI/EPC não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public EpiEpcResponseDTO create(EpiEpcRequestDTO dto) {
        EpiEpcEntity entity = mapper.toEntity(dto);
        updateRelationships(entity, dto);
        return mapper.toDto(epiEpcRepository.save(entity));
    }

    @Override
    @Transactional
    public EpiEpcResponseDTO update(Long id, EpiEpcRequestDTO dto) {
        EpiEpcEntity entity = epiEpcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EPI/EPC não encontrado com ID: " + id));

        // Atualizar campos primitivos
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        entity.setModelo(dto.getModelo());
        entity.setValidadeCA(dto.getValidadeCA());
        entity.setApenasParaPGR(dto.isApenasParaPGR());
        entity.setCertificadoAvaliacao(dto.getCertificadoAvaliacao());
        entity.setPeriodicidadeUso(dto.getPeriodicidadeUso());
        entity.setCriadoPor(dto.getCriadoPor());

        updateRelationships(entity, dto);

        return mapper.toDto(epiEpcRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!epiEpcRepository.existsById(id)) {
            throw new ResourceNotFoundException("EPI/EPC não encontrado com ID: " + id);
        }
        epiEpcRepository.deleteById(id);
    }

    private void updateRelationships(EpiEpcEntity entity, EpiEpcRequestDTO dto) {
        entity.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com ID: " + dto.getEmpresaId())));

        if (dto.getCategoriaId() != null) {
            entity.setCategoria(categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + dto.getCategoriaId())));
        } else {
            entity.setCategoria(null);
        }

        if (dto.getFabricanteId() != null) {
            entity.setFabricante(fabricanteRepository.findById(dto.getFabricanteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Fabricante não encontrado com ID: " + dto.getFabricanteId())));
        } else {
            entity.setFabricante(null);
        }
    }
}