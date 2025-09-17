package org.code_wizards.Sistema_Budget.dominio.exception;

public class TransaccionNoExisteException extends RuntimeException {
    public TransaccionNoExisteException(Long codigo) {
        super("Las Transacciones con codigo: " + codigo + " no existen");
    }
}
