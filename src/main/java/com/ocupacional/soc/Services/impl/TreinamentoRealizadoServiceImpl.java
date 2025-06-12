package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoRealizadoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoParticipante;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoRealizadoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Treinamento.TreinamentoRealizadoMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Treinamento.TreinamentoCatalogoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Treinamento.TreinamentoRealizadoRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.Treinamento.TreinamentoRealizadoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreinamentoRealizadoServiceImpl implements TreinamentoRealizadoService {

    private final TreinamentoRealizadoRepository repository;
    private final TreinamentoRealizadoMapper treinamentoRealizadoMapper;
    private final EmpresaRepository empresaRepository;
    private final TreinamentoCatalogoRepository catalogoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PrestadorServicoRepository prestadorServicoRepository;

    @Override
    public Page<TreinamentoRealizadoResponseDTO> findAll(Pageable pageable, Long empresaId, Long unidadeId) {
        return repository.findWithFilters(empresaId, unidadeId, pageable).map(treinamentoRealizadoMapper::toDto);
    }

    @Override
    public TreinamentoRealizadoResponseDTO getById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TreinamentoRealizado não encontrado"));
        return treinamentoRealizadoMapper.toDto(entity);
    }

    @Override
    @Transactional
    public TreinamentoRealizadoResponseDTO create(TreinamentoRealizadoRequestDTO dto) {
        TreinamentoRealizadoEntity entity = new TreinamentoRealizadoEntity();
        mapDtoToEntity(entity, dto);
        return treinamentoRealizadoMapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public TreinamentoRealizadoResponseDTO update(Long id, TreinamentoRealizadoRequestDTO dto) {
        TreinamentoRealizadoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treinamento Realizado não encontrado: " + id));
        mapDtoToEntity(entity, dto);
        return treinamentoRealizadoMapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Treinamento Realizado não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private void mapDtoToEntity(TreinamentoRealizadoEntity entity, TreinamentoRealizadoRequestDTO dto) {
        entity.setEmpresa(empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada")));
        entity.setTreinamentoCatalogo(catalogoRepository.findById(dto.getTreinamentoCatalogoId()).orElseThrow(() -> new ResourceNotFoundException("Treinamento (Catálogo) não encontrado")));
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataTermino(dto.getDataTermino());
        entity.setDataValidade(dto.getDataValidade());
        entity.setObservacoes(dto.getObservacoes());

        if (dto.getResponsaveisIds() != null) {
            entity.setResponsaveis(new HashSet<>(prestadorServicoRepository.findAllById(dto.getResponsaveisIds())));
        }

        entity.getParticipantes().clear();
        if (dto.getParticipantes() != null) {
            Set<TreinamentoParticipante> participantes = dto.getParticipantes().stream().map(pDto -> {
                TreinamentoParticipante participante = new TreinamentoParticipante();
                participante.setTreinamentoRealizado(entity);
                participante.setFuncionario(funcionarioRepository.findById(pDto.getFuncionarioId()).orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado")));
                participante.setConcluiu(pDto.isConcluiu());
                participante.setFezTreinamentoAnterior(pDto.isFezTreinamentoAnterior());
                return participante;
            }).collect(Collectors.toSet());
            entity.getParticipantes().addAll(participantes);
        }
    }
}