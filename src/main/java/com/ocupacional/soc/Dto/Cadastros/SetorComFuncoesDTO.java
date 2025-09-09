package com.ocupacional.soc.Dto.Cadastros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorComFuncoesDTO {
    private Long id;
    private String nome;
    private String descricao;
    private EmpresaSimpleResponseDTO empresa;
    private UnidadeOperacionalSimpleDTO unidadeOperacional;
    private List<FuncaoResponseDTO> funcoes;
}