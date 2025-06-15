package com.ocupacional.soc.Dto.Medicina.Aso;

import lombok.Data;

@Data
public class RegistroProfissionalSimpleDTO {
    private Long id; // Adicionando o ID para referência
    private String nomeFuncionario;
    private String nomeFuncao;
    private String nomeEmpresa;
}