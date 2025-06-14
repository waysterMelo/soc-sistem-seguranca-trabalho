package com.ocupacional.soc.Dto.Medicina.Espirometria;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EspirometriaAvaliacaoListDTO {
    private Long id;
    private LocalDate dataAvaliacao;
    private String nomeEmpresa;
    private String nomeFuncionario;
}