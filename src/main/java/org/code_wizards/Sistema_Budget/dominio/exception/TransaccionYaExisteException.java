package org.code_wizards.Sistema_Budget.dominio.exception;

public class TransaccionYaExisteException extends RuntimeException {
    public TransaccionYaExisteException(Integer idCategory, String descriptionTransaction) {
        super("La Transaccion con Categoria ID: " + idCategory + " y Descripcion: '" + descriptionTransaction + "' ya exsite.");
    }
}
