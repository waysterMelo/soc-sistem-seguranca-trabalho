package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EnderecoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Cat.CatEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CatMapper;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.*;
import com.ocupacional.soc.Services.SegurancaTrabalho.CatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final CatMapper catMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final ParteCorpoAtingidaRepo parteCorpoAtingidaRepository;
    private final AgenteCausadorRepository agenteCausadorRepository;
    private final NaturezaLesaoRepository naturezaLesaoRepository;
    private final SituacaoGeradoraRepo situacaoGeradoraRepository;
    private final CidRepository cidRepository;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final EnderecoMapper enderecoMapper;

    @Override
    @Transactional
    public CatResponseDTO createCat(CatRequestDTO dto) {
        CatEntity entity = new CatEntity();
        buildCatEntity(entity, dto);
        CatEntity savedEntity = catRepository.save(entity);
        return catMapper.toDto(savedEntity);
    }

    @Override
    public CatResponseDTO findById(Long id) {
        return catRepository.findById(id).map(catMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("CAT não encontrada com ID: " + id));
    }

    @Override
    public List<CatResponseDTO> findAll() {
        return catRepository.findAll().stream().map(catMapper::toDto).collect(Collectors.toList());
    }


    private void buildCatEntity(CatEntity entity, CatRequestDTO dto) {
        // Acidentado
        FuncionarioEntity acidentado = funcionarioRepository.findById(dto.getAcidentadoFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário acidentado não encontrado com ID: " + dto.getAcidentadoFuncionarioId()));
        entity.setAcidentado(acidentado);

        // Mapeamento de campos diretos
        entity.setTipoCat(dto.getTipoCat());
        entity.setIniciativaCat(dto.getIniciativaCat());
        entity.setNumeroReciboCat(dto.getNumeroReciboCat()); // <-- Mapeamento que estava faltando
        entity.setNumeroCatOrigem(dto.getNumeroCatOrigem());
        entity.setDataAcidente(dto.getDataAcidente());
        entity.setHoraAcidente(dto.getHoraAcidente());
        entity.setHorasTrabalhadas(dto.getHorasTrabalhadas());
        entity.setTipoAcidente(dto.getTipoAcidente());
        entity.setHouveAfastamento(dto.getHouveAfastamento());
        entity.setUltimoDiaTrabalhado(dto.getUltimoDiaTrabalhado());
        entity.setTipoLocalAcidente(dto.getTipoLocalAcidente()); // <-- Mapeamento que estava faltando
        entity.setLocalAcidenteEspecificacao(dto.getLocalAcidenteEspecificacao());
        entity.setTipoInscricaoLocalAcidente(dto.getTipoInscricaoLocalAcidente());
        entity.setInscricaoLocalAcidente(dto.getInscricaoLocalAcidente());
        entity.setHouveRegistroPolicial(dto.getHouveRegistroPolicial());
        entity.setHouveObito(dto.getHouveObito());
        entity.setDataObito(dto.getDataObito());
        entity.setObservacoes(dto.getObservacoes());
        entity.setDataAtendimento(dto.getDataAtendimento());
        entity.setHoraAtendimento(dto.getHoraAtendimento());
        entity.setHouveInternacao(dto.getHouveInternacao());
        entity.setDuracaoTratamentoDias(dto.getDuracaoTratamentoDias());
        entity.setProvavelAfastamento(dto.getProvavelAfastamento());
        entity.setDiagnosticoProvavel(dto.getDiagnosticoProvavel());
        entity.setUnidadeAtendimentoCnes(dto.getUnidadeAtendimentoCnes());


        // Mapeamento de relacionamentos
        if (dto.getPartesCorpoAtingidasIds() != null) {
            entity.setPartesCorpoAtingidas(new HashSet<>(parteCorpoAtingidaRepository.findAllById(dto.getPartesCorpoAtingidasIds())));
        }
        if (dto.getAgenteCausadorId() != null) {
            entity.setAgenteCausador(agenteCausadorRepository.findById(dto.getAgenteCausadorId()).orElse(null));
        }
        if (dto.getNaturezaLesaoId() != null) {
            entity.setNaturezaLesao(naturezaLesaoRepository.findById(dto.getNaturezaLesaoId()).orElse(null));
        }
        if (dto.getSituacaoGeradoraId() != null) {
            entity.setSituacaoGeradora(situacaoGeradoraRepository.findById(dto.getSituacaoGeradoraId()).orElse(null));
        }
        if (dto.getCidId() != null) {
            entity.setCid(cidRepository.findById(dto.getCidId()).orElse(null));
        }
        if (dto.getLocalAcidenteEndereco() != null) {
            EnderecoEntity endereco = enderecoMapper.toEntity(dto.getLocalAcidenteEndereco());
            entity.setLocalAcidenteEndereco(endereco);
        }
        if (dto.getAtestadoMedicoId() != null) {
            entity.setAtestadoMedico(prestadorServicoRepository.findById(dto.getAtestadoMedicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profissional do atestado (Prestador de Serviço) não encontrado com ID: " + dto.getAtestadoMedicoId())));
        } else {
            entity.setAtestadoMedico(null);
        }
    }
}