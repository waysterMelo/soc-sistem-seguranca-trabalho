package com.ocupacional.soc.Dto.SegurancaTrabalho;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.MetodologiaPlanoAcao;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.TipoPgr;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PgrResponseDTO {
    private Long id;
    private UnidadeOperacionalResponseDTO unidadeOperacional;
    private String conteudoCapa;
    private String termoValidacao;
    private String documentoBase;
    private String responsavelEmpresa;
    private LocalDate dataDocumento;
    private LocalDate dataRevisao;
    private String termoCiencia;
    private MetodologiaPlanoAcao planoAcaoMetodologia;
    private String planoAcaoOrientacoes;
    private String consideracoesFinais;
    private List<PgrMapaRiscoFuncaoResponseDTO> mapaRiscos;
    private List<PlanoAcaoRiscoResponseDTO> planoAcaoRiscos;
}