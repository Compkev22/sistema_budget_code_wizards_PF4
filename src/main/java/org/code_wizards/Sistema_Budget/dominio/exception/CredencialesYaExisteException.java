package org.code_wizards.Sistema_Budget.dominio.exception;

public class CredencialesYaExisteException extends RuntimeException {
    public CredencialesYaExisteException(String email) {
        super("Las credenciales con el Correo: " + email + " ya existe.");
    }
}
