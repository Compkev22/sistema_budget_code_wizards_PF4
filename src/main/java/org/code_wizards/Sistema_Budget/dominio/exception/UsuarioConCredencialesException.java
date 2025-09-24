package org.code_wizards.Sistema_Budget.dominio.exception;

public class UsuarioConCredencialesException extends UsuarioNoEliminableException {
    public UsuarioConCredencialesException(Long idUsuario) {
        super("No se puede eliminar el usuario con ID " + idUsuario +
                        " porque tiene credenciales asociadas.",
                "usuario-con-credenciales");
    }
}
