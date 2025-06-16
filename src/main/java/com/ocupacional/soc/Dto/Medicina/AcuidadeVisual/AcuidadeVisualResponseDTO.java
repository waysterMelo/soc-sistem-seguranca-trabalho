package com.ocupacional.soc.Dto.Medicina.AcuidadeVisual;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.RegistroProfissionalSimpleDTO;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.DiagnosticoComparativoEnum;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.ReavaliacaoEnum;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.TipoAvaliacaoAcuideEnum;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AcuidadeVisualResponseDTO {
    private Long id;
    private RegistroProfissionalSimpleDTO registroProfissional;
    private LocalDate dataAvaliacao;
    private TipoAvaliacaoAcuideEnum tipoAvaliacao;
    private boolean usaLentes;
    private PrestadorServicoResponseDTO medicoResponsavel;
    private TipoExameFuncao tipoExame;
    private Integer odP1;
    private Integer odP2;
    private Integer odPercentual;
    private Integer oeP1;
    private Integer oeP2;
    private Integer oePercentual;
    private Integer aoP1;
    private Integer aoP2;
    private Integer aoPercentual;
    private String observacoes;
    private DiagnosticoComparativoEnum diagnosticoComparativo;
    private ReavaliacaoEnum reavaliacao;
    private boolean encaminhamentoComAfastamento;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}