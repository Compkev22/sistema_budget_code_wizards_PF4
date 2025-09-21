package org.code_wizards.Sistema_Budget.dominio.exception;

public class MetaAhorroNoExisteException extends RuntimeException {
    public MetaAhorroNoExisteException(Long idAhorro) {
        super("La Meta Ahorro con ID " + idAhorro + " no existe.");
    }

    public MetaAhorroNoExisteException(String nombreMeta) {
        super("La Meta Ahorro con nombre '" + nombreMeta + "' no existe.");
    }


}
