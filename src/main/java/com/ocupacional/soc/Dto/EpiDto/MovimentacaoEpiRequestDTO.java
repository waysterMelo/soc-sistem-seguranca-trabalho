package com.ocupacional.soc.Dto.EpiDto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovimentacaoEpiRequestDTO {
    private Long funcionarioId;
    private List<ItemMovimentacaoDTO> itens;
    private String termoCiencia;

    @Getter
    @Setter
    public static class ItemMovimentacaoDTO {
        private Long epiId;
        private Integer quantidade;
    }
}