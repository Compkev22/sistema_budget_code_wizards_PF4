package org.code_wizards.Sistema_Budget.dominio.exception;

public class CategoriaNoExisteException extends RuntimeException {
    public CategoriaNoExisteException(Long idCategoria) {
        super("La categoría con ID " + idCategoria + " no existe.");
    }

    public CategoriaNoExisteException(String nombreCategoria) {
        super("La categoría con nombre '" + nombreCategoria + "' no existe.");
    }
}
