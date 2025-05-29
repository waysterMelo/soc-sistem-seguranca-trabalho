package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Entities.RiscoCatalogoEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.RiscoCatalogoMapper;
import com.ocupacional.soc.Repositories.RiscoCatalogoRepository;
import com.ocupacional.soc.Services.RiscoCatalogoService;
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
        log.info("Criando novo risco no catálogo: {}", requestDTO.getDescricao());
        // Adicionar validação para evitar duplicidade se necessário
        // riscoCatalogoRepository.findByGrupoAndDescricao(requestDTO.getGrupo(), requestDTO.getDescricao()).ifPresent(r -> {
        // throw new IllegalArgumentException("Risco com esta descrição já existe neste grupo.");
        // });
        RiscoCatalogoEntity entity = riscoCatalogoMapper.requestDtoToEntity(requestDTO);
        RiscoCatalogoEntity savedEntity = riscoCatalogoRepository.save(entity);
        return riscoCatalogoMapper.toResponseDTO(savedEntity);
    }

    @Override
    public RiscoCatalogoResponseDTO buscarRiscoPorId(Long id) {
        log.debug("Buscando risco no catálogo por ID: {}", id);
        RiscoCatalogoEntity entity = riscoCatalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Risco (Catálogo) não encontrado com ID: " + id));
        return riscoCatalogoMapper.toResponseDTO(entity);
    }

    @Override
    public Page<RiscoCatalogoResponseDTO> listarRiscos(Pageable pageable, GrupoRisco grupo) {
        log.debug("Listando riscos do catálogo. Página: {}, Tamanho: {}, Grupo: {}", pageable.getPageNumber(), pageable.getPageSize(), grupo);
        Page<RiscoCatalogoEntity> pageEntity;
        if (grupo != null) {
            // Implementar busca paginada por grupo se o JpaRepository não suportar diretamente
            // ou buscar todos e paginar na mão (menos eficiente para grandes datasets)
            // Para simplificar, vamos buscar todos do grupo e paginar na aplicação (não ideal para produção com muitos dados)
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
                .orElseThrow(() -> new ResourceNotFoundException("Risco (Catálogo) não encontrado com ID: " + id));

        // Adicionar validação para evitar duplicidade se o nome/grupo mudar, se necessário

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
            throw new ResourceNotFoundException("Risco (Catálogo) não encontrado com ID: " + id);
        }
        // Adicionar verificação se o risco está em uso antes de deletar, se necessário
        // Ex: if (riscoTrabalhistaPgrRepository.existsByRiscoCatalogoId(id)) {
        // throw new IllegalStateException("Este risco está vinculado a uma ou mais funções e não pode ser deletado.");
        // }
        riscoCatalogoRepository.deleteById(id);
        log.info("Risco ID: {} deletado do catálogo.", id);
    }
}