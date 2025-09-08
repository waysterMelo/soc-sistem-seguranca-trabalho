package com.ocupacional.soc.Enuns.SegurancaTrabalho;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MetodologiaPlanoAcao {

    _5W2H("5W2H"),
    _5W2H_PDCA("5W2H_PDCA"),
    PDCA("PDCA"),
    PERSONALIZADA("PERSONALIZADA");

    private final String value;

    MetodologiaPlanoAcao(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MetodologiaPlanoAcao fromValue(String value) {
        for (MetodologiaPlanoAcao metodologia : values()) {
            if (metodologia.value.equalsIgnoreCase(value)) {
                return metodologia;
            }
        }
        throw new IllegalArgumentException("Metodologia desconhecida: " + value);
    }

}
