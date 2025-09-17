package org.code_wizards.Sistema_Budget.dominio.dto;

public record ModCredencialesDto(
        Integer userID,
        String email,
        String password
) {
}
