package org.golden.exception;

public class ResourceNotFoundException extends RuntimeException {
  //se usa cuando el registro no existe en la base de datos
  //por ejemplo un registro no encontrado, rol no encontrado
  //categoria no encontrado
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
