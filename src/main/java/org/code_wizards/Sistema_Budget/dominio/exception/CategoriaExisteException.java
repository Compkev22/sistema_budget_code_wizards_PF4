package org.code_wizards.Sistema_Budget.dominio.exception;

public class CategoriaExisteException extends RuntimeException {
    public CategoriaExisteException(String nombreCategoria) {
        super("Ya existe una categoría con el nombre '" + nombreCategoria + "'.");
    }

    public CategoriaExisteException(Long idCategoria) {
        super("Ya existe una categoría con el ID " + idCategoria + ".");
    }
}
