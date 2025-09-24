package org.code_wizards.Sistema_Budget.dominio.exception;

public class UsuarioNoEliminableException extends RuntimeException {
    private final String codigoError;

    public UsuarioNoEliminableException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    public String getCodigoError() {
        return codigoError;
    }
}
