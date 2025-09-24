package org.code_wizards.Sistema_Budget.dominio.exception;

public class AuthException extends RuntimeException {
    public AuthException(String mensaje) {
        super(mensaje);
    }
}
