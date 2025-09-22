package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record IngresosDto(
        Long idIngreso,
        @NotNull(message = "El id del presupuesto es obligatorio")
        Long idBudget,
        @NotNull(message = "El id de la categoria es obligatorio")
        Integer idCategory,
        @NotBlank(message = "La descripcion del ingreso es obligatoria")
        String descriptionEntry,
        @NotNull(message = "El monto del ingreso es obligatorio")
        @Min(value = 0, message = "El monto del ingreso no puede ser menor a 0")
        Double incomeAmount,
        @PastOrPresent(message = "La fecha del ingreso no puede ser posterior a HOY")
        LocalDate entryDate
) {}