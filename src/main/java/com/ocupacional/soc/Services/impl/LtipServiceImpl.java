package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip.LtipNr16AnexoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip.LtipRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip.LtipResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip.LtipEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip.LtipNr16AnexoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.LtipMapper;
import com.ocupacional.soc.Repositories.Aparelhos.AparelhoRepository;
import com.ocupacional.soc.Repositories.BibliografiaRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.LtipRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Nr16AnexoRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.Ltip.LtipFileStorageService;
import com.ocupacional.soc.Services.SegurancaTrabalho.LtipService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LtipServiceImpl implements LtipService {

    private final LtipRepository ltipRepository;
    private final LtipMapper ltipMapper;
    private final FuncaoRepository funcaoRepository;
    private final PrestadorServicoRepository prestadorServicoRepository;
    private final Nr16AnexoRepository nr16AnexoRepository;
    private final AparelhoRepository aparelhoRepository;
    private final LtipFileStorageService ltipFileStorageService;


    @Override
    public Page<LtipResponseDTO> findAllLtips(Pageable pageable, Long empresaId, Long setorId, Long funcaoId) {

        return ltipRepository.findWithFilters(empresaId, setorId, funcaoId, pageable)
                .map(ltipMapper::toDto);
    }

    @Override
    @Transactional
    public LtipResponseDTO createLtip(LtipRequestDTO dto, MultipartFile imagemCapa) {
        LtipEntity ltipEntity = new LtipEntity();
        buildLtipEntity(ltipEntity, dto);
        if (imagemCapa != null && !imagemCapa.isEmpty()) {
            String fileUrl = ltipFileStorageService.storeFile(imagemCapa);
            ltipEntity.setImagemCapa(fileUrl);
        }
        LtipEntity savedEntity = ltipRepository.save(ltipEntity);
        return ltipMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public LtipResponseDTO updateLtip(Long id, LtipRequestDTO dto, MultipartFile imagemCapa) {
        LtipEntity ltipEntity = ltipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LTIP não encontrado com ID: " + id));
        buildLtipEntity(ltipEntity, dto);
        if (imagemCapa != null && !imagemCapa.isEmpty()) {
            if (ltipEntity.getImagemCapa() != null && !ltipEntity.getImagemCapa().isEmpty()) {
                ltipFileStorageService.deleteFile(ltipEntity.getImagemCapa());
            }
            String newFileUrl = ltipFileStorageService.storeFile(imagemCapa);
            ltipEntity.setImagemCapa(newFileUrl);
        }
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
        LtipEntity ltipEntity = ltipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LTIP não encontrado com ID: " + id));


        if (ltipEntity.getImagemCapa() != null && !ltipEntity.getImagemCapa().isEmpty()) {
            ltipFileStorageService.deleteFile(ltipEntity.getImagemCapa());
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

        if (dto.getDemaisElaboradoresIds() != null) {
            entity.setDemaisElaboradores(new HashSet<>(prestadorServicoRepository.findAllById(dto.getDemaisElaboradoresIds())));
        } else {
            entity.setDemaisElaboradores(new HashSet<>());
        }

        // Atualização inteligente dos anexos
        if (dto.getAtividadesPericulosasAnexos() != null) {
            // Coletar IDs dos anexos enviados no DTO
            var anexosEnviados = dto.getAtividadesPericulosasAnexos().stream()
                    .map(LtipNr16AnexoRequestDTO::getId)
                    .filter(Objects::nonNull)
                    .collect(java.util.stream.Collectors.toSet());

            // Remover anexos não enviados (que não estão no DTO)
            entity.getAtividadesPericulosasAnexos().removeIf(anexoExistente ->
                !anexosEnviados.contains(anexoExistente.getId()));

            // Processar anexos do DTO
            for (var anexoRequest : dto.getAtividadesPericulosasAnexos()) {
                var anexoNr16Optional = nr16AnexoRepository.findById(anexoRequest.getAnexoId());
                if (anexoNr16Optional.isEmpty()) {
                    continue; // Pula anexos inválidos
                }

                if (anexoRequest.getId() != null) {
                    // Atualizar anexo existente
                    var anexoExistente = entity.getAtividadesPericulosasAnexos().stream()
                            .filter(a -> a.getId().equals(anexoRequest.getId()))
                            .findFirst();

                    if (anexoExistente.isPresent()) {
                        anexoExistente.get().setAnexo(anexoNr16Optional.get());
                        anexoExistente.get().setAvaliacao(anexoRequest.getAvaliacao());
                    }
                } else {
                    // Criar novo anexo
                    LtipNr16AnexoEntity novoAnexo = new LtipNr16AnexoEntity();
                    novoAnexo.setLtip(entity);
                    novoAnexo.setAnexo(anexoNr16Optional.get());
                    novoAnexo.setAvaliacao(anexoRequest.getAvaliacao());
                    entity.getAtividadesPericulosasAnexos().add(novoAnexo);
                }
            }
        } else {
            // Se DTO não tem anexos, remove todos
            entity.getAtividadesPericulosasAnexos().clear();
        }
        if (dto.getAparelhosIds() != null) {
            entity.setAparelhos(new HashSet<>(aparelhoRepository.findAllById(dto.getAparelhosIds())));
        } else {
            entity.setAparelhos(new HashSet<>());
        }

        // Mapeamento de campos diretos
        entity.setDataLevantamento(dto.getDataLevantamento());
        entity.setHoraInicial(dto.getHoraInicial());
        entity.setHoraFinal(dto.getHoraFinal());
        entity.setResponsavelEmpresa(dto.getResponsavelEmpresa());
        entity.setInicioValidade(dto.getInicioValidade());
        entity.setProximaRevisao(dto.getProximaRevisao());
        entity.setAlertaValidadeDias(dto.getAlertaValidadeDias());
        entity.setIntroducao(dto.getIntroducao());
        entity.setObjetivo(dto.getObjetivo());
        entity.setDefinicoes(dto.getDefinicoes());
        entity.setDescritivoAtividades(dto.getDescritivoAtividades());
        entity.setIdentificacaoLocal(dto.getIdentificacaoLocal());
        entity.setConclusao(dto.getConclusao());
        entity.setAtividadesNaoInsalubres(dto.isAtividadesNaoInsalubres());
    }
}