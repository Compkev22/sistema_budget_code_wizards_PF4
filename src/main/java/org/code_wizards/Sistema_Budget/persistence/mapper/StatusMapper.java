package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.Status;
import org.mapstruct.Named;

public class StatusMapper {
    @Named("generarStatus")
    public static Status generarStatus(String status) {
        if (status == null) return null;

        return switch (status.toUpperCase()) {
            case "ACTIVO" -> Status.ACTIVO;
            case "INACTIVO" -> Status.INACTIVO;
            case "COMPLETADO" -> Status.COMPLETADO;
            default -> null;
        };

    }

    @Named("generarEstado")
    public static String generarEstado(Status status) {
        if (status == null) return null;

        return switch (status) {
            case ACTIVO -> "ACTIVO";
            case INACTIVO -> "INACTIVO";
            case COMPLETADO -> "COMPLETADO";
            default -> null;
        };
    }
}
