package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ModGastoDto(
        @NotBlank(message = "La descripción del gasto no puede estar vacía")
        String descripcionGasto,
        String categoriaGasto,
        @NotNull(message = "El monto del gasto no puede ser nulo")
        BigDecimal montoGasto,
        LocalDate fechaGasto
){}