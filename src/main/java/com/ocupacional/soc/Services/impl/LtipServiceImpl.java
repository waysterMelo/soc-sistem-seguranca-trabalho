package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtipEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.LtipMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Repositories.BibliografiaRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.LtipRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Nr16AnexoRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.LtipService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class LtipServiceImpl implements LtipService {

    private final LtipRepository ltipRepository;
    private final LtipMapper ltipMapper;
    private final FuncaoRepository funcaoRepository;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final Nr16AnexoRepository nr16AnexoRepository;
    private final BibliografiaRepository bibliografiaRepository;
    private final AparelhoRepository aparelhoRepository;


    @Override
    public Page<LtipResponseDTO> findAllLtips(Pageable pageable, Long empresaId, Long setorId, Long funcaoId) {

        return ltipRepository.findWithFilters(empresaId, setorId, funcaoId, pageable)
                .map(ltipMapper::toDto);
    }

    @Override
    @Transactional
    public LtipResponseDTO createLtip(LtipRequestDTO dto) {
        LtipEntity ltipEntity = new LtipEntity();
        buildLtipEntity(ltipEntity, dto);
        LtipEntity savedEntity = ltipRepository.save(ltipEntity);
        return ltipMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public LtipResponseDTO updateLtip(Long id, LtipRequestDTO dto) {
        LtipEntity ltipEntity = ltipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LTIP não encontrado com ID: " + id));
        buildLtipEntity(ltipEntity, dto);
        LtipEntity updatedEntity = ltipRepository.save(ltipEntity);
        return ltipMapper.toDto(updatedEntity);
    }

    @Override
    public LtipResponseDTO findLtipById(Long id) {
        return ltipRepository.findById(id)
                .map(ltipMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("LTIP não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public void deleteLtip(Long id) {
        if (!ltipRepository.existsById(id)) {
            throw new ResourceNotFoundException("LTIP não encontrado com ID: " + id);
        }
        ltipRepository.deleteById(id);
    }
    private void buildLtipEntity(LtipEntity entity, LtipRequestDTO dto) {
        // Associações
        entity.setFuncao(funcaoRepository.findById(dto.getFuncaoId()).orElseThrow(() -> new ResourceNotFoundException("Função não encontrada com ID: " + dto.getFuncaoId())));

        if (dto.getResponsavelTecnicoId() != null) {
            entity.setResponsavelTecnico(prestadorServicoRepository.findById(dto.getResponsavelTecnicoId()).orElseThrow(() -> new ResourceNotFoundException("Responsável Técnico não encontrado com ID: " + dto.getResponsavelTecnicoId())));
        } else {
            entity.setResponsavelTecnico(null);
        }

        if (dto.getDemaisElaboradoresIds() != null) entity.setDemaisElaboradores(new HashSet<>(prestadorServicoRepository.findAllById(dto.getDemaisElaboradoresIds())));
        if (dto.getAtividadesPericulosasAnexosIds() != null) entity.setAtividadesPericulosasAnexos(new HashSet<>(nr16AnexoRepository.findAllById(dto.getAtividadesPericulosasAnexosIds())));
        if (dto.getBibliografiasIds() != null) entity.setBibliografias(new HashSet<>(bibliografiaRepository.findAllById(dto.getBibliografiasIds())));
        if (dto.getAparelhosIds() != null) entity.setAparelhos(new HashSet<>(aparelhoRepository.findAllById(dto.getAparelhosIds())));

        // Mapeamento de campos diretos
        entity.setDataLevantamento(dto.getDataLevantamento());
        entity.setHoraInicial(dto.getHoraInicial());
        entity.setHoraFinal(dto.getHoraFinal());
        entity.setResponsavelEmpresa(dto.getResponsavelEmpresa());
        entity.setInicioValidade(dto.getInicioValidade());
        entity.setProximaRevisao(dto.getProximaRevisao());
        entity.setAlertaValidadeDias(dto.getAlertaValidadeDias());
        entity.setConteudoCapa(dto.getConteudoCapa());
        entity.setIntroducao(dto.getIntroducao());
        entity.setObjetivo(dto.getObjetivo());
        entity.setDefinicoes(dto.getDefinicoes());
        entity.setMetodologia(dto.getMetodologia());
        entity.setDescritivoAtividades(dto.getDescritivoAtividades());
        entity.setIdentificacaoLocal(dto.getIdentificacaoLocal());
        entity.setConclusao(dto.getConclusao());
        entity.setPlanejamentoAnual(dto.getPlanejamentoAnual());
        entity.setAvaliacaoAtividadesPericulosas(dto.getAvaliacaoAtividadesPericulosas());
        entity.setAtividadesNaoInsalubres(dto.isAtividadesNaoInsalubres());
    }
}