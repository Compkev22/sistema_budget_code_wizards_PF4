package org.code_wizards.Sistema_Budget.dominio.exception;

public class PresupuestoConIngresoException extends PresupuestoNoEliminableException {
    public PresupuestoConIngresoException(Long idPresupuesto) {
        super("No se puede eliminar el presupuesto con ID " + idPresupuesto +
                " porque tiene Ingresos asociados.","presupuesto-con-ingreso");
    }
}
