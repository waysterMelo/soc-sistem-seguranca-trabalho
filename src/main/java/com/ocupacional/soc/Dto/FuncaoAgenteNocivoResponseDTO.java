package com.ocupacional.soc.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoAgenteNocivoResponseDTO {

    private Long id; // ID da entidade FuncaoAgenteNocivoEntity
    private AgenteNocivoCatalogoSimpleResponseDTO agenteNocivoCatalogo;

}
