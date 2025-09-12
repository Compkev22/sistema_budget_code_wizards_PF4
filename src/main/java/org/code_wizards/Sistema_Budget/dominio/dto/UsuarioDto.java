package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto (
    Long codigo,
    @NotBlank(message = "El nombre es obligatorio")
    String nameUser,
    @NotBlank(message = "El apellido es obligatorio")
    String lastnameUser,
    @NotBlank(message = "El telefono es obligatorio")
    String telephone,
    @NotBlank(message = "El nit es obligatorio")
    String nitUser

) {}
