package com.ocupacional.soc.Dto.Aparelhos;

import com.ocupacional.soc.Enuns.Aparelho.StatusAparelho;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AparelhoResponseDTO {

    private Long id;
    private String descricao;
    private String modelo;
    private String marca;
    private String calibracao;
    private LocalDate dataCalibracao;
    private LocalDate validadeCalibracao;
    private String criteriosAvaliacao;
    private StatusAparelho status;
    private String certificadoUrl;
}