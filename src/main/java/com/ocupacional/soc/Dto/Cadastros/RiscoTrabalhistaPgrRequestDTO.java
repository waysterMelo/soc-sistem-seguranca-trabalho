package com.ocupacional.soc.Dto.Cadastros;

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

    @NotNull(message = "O ID do Risco (Catálogo) é obrigatório.")
    private Long riscoCatalogoId;
}