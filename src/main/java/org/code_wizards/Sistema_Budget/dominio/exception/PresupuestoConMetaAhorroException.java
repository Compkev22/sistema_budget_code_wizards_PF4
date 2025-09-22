    package org.code_wizards.Sistema_Budget.dominio.exception;

    public class PresupuestoConMetaAhorroException extends PresupuestoNoEliminableException {
        public PresupuestoConMetaAhorroException(Long idPresupuesto) {
            super("No se puede eliminar el presupuesto con ID " + idPresupuesto +
                    " porque tiene Metas de Ahorro asociadas.","presupuesto-con-meta-aharro");
        }
    }
