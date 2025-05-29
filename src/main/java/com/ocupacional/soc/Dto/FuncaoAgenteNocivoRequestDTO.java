package com.ocupacional.soc.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoAgenteNocivoRequestDTO {

    @NotNull(message = "O ID do Agente Nocivo (Catálogo) é obrigatório.")
    private Long agenteNocivoCatalogoId;


}
