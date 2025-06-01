package com.ocupacional.soc.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgenteNocivoCatalogoResponseDTO {

    private Long id;
    private String codigoEsocial;
    private String descricao;

}
