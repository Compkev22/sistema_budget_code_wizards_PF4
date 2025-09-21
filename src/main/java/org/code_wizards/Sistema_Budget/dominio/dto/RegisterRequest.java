package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotBlank(message = "El apellido es obligatorio")
        String lastnameUser,

        @NotBlank(message = "El teléfono es obligatorio")
        String telephone,

        @NotBlank(message = "El nit es obligatorio")
        String nitUser,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {}