package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.Status;
import org.mapstruct.Named;

public class StatusMapper {
    @Named("generarStatus")
    public static Status generarStatus(String status) {
        if (status == null) return null;

        return switch (status.toUpperCase()) {
            case "ACTIVO" -> Status.ASSET;
            case "INACTIVO" -> Status.IDLE;
            case "COMPLETADO" -> Status.FILLED;
            default -> null;
        };

    }

    @Named("generarEstado")
    public static String generarEstado(Status status) {
        if (status == null) return null;

        return switch (status) {
            case ASSET -> "ACTIVO";
            case IDLE -> "INACTIVO";
            case FILLED -> "COMPLETADO";
            default -> null;
        };
    }
}
