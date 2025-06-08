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
public class ProfissionalResponsavelRequestDTO {

    @NotNull(message = "O ID do funcionário responsável não pode ser nulo.")
    private Long funcionarioId;

}
