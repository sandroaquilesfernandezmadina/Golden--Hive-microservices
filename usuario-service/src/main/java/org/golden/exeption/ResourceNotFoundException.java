package org.golden.exeption;

public class ResourceNotFoundException extends RuntimeException{
    //se usa cuando el registro no existe en la base de datos
    //por ejemplo un registro no encontrado, rol no encontrado
    // producto no encontrado
    public ResourceNotFoundException(String message){
        super(message);
    }
}
