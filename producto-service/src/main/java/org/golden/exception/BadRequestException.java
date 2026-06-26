package org.golden.exception;

public class BadRequestException extends RuntimeException {
    //se usa cuando el usuario manda datos incorrectos, repetidos, campos invalidos
    public BadRequestException(String message) {
        super(message);
    }
}
