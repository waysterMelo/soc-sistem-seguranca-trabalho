package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoTrabalhistaPgrRequestDTO {

    @NotNull(message = "O grupo do risco PGR não pode ser nulo.")
    private GrupoRisco grupo;

    @NotBlank(message = "A descrição do risco PGR não pode ser vazia.")
    private String descricao;


}
