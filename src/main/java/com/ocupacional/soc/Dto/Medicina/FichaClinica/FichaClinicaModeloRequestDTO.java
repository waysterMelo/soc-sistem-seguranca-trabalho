package com.ocupacional.soc.Dto.Medicina.FichaClinica;

import com.ocupacional.soc.Enuns.Medicina.Fichaclinica.TipoPergunta;
import lombok.Data;

import java.util.List;

@Data
public class FichaClinicaModeloRequestDTO {
    private String nome;
    private boolean imprimirDuasColunas;
    private boolean exibirCampoAssinatura;
    private List<QuadroDTO> quadros;

    @Data
    public static class QuadroDTO {
        private String nome;
        private String observacoes;
        private List<PerguntaDTO> perguntas;
    }

    @Data
    public static class PerguntaDTO {
        private String nome;
        private TipoPergunta tipo;
        private List<OpcaoDTO> opcoes;
    }

    @Data
    public static class OpcaoDTO {
        private String textoOpcao;
    }
}