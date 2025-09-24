package org.code_wizards.Sistema_Budget.dominio;

public enum Status {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    COMPLETADO("COMPLETADO");

    private final String display;

    Status(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
