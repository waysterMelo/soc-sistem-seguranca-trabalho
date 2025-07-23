package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.*;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Repositories.Cadastros.CnaeRepository;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.SetorRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Services.Cadastros.UnidadeOperacionalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        validarConfiguracaoEmpresaTerceira(unidadeEntity);

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

        unidadeOperacionalMapper.updateEntityFromDto(dto, unidadeEntity);
        carregarRelacionamentos(unidadeEntity, dto);
        validarConfiguracaoEmpresaTerceira(unidadeEntity);

        UnidadeOperacionalEntity updatedUnidade = unidadeOperacionalRepository.save(unidadeEntity);
        return unidadeOperacionalMapper.toResponseDto(updatedUnidade);
    }

    private void carregarRelacionamentos(UnidadeOperacionalEntity unidadeEntity, UnidadeOperacionalRequestDTO dto) {
        if (dto.getCnaePrincipalId() != null) {
            unidadeEntity.setCnaePrincipal(findCnaeById(dto.getCnaePrincipalId()));
        } else {
            unidadeEntity.setCnaePrincipal(null);
        }

        if (!CollectionUtils.isEmpty(dto.getSetoresIds())) {
            List<SetorEntity> setores = findEntidadesByIds(setorRepository, dto.getSetoresIds());
            unidadeEntity.setSetores(setores);
        } else {
            unidadeEntity.setSetores(Collections.emptyList());
        }
    }

    private void validarConfiguracaoEmpresaTerceira(UnidadeOperacionalEntity unidadeEntity) {
        if (unidadeEntity.isAlocadaEmEmpresaTerceira()) {
            if (isNullOrBlank(unidadeEntity.getCnpjEmpresaTerceira()) || isNullOrBlank(unidadeEntity.getRazaoSocialEmpresaTerceira())) {
                throw new IllegalArgumentException("CNPJ e Razão Social da empresa terceira são obrigatórios quando a unidade está alocada em empresa terceira.");
            }
        } else {
            unidadeEntity.setCnpjEmpresaTerceira(null);
            unidadeEntity.setRazaoSocialEmpresaTerceira(null);
        }
    }

    private EmpresaEntity findEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + id));
    }

    private UnidadeOperacionalEntity findUnidadeOperacionalById(Long id) {
        return unidadeOperacionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade Operacional não encontrada com ID: " + id));
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
            throw new EntityNotFoundException("Um ou mais " + "Setores" + " não foram encontrados. IDs fornecidos: " + ids);
        }
        return entidades;
    }

    private boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
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
            throw new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId + " ao listar unidades operacionais.");
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
                .orElseThrow(() -> new EntityNotFoundException("Unidade Operacional não encontrada com ID: " + unidadeId));

        return unidade.getSetores() != null ? (long) unidade.getSetores().size() : 0L;
    }

}
