package com.ocupacional.soc.Dto.Medicina.FichaClinica;

import com.ocupacional.soc.Enuns.Medicina.Fichaclinica.TipoPergunta;
import lombok.Data;

import java.util.List;

@Data
public class FichaClinicaResponseDTO {
    private Long id;
    private String nome;
    private boolean imprimirDuasColunas;
    private boolean exibirCampoAssinatura;
    private List<QuadroResponseDTO> quadros;

    @Data
    public static class QuadroResponseDTO {
        private Long id;
        private String nome;
        private String observacoes;
        private List<PerguntaResponseDTO> perguntas;
    }

    @Data
    public static class PerguntaResponseDTO {
        private Long id;
        private String nome;
        private TipoPergunta tipo;
        private List<OpcaoResponseDTO> opcoes;
    }

    @Data
    public static class OpcaoResponseDTO {
        private Long id;
        private String textoOpcao;
    }
}