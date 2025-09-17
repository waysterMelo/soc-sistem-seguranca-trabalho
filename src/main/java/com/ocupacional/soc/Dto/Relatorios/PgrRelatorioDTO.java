package com.ocupacional.soc.Dto.Relatorios;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoSimpleResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrMapaRiscoFuncaoResponseDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PlanoAcaoRiscoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Pgr.PgrEntity;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PgrMapaRiscoFuncaoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PlanoAcaoRiscoMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PgrRelatorioDTO {

    private UnidadeOperacionalResponseDTO unidadeOperacional;

    private Long id;
    private String conteudoCapa;
    private String termoValidacao;
    private String documentoBase;
    private String responsavelEmpresa;
    private LocalDate dataDocumento;
    private LocalDate dataRevisao;
    private String termoCiencia;
    private String planoAcaoMetodologia;
    private String planoAcaoOrientacoes;
    private String consideracoesFinais;
    private List<PgrMapaRiscoFuncaoResponseDTO> mapaRiscos;
    private List<PlanoAcaoRiscoResponseDTO> planoAcaoRiscos;

    public PgrRelatorioDTO(PgrEntity entity, UnidadeOperacionalMapper unidadeMapper, PgrMapaRiscoFuncaoMapper mapaRiscoMapper, PlanoAcaoRiscoMapper planoAcaoMapper) {
        this.id = entity.getId();

        this.unidadeOperacional = unidadeMapper.toResponseDto(entity.getUnidadeOperacional());

        // Carrega a imagem da capa, converte para Base64 e monta a Data URI
        if (entity.getConteudoCapa() != null && !entity.getConteudoCapa().isBlank()) {
            try {
                Path imagePath = Paths.get("uploads/pgr-capas").resolve(entity.getConteudoCapa());
                if (Files.exists(imagePath)) {
                    byte[] imageBytes = Files.readAllBytes(imagePath);
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    String filename = entity.getConteudoCapa().toLowerCase();
                    String mimeType = "image/jpeg"; // Default
                    if (filename.endsWith(".png")) {
                        mimeType = "image/png";
                    } else if (filename.endsWith(".gif")) {
                        mimeType = "image/gif";
                    }
                    this.conteudoCapa = "data:" + mimeType + ";base64," + base64Image;
                }
            } catch (IOException e) {
                e.printStackTrace();
                this.conteudoCapa = null; // Garante que seja nulo em caso de erro
            }
        } else {
            this.conteudoCapa = null;
        }

        this.termoValidacao = entity.getTermoValidacao();
        this.documentoBase = entity.getDocumentoBase();
        this.responsavelEmpresa = entity.getResponsavelEmpresa();
        this.dataDocumento = entity.getDataDocumento();
        this.dataRevisao = entity.getDataRevisao();
        this.termoCiencia = entity.getTermoCiencia();
        this.planoAcaoMetodologia = entity.getPlanoAcaoMetodologia() != null ? entity.getPlanoAcaoMetodologia().getValue() : null;
        this.planoAcaoOrientacoes = entity.getPlanoAcaoOrientacoes();
        this.consideracoesFinais = entity.getConsideracoesFinais();

        if (entity.getUnidadeOperacional() != null && entity.getUnidadeOperacional().getSetores() != null) {
            this.mapaRiscos = entity.getUnidadeOperacional().getSetores().stream()
                    .flatMap(setor -> setor.getFuncoes().stream())
                    .map(funcao -> {
                        PgrMapaRiscoFuncaoResponseDTO mapaDto = new PgrMapaRiscoFuncaoResponseDTO();
                        mapaDto.setFuncaoId(funcao.getId());
                        mapaDto.setNomeFuncao(funcao.getNome());

                        if (funcao.getRiscosPGR() != null) {
                            Set<RiscoCatalogoSimpleResponseDTO> riscos = funcao.getRiscosPGR().stream()
                                    .map(riscoPgr -> {
                                        RiscoCatalogoEntity riscoCat = riscoPgr.getRiscoCatalogo();
                                        return new RiscoCatalogoSimpleResponseDTO(riscoCat.getId(), riscoCat.getGrupo(), riscoCat.getDescricao());
                                    })
                                    .collect(Collectors.toSet());
                            mapaDto.setRiscosDoCatalogo(riscos);
                        }
                        
                        return mapaDto;
                    })
                    .collect(Collectors.toList());
        } else {
            this.mapaRiscos = new ArrayList<>();
        }

        this.planoAcaoRiscos = entity.getPlanoAcaoRiscos().stream()
                .map(planoAcaoMapper::toDto)
                .collect(Collectors.toList());
    }

}
