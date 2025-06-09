package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.*;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatAgenteNocivoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.LtcatMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Repositories.BibliografiaRepository;
import com.ocupacional.soc.Repositories.Cadastros.*;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.LtcatRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.LtcatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LtcatServiceImpl implements LtcatService {

    private final LtcatRepository ltcatRepository;
    private final LtcatMapper ltcatMapper;
    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final ProfissionalRegistroAmbientalRepository profissionalRegistroAmbientalRepository;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final AparelhoRepository aparelhoRepository;
    private final BibliografiaRepository bibliografiaRepository;
    private final FuncaoRepository funcaoRepository;
    private final EmpresaRepository empresaRepository;
    private final AgenteNocivoCatalogoRepository agenteNocivoCatalogoRepository;

    @Override
    @Transactional
    public LtcatResponseDTO createLtcat(LtcatRequestDTO dto) {
        LtcatEntity ltcatEntity = buildLtcatEntity(new LtcatEntity(), dto);
        return ltcatMapper.toDto(ltcatRepository.save(ltcatEntity));
    }

    @Override
    public LtcatResponseDTO getLtcatById(Long id) {
        return ltcatRepository.findById(id)
                .map(ltcatMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("LTCAT não encontrado com ID: " + id));
    }

    @Override
    public Page<LtcatResponseDTO> getAllLtcats(Pageable pageable) {
        return ltcatRepository.findAll(pageable).map(ltcatMapper::toDto);
    }

    @Override
    @Transactional
    public LtcatResponseDTO updateLtcat(Long id, LtcatRequestDTO dto) {
        LtcatEntity ltcatEntity = ltcatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LTCAT não encontrado com ID: " + id));

        ltcatEntity = buildLtcatEntity(ltcatEntity, dto);
        return ltcatMapper.toDto(ltcatRepository.save(ltcatEntity));
    }

    @Override
    @Transactional
    public void deleteLtcat(Long id) {
        if (!ltcatRepository.existsById(id)) {
            throw new ResourceNotFoundException("LTCAT não encontrado com ID: " + id);
        }
        ltcatRepository.deleteById(id);
    }

    private LtcatEntity buildLtcatEntity(LtcatEntity entity, LtcatRequestDTO dto) {
        entity.setUnidadeOperacional(unidadeOperacionalRepository.findById(dto.getUnidadeOperacionalId()).orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada.")));
        entity.setDataDocumento(dto.getDataDocumento());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setAlertaValidadeDias(dto.getAlertaValidadeDias());
        entity.setSituacao(dto.getSituacao());
        entity.setComentariosInternos(dto.getComentariosInternos());
        entity.setCondicoesPreliminares(dto.getCondicoesPreliminares());
        entity.setConteudoCapa(dto.getConteudoCapa());
        entity.setLaudoResponsabilidadeTecnica(dto.getLaudoResponsabilidadeTecnica());
        entity.setLaudoIntroducao(dto.getLaudoIntroducao());
        entity.setLaudoObjetivos(dto.getLaudoObjetivos());
        entity.setLaudoConsideracoesGerais(dto.getLaudoConsideracoesGerais());
        entity.setLaudoCriteriosAvaliacao(dto.getLaudoCriteriosAvaliacao());
        entity.setRecomendacoesTecnicas(dto.getRecomendacoesTecnicas());
        entity.setConclusao(dto.getConclusao());
        entity.setPlanejamentoAnual(dto.getPlanejamentoAnual());

        if(dto.getProfissionaisAmbientaisIds() != null) entity.setProfissionaisAmbientais(new HashSet<>(profissionalRegistroAmbientalRepository.findAllById(dto.getProfissionaisAmbientaisIds())));
        if(dto.getPrestadoresServicoIds() != null) entity.setPrestadoresServico(new HashSet<>(prestadorServicoRepository.findAllById(dto.getPrestadoresServicoIds())));
        if(dto.getAparelhosIds() != null) entity.setAparelhos(new HashSet<>(aparelhoRepository.findAllById(dto.getAparelhosIds())));
        if(dto.getBibliografiasIds() != null) entity.setBibliografias(new HashSet<>(bibliografiaRepository.findAllById(dto.getBibliografiasIds())));
        if(dto.getFuncoesIds() != null) entity.setFuncoes(new HashSet<>(funcaoRepository.findAllById(dto.getFuncoesIds())));
        if(dto.getEmpresasContratantesIds() != null) entity.setEmpresasContratantes(new HashSet<>(empresaRepository.findAllById(dto.getEmpresasContratantesIds())));

        entity.getAgentesNocivos().clear();
        if (dto.getAgentesNocivos() != null) {
            List<LtcatAgenteNocivoEntity> novosAgentes = dto.getAgentesNocivos().stream().map(agenteDto -> {
                AgenteNocivoCatalogoEntity an = agenteNocivoCatalogoRepository.findById(agenteDto.getAgenteNocivoId()).orElseThrow(() -> new ResourceNotFoundException("Agente Nocivo não encontrado."));
                FuncaoEntity f = funcaoRepository.findById(agenteDto.getFuncaoId()).orElseThrow(() -> new ResourceNotFoundException("Função não encontrada."));
                return LtcatAgenteNocivoEntity.builder().ltcat(entity).agenteNocivo(an).funcao(f).build();
            }).collect(Collectors.toList());
            entity.getAgentesNocivos().addAll(novosAgentes);
        }

        return entity;
    }
}