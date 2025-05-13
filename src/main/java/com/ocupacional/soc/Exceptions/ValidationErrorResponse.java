package com.ocupacional.soc.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ValidationErrorResponse extends  ErrorResponse{

    private Map<String, String> erros;

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String titulo,
                                   String mensagem, String path, Map<String, String> erros) {
        super(timestamp, status, titulo, mensagem, path);
        this.erros = erros;
    }

}
