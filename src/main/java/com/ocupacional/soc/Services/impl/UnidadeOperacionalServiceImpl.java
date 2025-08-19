package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.*;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Repositories.Cadastros.CnaeRepository;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.SetorRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Services.Cadastros.UnidadeOperacionalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeOperacionalServiceImpl implements UnidadeOperacionalService {

    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final EmpresaRepository empresaRepository;
    private final CnaeRepository cnaeRepository;
    private final SetorRepository setorRepository;
    private final UnidadeOperacionalMapper unidadeOperacionalMapper;

    public UnidadeOperacionalServiceImpl(
            UnidadeOperacionalRepository unidadeOperacionalRepository,
            EmpresaRepository empresaRepository,
            CnaeRepository cnaeRepository,
            SetorRepository setorRepository,
            UnidadeOperacionalMapper unidadeOperacionalMapper) {
        this.unidadeOperacionalRepository = unidadeOperacionalRepository;
        this.empresaRepository = empresaRepository;
        this.cnaeRepository = cnaeRepository;
        this.setorRepository = setorRepository;
        this.unidadeOperacionalMapper = unidadeOperacionalMapper;
    }

    @Override
    @Transactional
    public UnidadeOperacionalResponseDTO criar(Long empresaId, UnidadeOperacionalRequestDTO dto) {
        EmpresaEntity empresa = findEmpresaById(empresaId);
        UnidadeOperacionalEntity unidadeEntity = unidadeOperacionalMapper.toEntity(dto);
        unidadeEntity.setEmpresa(empresa);

        carregarRelacionamentos(unidadeEntity, dto);

        UnidadeOperacionalEntity savedUnidade = unidadeOperacionalRepository.save(unidadeEntity);
        return unidadeOperacionalMapper.toResponseDto(savedUnidade);
    }

    @Override
    @Transactional
    public UnidadeOperacionalResponseDTO atualizar(Long unidadeId, UnidadeOperacionalRequestDTO dto) {
        UnidadeOperacionalEntity unidadeEntity = findUnidadeOperacionalById(unidadeId);

        if (dto.getEmpresaId() != null && !unidadeEntity.getEmpresa().getId().equals(dto.getEmpresaId())) {
            throw new IllegalArgumentException("Não é permitido alterar a empresa associada à unidade operacional por este método.");
        }

        // Atualiza os campos simples da entidade
        unidadeOperacionalMapper.updateEntityFromDto(dto, unidadeEntity);

        // --- INÍCIO DA CORREÇÃO ---
        // Apenas modifica os relacionamentos se o campo setoresIds foi enviado no DTO.
        // Se for nulo, significa que o usuário não quis alterar os setores.
        if (dto.getSetoresIds() != null) {
            carregarRelacionamentos(unidadeEntity, dto);
        } else {
            // Se cnaePrincipalId também não for editável nesta tela separadamente,
            // você pode mover a lógica do Cnae para dentro do 'if' também.
            // Por enquanto, vamos focar apenas nos setores.
            if (dto.getCnaePrincipalId() != null) {
                unidadeEntity.setCnaePrincipal(findCnaeById(dto.getCnaePrincipalId()));
            } else {
                unidadeEntity.setCnaePrincipal(null);
            }
        }
        // --- FIM DA CORREÇÃO ---

        UnidadeOperacionalEntity updatedUnidade = unidadeOperacionalRepository.save(unidadeEntity);
        return unidadeOperacionalMapper.toResponseDto(updatedUnidade);
    }

    // O método carregarRelacionamentos pode ser simplificado se você mover a lógica do Cnae
// para dentro do 'if' acima. Por ora, vamos mantê-lo para a criação.
    private void carregarRelacionamentos(UnidadeOperacionalEntity unidadeEntity, UnidadeOperacionalRequestDTO dto) {
        if (dto.getCnaePrincipalId() != null) {
            unidadeEntity.setCnaePrincipal(findCnaeById(dto.getCnaePrincipalId()));
        } else {
            unidadeEntity.setCnaePrincipal(null);
        }

        // Esta parte agora só será chamada se dto.getSetoresIds() não for nulo.
        if (!CollectionUtils.isEmpty(dto.getSetoresIds())) {
            List<SetorEntity> setores = setorRepository.findAllById(dto.getSetoresIds());
            if (setores.size() != dto.getSetoresIds().size()) {
                throw new EntityNotFoundException("Um ou mais Setores não foram encontrados.");
            }
            unidadeEntity.setSetores(setores);
        } else {
            unidadeEntity.setSetores(Collections.emptyList());
        }
    }

    private EmpresaEntity findEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(STR."Empresa não encontrada com ID: \{id}"));
    }

    private UnidadeOperacionalEntity findUnidadeOperacionalById(Long id) {
        return unidadeOperacionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(STR."Unidade Operacional não encontrada com ID: \{id}"));
    }

    private CnaeEntity findCnaeById(Long id) {
        return cnaeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(STR."CNAE Principal não encontrado com ID: \{id}"));
    }

    private <T, ID> List<T> findEntidadesByIds(JpaRepository<T, ID> repository, List<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<T> entidades = repository.findAllById(ids);
        if (entidades.size() != ids.size()) {
            throw new EntityNotFoundException(STR."Um ou mais Setores não foram encontrados. IDs fornecidos: \{ids}");
        }
        return entidades;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnidadeOperacionalResponseDTO> buscarPorId(Long unidadeId) {
        return unidadeOperacionalRepository.findById(unidadeId)
                .map(unidadeOperacionalMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UnidadeOperacionalResponseDTO> listarPorEmpresaId(Long empresaId) {
        if (!empresaRepository.existsById(empresaId)) {
            throw new EntityNotFoundException(STR."Empresa não encontrada com ID: \{empresaId} ao listar unidades operacionais.");
        }
        return unidadeOperacionalRepository.findByEmpresaId(empresaId).stream()
                .map(unidadeOperacionalMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletar(Long unidadeId) {
        UnidadeOperacionalEntity unidade = findUnidadeOperacionalById(unidadeId);
        unidadeOperacionalRepository.delete(unidade);
    }

    @Override
    public Long calcularTotalSetores(Long unidadeId) {
        UnidadeOperacionalEntity unidade = unidadeOperacionalRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException(STR."Unidade Operacional não encontrada com ID: \{unidadeId}"));

        return unidade.getSetores() != null ? (long) unidade.getSetores().size() : 0L;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnidadeOperacionalResponseDTO> listarTodos(Pageable pageable) {
        Page<UnidadeOperacionalEntity> entidades = unidadeOperacionalRepository.findAll(pageable);
        return entidades.map(unidadeOperacionalMapper::toResponseDto);
    }

    @Override
    public Page<UnidadeOperacionalResponseDTO> filtrarPorNome(String nome, Pageable pageable) {
            Page<UnidadeOperacionalEntity> entidades = unidadeOperacionalRepository
                    .findByNomeContainingIgnoreCase(nome, pageable);
            return entidades.map(unidadeOperacionalMapper::toResponseDto);
    }
}
