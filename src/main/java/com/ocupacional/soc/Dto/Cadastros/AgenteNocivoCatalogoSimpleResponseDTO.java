package com.ocupacional.soc.Dto.Cadastros;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgenteNocivoCatalogoSimpleResponseDTO {

    private Long id;
    private String codigoEsocial;
    private String descricao;


}
