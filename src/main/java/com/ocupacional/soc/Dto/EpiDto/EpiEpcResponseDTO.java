package com.ocupacional.soc.Dto.EpiDto;

import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import com.ocupacional.soc.Enuns.CadastroEpi.TipoEpiEpc;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EpiEpcResponseDTO {

    private Long id;
    private String nome;
    private TipoEpiEpc tipo;
    private String modelo;
    private LocalDate validadeCA;
    private boolean apenasParaPGR;
    private String certificadoAvaliacao;
    private String periodicidadeUso;
    private EpiCategoriaDTO categoria;
    private EpiFabricanteDTO fabricante;
    private EmpresaSimpleResponseDTO empresa;
    private String criadoPor;
}