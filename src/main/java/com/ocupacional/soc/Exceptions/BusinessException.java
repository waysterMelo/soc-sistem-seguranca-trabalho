package com.ocupacional.soc.Exceptions;


/**
 * Usada para erros de regra de negócio (validações, conflitos, etc.).
 * Mapeada para HTTP 400.
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
