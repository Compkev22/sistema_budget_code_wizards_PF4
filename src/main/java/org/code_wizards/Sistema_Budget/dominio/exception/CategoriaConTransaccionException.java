package org.code_wizards.Sistema_Budget.dominio.exception;

public class CategoriaConTransaccionException extends PresupuestoNoEliminableException {
    public CategoriaConTransaccionException(Long idCategoria) {
        super("No se puede eliminar La Categoria con ID " + idCategoria +
                " porque tiene Transacciones asociadas.","categoria-con-transaccion");
    }
}
