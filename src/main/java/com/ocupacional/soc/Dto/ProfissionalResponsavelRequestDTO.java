package com.ocupacional.soc.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalResponsavelRequestDTO {

    @NotNull(message = "O ID do funcionário responsável não pode ser nulo.")
    private Long funcionarioId;

}
