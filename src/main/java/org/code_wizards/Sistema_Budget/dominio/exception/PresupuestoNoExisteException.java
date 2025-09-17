package org.code_wizards.Sistema_Budget.dominio.exception;

public class PresupuestoNoExisteException extends RuntimeException {
    public PresupuestoNoExisteException(Long codigo) {
        super("El presupuesto con el código " + codigo + " no existe.");
    }
    public PresupuestoNoExisteException(String mensaje) {
        super(mensaje);
    }
}
