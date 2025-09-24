package org.code_wizards.Sistema_Budget.dominio.exception;

public class PresupuestoConCategoriaException extends PresupuestoNoEliminableException {
    public PresupuestoConCategoriaException(Long idPresupuesto) {
        super("No se puede eliminar el presupuesto con ID " + idPresupuesto +
                        " porque tiene Categorias asociadas.","presupuesto-con-categoria");
    }
}
