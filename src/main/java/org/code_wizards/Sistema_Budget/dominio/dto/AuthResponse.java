package org.code_wizards.Sistema_Budget.dominio.dto;

public record AuthResponse(
        String mensaje,
        boolean exito,
        Long idUsuario,
        String nombreUsuario
) {

    public static AuthResponse error(String mensaje) {
        return new AuthResponse(mensaje, false, null, null);
    }

    public static AuthResponse success(String mensaje, Long idUsuario, String nombreUsuario) {
        return new AuthResponse(mensaje, true, idUsuario, nombreUsuario);
    }
}
