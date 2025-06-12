package com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento;


import com.ocupacional.soc.Enuns.SegurancaTrabalho.Treinamentos.ModalidadeTreinamento;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Treinamentos.TipoCapacitacao;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Treinamentos.TipoTreinamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TreinamentoCatalogoDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private TipoTreinamento tipo;
    @NotNull
    private TipoCapacitacao tipoCapacitacao;
    @NotNull
    private ModalidadeTreinamento modalidade;
    private Integer duracaoHoras;
}