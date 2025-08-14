package com.ocupacional.soc.Dto.CadastroPrestadorServicos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestadorServicoSimplificadoDTO {

    private Long id;
    private String nome;
    private String cpfCnpj;
    private String especialidade;
    private String conselho;
    private String numeroConselho;
    private String telefone;
    private String email;
}
