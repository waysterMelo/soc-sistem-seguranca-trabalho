package com.ocupacional.soc.Dto.SegurancaTrabalho;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BibliografiaRequestDTO {
    @NotBlank
    private String titulo;
    private String autor;
    private String edicao;
    private String editora;
    private Integer anoPublicacao;
}