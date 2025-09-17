package org.code_wizards.Sistema_Budget.dominio.exception;

public class GastoYaExisteException extends RuntimeException {
    public GastoYaExisteException(String message) {
        super(message);
    }
}