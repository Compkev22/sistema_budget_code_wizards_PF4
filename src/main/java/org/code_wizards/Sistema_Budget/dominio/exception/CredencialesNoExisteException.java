package org.code_wizards.Sistema_Budget.dominio.exception;

public class CredencialesNoExisteException extends RuntimeException {
    public CredencialesNoExisteException(Long codigo) {
        super("Las credenciales con codigo: " + codigo + " no existen");
    }
}
