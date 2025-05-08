package com.ocupacional.soc.Dto;

import lombok.Data;

@Data
public class EnderecoDto {
    private Long id;
    private String cep;
    private String cidade;
    private String estado;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String regiao;
}
