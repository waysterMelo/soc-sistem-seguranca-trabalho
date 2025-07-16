package com.ocupacional.soc.Enuns.Medicina.Toxicologico;

import lombok.Getter;

@Getter
public enum StatusExameToxicologicoEnum {

    PENDENTE_DE_ENVIO("Pendente de Envio"),
    ENVIADO("Enviado"),
    CONCLUIDO("Concluído");

    private final String descricao;

    StatusExameToxicologicoEnum(String descricao) {
        this.descricao = descricao;
    }
}
