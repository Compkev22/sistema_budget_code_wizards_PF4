package org.code_wizards.Sistema_Budget.dominio.exception;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(Long codigo) {
        super("El usuario con codigo: " + codigo + " no existe");
    }
}
