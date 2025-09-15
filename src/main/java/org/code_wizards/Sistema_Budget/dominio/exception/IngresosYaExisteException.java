package org.code_wizards.Sistema_Budget.dominio.exception;

public class IngresosYaExisteException extends RuntimeException {

    public IngresosYaExisteException(Integer idBudget, String descriptionEntry) {
        super("El ingreso con Presupuesto ID: " + idBudget + " y Descripción: '" + descriptionEntry + "' ya existe.");
    }
}
