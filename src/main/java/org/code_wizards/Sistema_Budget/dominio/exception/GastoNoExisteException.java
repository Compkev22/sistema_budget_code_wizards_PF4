package org.code_wizards.Sistema_Budget.dominio.exception;

public class GastoNoExisteException extends RuntimeException {
    public GastoNoExisteException(Long codigo) {
        super("El gasto con el código " + codigo + " no existe.");
    }
    public GastoNoExisteException(String mensaje) {
        super(mensaje);
    }
}