package com.ocupacional.soc.Dto.SegurancaTrabalho;

import com.ocupacional.soc.Enuns.SegurancaTrabalho.MetodologiaPlanoAcao;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.TipoPgr;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PgrRequestDTO {
    @NotNull
    private Long unidadeOperacionalId;
    private String termoValidacao;
    private String documentoBase;
    private String responsavelEmpresa;
    private LocalDate dataDocumento;
    private LocalDate dataRevisao;
    private String termoCiencia;
    private MetodologiaPlanoAcao planoAcaoMetodologia;
    private String planoAcaoOrientacoes;
    private String consideracoesFinais;
    @Valid
    private List<PgrMapaRiscoFuncaoRequestDTO> mapaRiscos;
    @Valid
    private List<PlanoAcaoRiscoRequestDTO> planoAcaoRiscos;

}