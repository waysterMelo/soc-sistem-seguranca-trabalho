package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.RiscoCatalogoMapper;
import com.ocupacional.soc.Repositories.Cadastros.RiscoCatalogoRepository;
import com.ocupacional.soc.Services.Cadastros.RiscoCatalogoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiscoCatalogoServiceImpl implements RiscoCatalogoService {

    private final RiscoCatalogoRepository riscoCatalogoRepository;
    private final RiscoCatalogoMapper riscoCatalogoMapper;

    @Override
    @Transactional
    public RiscoCatalogoResponseDTO criarRisco(RiscoCatalogoRequestDTO requestDTO) {
        RiscoCatalogoEntity entity = riscoCatalogoMapper.requestDtoToEntity(requestDTO);
        RiscoCatalogoEntity savedEntity = riscoCatalogoRepository.save(entity);
        return riscoCatalogoMapper.toResponseDTO(savedEntity);
    }

    @Override
    public RiscoCatalogoResponseDTO buscarRiscoPorId(Long id) {
        log.debug("Buscando risco no catálogo por ID: {}", id);
        RiscoCatalogoEntity entity = riscoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Risco (Catálogo) não encontrado com ID: \{id}"));
        return riscoCatalogoMapper.toResponseDTO(entity);
    }

    @Override
    public Page<RiscoCatalogoResponseDTO> listarRiscos(Pageable pageable, GrupoRisco grupo) {
        log.debug("Listando riscos do catálogo. Página: {}, Tamanho: {}, Grupo: {}", pageable.getPageNumber(), pageable.getPageSize(), grupo);
        Page<RiscoCatalogoEntity> pageEntity;
        if (grupo != null) {
            List<RiscoCatalogoEntity> listByGrupo = riscoCatalogoRepository.findByGrupo(grupo);
            List<RiscoCatalogoResponseDTO> dtoList = riscoCatalogoMapper.toResponseDTOList(listByGrupo);

            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), dtoList.size());
            if (start > dtoList.size()) { // Evita erro se a página pedida for além do disponível
                return new PageImpl<>(List.of(), pageable, dtoList.size());
            }
            return new PageImpl<>(dtoList.subList(start, end), pageable, dtoList.size());
        } else {
            pageEntity = riscoCatalogoRepository.findAll(pageable);
        }
        return pageEntity.map(riscoCatalogoMapper::toResponseDTO);
    }

    @Override
    public List<RiscoCatalogoResponseDTO> listarTodosRiscos(GrupoRisco grupo) {
        log.debug("Listando todos os riscos do catálogo. Grupo: {}", grupo);
        List<RiscoCatalogoEntity> entities;
        if (grupo != null) {
            entities = riscoCatalogoRepository.findByGrupo(grupo);
        } else {
            entities = riscoCatalogoRepository.findAll();
        }
        return riscoCatalogoMapper.toResponseDTOList(entities);
    }

    @Override
    @Transactional
    public RiscoCatalogoResponseDTO atualizarRisco(Long id, RiscoCatalogoRequestDTO requestDTO) {
        log.info("Atualizando risco no catálogo ID: {}. Nova descrição: {}", id, requestDTO.getDescricao());
        RiscoCatalogoEntity entity = riscoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Risco (Catálogo) não encontrado com ID: \{id}"));

        entity.setGrupo(requestDTO.getGrupo());
        entity.setDescricao(requestDTO.getDescricao());
        RiscoCatalogoEntity updatedEntity = riscoCatalogoRepository.save(entity);
        return riscoCatalogoMapper.toResponseDTO(updatedEntity);
    }

    @Override
    @Transactional
    public void deletarRisco(Long id) {
        log.info("Deletando risco no catálogo ID: {}", id);
        if (!riscoCatalogoRepository.existsById(id)) {
            throw new ResourceNotFoundException(STR."Risco (Catálogo) não encontrado com ID: \{id}");
        }
        riscoCatalogoRepository.deleteById(id);
        log.info("Risco ID: {} deletado do catálogo.", id);
    }

    @Override
    @Transactional
    public Page<RiscoCatalogoResponseDTO> listarRiscosPorDescricao(String descricao, Pageable pageable) {
        Page<RiscoCatalogoEntity> riscos = riscoCatalogoRepository.findByDescricao(descricao, pageable);
        return riscos.map(riscoCatalogoMapper::toResponseDTO);
    }

}