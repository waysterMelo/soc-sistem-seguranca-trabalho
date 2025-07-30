package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private EmpresaSimpleResponseDTO empresa;
    private UnidadeOperacionalSimpleDTO unidadeOperacional;
    private StatusEmpresa status;


}