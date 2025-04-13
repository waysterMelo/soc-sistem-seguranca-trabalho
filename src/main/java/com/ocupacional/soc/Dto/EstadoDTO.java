package com.ocupacional.soc.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {

    private String codigo;
    private String nome;

        // Lista estática de estados brasileiros
    public static List<EstadoDTO> getTodosEstados() {
        List<EstadoDTO> estados = new ArrayList<>();
        estados.add(new EstadoDTO("AC", "Acre"));
        estados.add(new EstadoDTO("AL", "Alagoas"));
        estados.add(new EstadoDTO("AP", "Amapá"));
        estados.add(new EstadoDTO("AM", "Amazonas"));
        estados.add(new EstadoDTO("BA", "Bahia"));
        estados.add(new EstadoDTO("CE", "Ceará"));
        estados.add(new EstadoDTO("DF", "Distrito Federal"));
        estados.add(new EstadoDTO("ES", "Espírito Santo"));
        estados.add(new EstadoDTO("GO", "Goiás"));
        estados.add(new EstadoDTO("MA", "Maranhão"));
        estados.add(new EstadoDTO("MT", "Mato Grosso"));
        estados.add(new EstadoDTO("MS", "Mato Grosso do Sul"));
        estados.add(new EstadoDTO("MG", "Minas Gerais"));
        estados.add(new EstadoDTO("PA", "Pará"));
        estados.add(new EstadoDTO("PB", "Paraíba"));
        estados.add(new EstadoDTO("PR", "Paraná"));
        estados.add(new EstadoDTO("PE", "Pernambuco"));
        estados.add(new EstadoDTO("PI", "Piauí"));
        estados.add(new EstadoDTO("RJ", "Rio de Janeiro"));
        estados.add(new EstadoDTO("RN", "Rio Grande do Norte"));
        estados.add(new EstadoDTO("RS", "Rio Grande do Sul"));
        estados.add(new EstadoDTO("RO", "Rondônia"));
        estados.add(new EstadoDTO("RR", "Roraima"));
        estados.add(new EstadoDTO("SC", "Santa Catarina"));
        estados.add(new EstadoDTO("SP", "São Paulo"));
        estados.add(new EstadoDTO("SE", "Sergipe"));
        estados.add(new EstadoDTO("TO", "Tocantins"));
        return estados;
    }
}
