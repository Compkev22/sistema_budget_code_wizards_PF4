package org.code_wizards.Sistema_Budget.dominio.exception;

public class UsuarioConPresupuestosException extends UsuarioNoEliminableException {
    public UsuarioConPresupuestosException(Long idUsuario) {
        super("No se puede eliminar el usuario con ID " + idUsuario +
                        " porque tiene presupuestos asociados.",
                "usuario-con-presupuestos");
    }
}
