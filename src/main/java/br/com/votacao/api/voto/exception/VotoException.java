package br.com.votacao.api.voto.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class VotoException extends RuntimeException implements Serializable {

    private final String message;
    private final HttpStatus httpStatus;

    public VotoException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
