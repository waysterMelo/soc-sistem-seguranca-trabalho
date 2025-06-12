package com.ocupacional.soc.Dto.EpiDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MovimentacaoEpiResponseDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataMovimentacao;

    private Integer quantidade;

    private FuncionarioResponseDTO funcionario; // DTO simplificado do funcionário

    private EpiEpcResponseDTO epi; // DTO do EPI

    private String termoCiencia;
}