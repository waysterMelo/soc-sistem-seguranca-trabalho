package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiscoCatalogoRequestDTO {
    @NotNull(message = "O grupo do risco é obrigatório.")
    private GrupoRisco grupo;

    @NotBlank(message = "A descrição do risco é obrigatória.")
    private String descricao;
}