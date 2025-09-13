package org.code_wizards.Sistema_Budget.dominio.exception;

public class CredencialesNoExisteException extends RuntimeException {
    public CredencialesNoExisteException(Long idCredencial) {
        super("Las credenciales con codigo: " + idCredencial + " no existen");
    }
}
