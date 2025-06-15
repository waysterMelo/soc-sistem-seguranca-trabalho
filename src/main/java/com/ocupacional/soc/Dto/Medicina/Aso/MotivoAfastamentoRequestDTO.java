package com.ocupacional.soc.Dto.Medicina.Aso;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MotivoAfastamentoRequestDTO {
    @NotBlank(message = "O código é obrigatório.")
    private String codigo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;
}