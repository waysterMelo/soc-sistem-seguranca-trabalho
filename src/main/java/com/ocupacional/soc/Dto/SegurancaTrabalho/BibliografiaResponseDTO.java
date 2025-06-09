package com.ocupacional.soc.Dto.SegurancaTrabalho;

import lombok.Data;

@Data
public class BibliografiaResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String edicao;
    private String editora;
    private Integer anoPublicacao;
}