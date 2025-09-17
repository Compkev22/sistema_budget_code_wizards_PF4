package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record CredencialesDto(
        Long idCredencial,
        @Min(value = 0, message = "El codigo de usuario es Obligatorio")
        Integer userID,
        @NotBlank(message = "La dirección de Correo Electronico es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es Obligatoria")
        String password,
        @PastOrPresent(message = "La fecha es automática")
        LocalDateTime dateRecord
) {}
