package com.ocupacional.soc.Dto.SegurancaTrabalho;

import com.ocupacional.soc.Enuns.SegurancaTrabalho.LtcatSituacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class LtcatRequestDTO {
    @NotNull
    private Long unidadeOperacionalId;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private Integer alertaValidadeDias;
    private LtcatSituacao situacao;
    private String comentariosInternos;
    private String condicoesPreliminares;
    private String laudoResponsabilidadeTecnica;
    private String laudoIntroducao;
    private String laudoObjetivos;
    private String laudoConsideracoesGerais;
    private String laudoCriteriosAvaliacao;
    private String recomendacoesTecnicas;
    private String conclusao;
    @Valid
    private List<LtcatAgenteNocivoRequestDTO> agentesNocivos;
    private List<Long> profissionaisAmbientaisIds;
    private List<Long> prestadoresServicoIds;
    private List<Long> aparelhosIds;
    private List<Long> bibliografiasIds;
    private List<Long> funcoesIds;
    private List<Long> empresasContratantesIds;
    private String imagemCapa;
}