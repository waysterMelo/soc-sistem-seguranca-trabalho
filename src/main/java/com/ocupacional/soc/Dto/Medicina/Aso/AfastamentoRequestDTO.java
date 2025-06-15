package com.ocupacional.soc.Dto.Medicina.Aso;

import com.ocupacional.soc.Enuns.Medicina.Aso.OnusRemuneracao;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoAcidenteAfastamento;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoRetificacaoAso;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AfastamentoRequestDTO {
    @NotNull
    private Long registroProfissionalId;
    @NotNull private Long emitenteId;
    @NotNull private Long motivoAfastamentoId;
    private Long cidId;
    @NotNull private TipoAcidenteAfastamento tipoAcidente;
    @NotNull private String cnpj;
    @NotNull private OnusRemuneracao onusRemuneracao;
    @NotNull private LocalDate dataInicio;
    private LocalDate dataFim;
    private String observacao;
    private Boolean mesmaDoenca60Dias;
    private Boolean alterarStatusFuncionario;
    private Boolean exibirRgAtestado;
    private Boolean exibirCpfAtestado;
    @NotNull private TipoRetificacaoAso tipoRetificacao;
    private String numeroReciboEsocial;
}