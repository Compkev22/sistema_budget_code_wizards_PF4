package org.code_wizards.Sistema_Budget.dominio.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String nombreUsuario) {
        super("EL usuario con el nombre: " +nombreUsuario+ " ya existe.");
    }
}
