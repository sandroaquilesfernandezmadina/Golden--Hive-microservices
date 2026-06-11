package org.golden.exeption;

public class BadRequestException extends RuntimeException {
    // se usa cuando el usuario manda datos incorretos, repetidos, campos invalidos
public BadRequestException(String message){
    super(message);
}
}
