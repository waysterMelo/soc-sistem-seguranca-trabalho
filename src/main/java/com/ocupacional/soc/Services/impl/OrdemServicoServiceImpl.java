package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.OrdemServicoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.OrdemServico.OrdemServicoMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.OrdemServico.OrdemServicoRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.OrdemServico.OrdemServicoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class OrdemServicoServiceImpl implements OrdemServicoService {

    private static final String REVISAO_INICIAL_TEXTO = "Documento nao revisado";
    private final OrdemServicoRepository repository;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncaoRepository funcaoRepository;
    private final AparelhoRepository aparelhoRepository;
    private final OrdemServicoMapper ordemServicoMapper;

    @Override
    @Transactional
    public Page<OrdemServicoListDTO> findAll(Pageable pageable, Long funcionarioId, String search) {
        Page<OrdemServicoEntity> page = repository.findWithFilters(funcionarioId, search, pageable);
        return page.map(ordemServicoMapper::toListDto);
    }

    @Override
    @Transactional
    public OrdemServicoResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(ordemServicoMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com ID: " + id));
    }

    @Override
    @Transactional
    public OrdemServicoResponseDTO create(OrdemServicoRequestDTO dto) {
        OrdemServicoEntity entity = new OrdemServicoEntity();

        mapDtoToEntity(entity, dto);
        entity.setRevisao(REVISAO_INICIAL_TEXTO); // Lógica da revisão na criação

        OrdemServicoEntity savedEntity = repository.save(entity);
        return ordemServicoMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional
    public OrdemServicoResponseDTO update(Long id, OrdemServicoRequestDTO dto) {
        OrdemServicoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com ID: " + id));

        mapDtoToEntity(entity, dto);

        // Lógica de atualização da revisão
        if (REVISAO_INICIAL_TEXTO.equals(entity.getRevisao())) {
            entity.setRevisao("1");
        } else {
            try {
                int currentRevision = Integer.parseInt(entity.getRevisao());
                entity.setRevisao(String.valueOf(currentRevision + 1));
            } catch (NumberFormatException e) {
                entity.setRevisao("1"); // Se o valor for inválido, reinicia em 1
            }
        }

        OrdemServicoEntity updatedEntity = repository.save(entity);
        return ordemServicoMapper.toResponseDto(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ordem de Serviço não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    private void mapDtoToEntity(OrdemServicoEntity entity, OrdemServicoRequestDTO dto) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + dto.getFuncionarioId()));

        FuncaoEntity funcao = funcaoRepository.findById(dto.getFuncaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada com ID: " + dto.getFuncaoId()));

        entity.setFuncionario(funcionario);
        entity.setFuncao(funcao);
        entity.setDataElaboracao(dto.getDataElaboracao());
        entity.setDescricaoOrdemServico(dto.getDescricaoOrdemServico());
        entity.setExibirDescricaoSetor(dto.isExibirDescricaoSetor());
        entity.setInformacoesPreventivas(dto.getInformacoesPreventivas());
        entity.setRecomendacoes(dto.getRecomendacoes());
        entity.setObservacoes(dto.getObservacoes());

        // Copia a descrição da função para a OS (apenas na criação)
        if (entity.getId() == null) {
            entity.setDescricaoFuncao(funcao.getDescricao());
        }

        if (!CollectionUtils.isEmpty(dto.getEquipamentosAdicionaisIds())) {
            entity.setEquipamentosAdicionais(new HashSet<>(aparelhoRepository.findAllById(dto.getEquipamentosAdicionaisIds())));
        } else {
            entity.getEquipamentosAdicionais().clear();
        }
    }
}