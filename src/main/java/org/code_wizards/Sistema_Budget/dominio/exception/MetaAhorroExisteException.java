package org.code_wizards.Sistema_Budget.dominio.exception;

public class MetaAhorroExisteException extends RuntimeException {
    public MetaAhorroExisteException(String nombreMeta) {
        super("La meta de ahorro con nombre '" + nombreMeta + "' ya existe.");
    }

    public MetaAhorroExisteException(Long idAhorro) {

        super("Ya existe una meta ahorro con el ID " + idAhorro + ".");
    }
}
