package com.ocupacional.soc.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorRequestDTO {

    @NotBlank(message = "O nome do setor é obrigatório.")
    private String nome;
    private String descricao;
    @NotNull(message = "O ID da empresa é obrigatório.")
    private Long empresaId;
}
