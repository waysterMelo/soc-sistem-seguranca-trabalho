package com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Não mostra campos nulos no JSON (empresa ou unidade)
public class PcmsoAnaliticoResponseDTO {
    private Long id;
    private EmpresaSimpleResponseDTO empresa;
    private UnidadeOperacionalResponseDTO unidadeOperacional;
    private PrestadorServicoResponseDTO medicoResponsavel;
    private String discussaoResultados;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}