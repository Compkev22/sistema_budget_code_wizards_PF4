package org.code_wizards.Sistema_Budget.dominio.exception;

public class CategoriaConIngresosException extends PresupuestoNoEliminableException {
    public CategoriaConIngresosException(Long idCategoria) {
        super("No se puede eliminar La Categoria con ID " + idCategoria +
                " porque tiene Ingresos asociados.","categoria-con-ingreso");
    }
}
