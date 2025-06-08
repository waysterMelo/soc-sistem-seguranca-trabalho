package com.ocupacional.soc.Dto.Cadastros;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaSimpleResponseDTO {

    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
}
