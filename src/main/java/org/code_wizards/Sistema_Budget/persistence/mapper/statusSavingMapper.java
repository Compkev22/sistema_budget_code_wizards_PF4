package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.statusSaving;

public class statusSavingMapper {
    public static String toString(statusSaving status) {
        if (status == null) return null;
        return status.name();
    }

    public static statusSaving fromString(String status) {
        if (status == null || status.isBlank()) return null;
        return statusSaving.valueOf(status.toUpperCase());
    }
}
