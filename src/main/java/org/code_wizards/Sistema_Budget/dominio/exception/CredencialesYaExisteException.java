package org.code_wizards.Sistema_Budget.dominio.exception;

public class CredencialesYaExisteException extends RuntimeException {
    public CredencialesYaExisteException(Integer userID,String email) {
        super("Las credenciales con el Usuario :  " + userID +" Y Correo: " + email + " ya existe.");
    }
}
