package com.ocupacional.soc.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrauRiscoDTO {
    private String codigo;
    private String descricao;
    private int dias;


     // Lista estática de graus de risco
    public static List<GrauRiscoDTO> getTodosGrausRisco() {
        List<GrauRiscoDTO> grausRisco = new ArrayList<>();
        grausRisco.add(new GrauRiscoDTO("NAO_INFORMADO", "Não Informado", 0));
        grausRisco.add(new GrauRiscoDTO("GRAU_1", "1 - até 135 dias", 135));
        grausRisco.add(new GrauRiscoDTO("GRAU_2", "2 - até 135 dias", 135));
        grausRisco.add(new GrauRiscoDTO("GRAU_3", "3 - até 90 dias", 90));
        grausRisco.add(new GrauRiscoDTO("GRAU_4", "4 - até 90 dias", 90));
        return grausRisco;
    }
}
