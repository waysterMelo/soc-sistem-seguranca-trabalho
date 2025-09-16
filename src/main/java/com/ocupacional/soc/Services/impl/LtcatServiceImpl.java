package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat.LtcatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat.LtcatResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.AgenteNocivoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatAgenteNocivoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.LtcatEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.LtcatMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Repositories.BibliografiaRepository;
import com.ocupacional.soc.Repositories.Cadastros.*;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.LtcatRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.Ltcat.LtcatFileStorageService;
import com.ocupacional.soc.Services.SegurancaTrabalho.Ltcat.LtcatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LtcatServiceImpl implements LtcatService {

    private final LtcatRepository ltcatRepository;
    private final LtcatMapper ltcatMapper;
    private final UnidadeOperacionalRepository unidadeOperacionalRepository;
    private final ProfissionalRegistrosRepository profissionalRegistrosRepository;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final AparelhoRepository aparelhoRepository;
    private final BibliografiaRepository bibliografiaRepository;
    private final FuncaoRepository funcaoRepository;
    private final EmpresaRepository empresaRepository;
    private final AgenteNocivoCatalogoRepository agenteNocivoCatalogoRepository;
    private final LtcatFileStorageService ltcatFileStorageService;

    @Override
    @Transactional
    public LtcatResponseDTO createLtcat(LtcatRequestDTO dto, MultipartFile imagemCapa) {
       LtcatEntity entity = buildLtcatEntity(new LtcatEntity(), dto);
        if (imagemCapa != null && !imagemCapa.isEmpty()) {
            String fileUrl = ltcatFileStorageService.storeFile(imagemCapa);
            entity.setImagemCapa(fileUrl);
        }
        return ltcatMapper.toDto(ltcatRepository.save(entity));
    }

    @Override
    public LtcatResponseDTO getLtcatById(Long id) {
        return ltcatRepository.findByIdWithDetails(id)
                .map(ltcatMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(STR."LTCAT não encontrado com ID: \{id}"));
    }

    @Override
    public Page<LtcatResponseDTO> getAllLtcats(Pageable pageable) {
        return ltcatRepository.findAll(pageable).map(ltcatMapper::toDto);
    }

    @Override
    @Transactional
    public LtcatResponseDTO updateLtcat(Long id, LtcatRequestDTO dto, MultipartFile imagemCapa) {
        LtcatEntity ltcatEntity = ltcatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LTCAT não encontrado com ID: " + id));

        if (imagemCapa != null && !imagemCapa.isEmpty()) {
            ltcatFileStorageService.deleteFile(ltcatEntity.getImagemCapa()); // Deleta a imagem antiga
            String newFileUrl = ltcatFileStorageService.storeFile(imagemCapa);
            ltcatEntity.setImagemCapa(newFileUrl);
        }

        ltcatEntity = buildLtcatEntity(ltcatEntity, dto);
        return ltcatMapper.toDto(ltcatRepository.save(ltcatEntity));
    }

    @Override
    @Transactional
    public void deleteLtcat(Long id) {
        LtcatEntity ltcatEntity = ltcatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LTCAT não encontrado com ID: " + id));

        ltcatFileStorageService.deleteFile(ltcatEntity.getImagemCapa());
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
        entity.setImagemCapa(dto.getImagemCapa());
        entity.setLaudoResponsabilidadeTecnica(dto.getLaudoResponsabilidadeTecnica());
        entity.setLaudoIntroducao(dto.getLaudoIntroducao());
        entity.setLaudoObjetivos(dto.getLaudoObjetivos());
        entity.setLaudoConsideracoesGerais(dto.getLaudoConsideracoesGerais());
        entity.setLaudoCriteriosAvaliacao(dto.getLaudoCriteriosAvaliacao());
        entity.setRecomendacoesTecnicas(dto.getRecomendacoesTecnicas());
        entity.setConclusao(dto.getConclusao());

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
            }).toList();
            entity.getAgentesNocivos().addAll(novosAgentes);
        }

        return entity;
    }
}