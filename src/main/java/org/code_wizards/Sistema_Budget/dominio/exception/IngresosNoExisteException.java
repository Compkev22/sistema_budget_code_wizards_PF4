package org.code_wizards.Sistema_Budget.dominio.exception;

public class IngresosNoExisteException extends RuntimeException {
    public IngresosNoExisteException(Long codigo) {
        super("Los Ingresos con codigo: " + codigo + " no existen");
    }
}
