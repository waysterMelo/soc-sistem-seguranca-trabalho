package com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual;

import lombok.Getter;

@Getter
public enum TipoAvaliacaoAcuideEnum {
    PRIMEIRO_TESTE("1º Teste"),
    NAO_INFORMADO("Não Informado"),
    ACOMPANHAMENTO("Acompanhamento");

    private final String descricao;

    TipoAvaliacaoAcuideEnum(String descricao) {
        this.descricao = descricao;
    }
}