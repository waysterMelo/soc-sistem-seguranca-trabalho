package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
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
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class UnidadeOperacionalServiceImpl implements UnidadeOperacionalService {

    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final EmpresaRepository empresaRepository;
    private final CnaeRepository cnaeRepository;
    private final SetorRepository setorRepository;
    private final UnidadeOperacionalMapper unidadeOperacionalMapper;
    private final EnderecoMapper enderecoMapper;

    public UnidadeOperacionalServiceImpl(
            UnidadeOperacionalRepository unidadeOperacionalRepository,
            EmpresaRepository empresaRepository,
            CnaeRepository cnaeRepository,
            SetorRepository setorRepository,
            UnidadeOperacionalMapper unidadeOperacionalMapper,
            EnderecoMapper enderecoMapper) {
        this.unidadeOperacionalRepository = unidadeOperacionalRepository;
        this.empresaRepository = empresaRepository;
        this.cnaeRepository = cnaeRepository;
        this.setorRepository = setorRepository;
        this.unidadeOperacionalMapper = unidadeOperacionalMapper;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    @Transactional
    public UnidadeOperacionalResponseDTO criar(Long empresaId, UnidadeOperacionalRequestDTO dto) {
        EmpresaEntity empresa = findEmpresaById(empresaId);
        UnidadeOperacionalEntity unidadeEntity = unidadeOperacionalMapper.toEntity(dto);
        unidadeEntity.setEmpresa(empresa);

        unidadeEntity.setEndereco(formatarEnderecoParaString(dto, empresa));

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
        unidadeEntity.setEndereco(formatarEnderecoParaString(dto, unidadeEntity.getEmpresa()));

        carregarRelacionamentos(unidadeEntity, dto);
        validarConfiguracaoEmpresaTerceira(unidadeEntity);

        UnidadeOperacionalEntity updatedUnidade = unidadeOperacionalRepository.save(unidadeEntity);
        return unidadeOperacionalMapper.toResponseDto(updatedUnidade);
    }

    private String formatarEnderecoParaString(UnidadeOperacionalRequestDTO dto, EmpresaEntity empresaContexto) {
        if (dto.isUsarEnderecoEmpresa()) {
            if (empresaContexto.getEndereco() == null) {
                throw new IllegalArgumentException("A empresa selecionada não possui endereço cadastrado para cópia.");
            }
            EnderecoDto enderecoDtoDaEmpresa = enderecoMapper.toDto(empresaContexto.getEndereco());
            return construirStringEnderecoFormatado(enderecoDtoDaEmpresa);
        } else if (dto.getEndereco() != null) {
            return construirStringEnderecoFormatado(dto.getEndereco());
        }
        return null;
    }

    private String construirStringEnderecoFormatado(EnderecoDto dto) {
        if (dto == null) {
            return null;
        }
        List<String> parts = new ArrayList<>();
        if (StringUtils.hasText(dto.getLogradouro())) parts.add(dto.getLogradouro());
        if (StringUtils.hasText(dto.getNumero())) parts.add(dto.getNumero());
        if (StringUtils.hasText(dto.getComplemento())) parts.add(dto.getComplemento());

        String primeiraLinha = String.join(", ", parts.stream().filter(StringUtils::hasText).collect(Collectors.toList()));
        parts.clear();

        if (StringUtils.hasText(dto.getBairro())) parts.add(dto.getBairro());

        String cidadeEstado = "";
        if (StringUtils.hasText(dto.getCidade()) && StringUtils.hasText(dto.getEstado())) {
            cidadeEstado = dto.getCidade() + "/" + dto.getEstado();
        } else if (StringUtils.hasText(dto.getCidade())) {
            cidadeEstado = dto.getCidade();
        } else if (StringUtils.hasText(dto.getEstado())) {
            cidadeEstado = dto.getEstado();
        }
        if(StringUtils.hasText(cidadeEstado)) parts.add(cidadeEstado);

        String segundaLinha = String.join(" - ", parts.stream().filter(StringUtils::hasText).collect(Collectors.toList()));
        parts.clear();

        String cepFormatado = "";
        if (StringUtils.hasText(dto.getCep())) cepFormatado = "CEP: " + dto.getCep();

        StringBuilder enderecoCompleto = new StringBuilder();
        if(StringUtils.hasText(primeiraLinha)) enderecoCompleto.append(primeiraLinha);
        if(StringUtils.hasText(segundaLinha)) {
            if(enderecoCompleto.length() > 0) enderecoCompleto.append(" - ");
            enderecoCompleto.append(segundaLinha);
        }
        if(StringUtils.hasText(cepFormatado)) {
            if(enderecoCompleto.length() > 0) enderecoCompleto.append(" - ");
            enderecoCompleto.append(cepFormatado);
        }
        if (StringUtils.hasText(dto.getRegiao())) {
            if(enderecoCompleto.length() > 0) enderecoCompleto.append(" (Região: ").append(dto.getRegiao()).append(")");
            else enderecoCompleto.append("Região: ").append(dto.getRegiao());
        }

        return enderecoCompleto.length() > 0 ? enderecoCompleto.toString() : null;
    }


    private void carregarRelacionamentos(UnidadeOperacionalEntity unidadeEntity, UnidadeOperacionalRequestDTO dto) {
        if (dto.getCnaePrincipalId() != null) {
            unidadeEntity.setCnaePrincipal(findCnaeById(dto.getCnaePrincipalId(), "CNAE Principal"));
        } else {
            unidadeEntity.setCnaePrincipal(null);
        }

        if (!CollectionUtils.isEmpty(dto.getSetoresIds())) {
            List<SetorEntity> setores = findEntidadesByIds(setorRepository, dto.getSetoresIds(), "Setores");
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

    private CnaeEntity findCnaeById(Long id, String cnaeTypeDescription) {
        return cnaeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(cnaeTypeDescription + " não encontrado com ID: " + id));
    }

    private <T, ID> List<T> findEntidadesByIds(JpaRepository<T, ID> repository, List<ID> ids, String entityDescription) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<T> entidades = repository.findAllById(ids);
        if (entidades.size() != ids.size()) {
            throw new EntityNotFoundException("Um ou mais " + entityDescription + " não foram encontrados. IDs fornecidos: " + ids);
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
}
