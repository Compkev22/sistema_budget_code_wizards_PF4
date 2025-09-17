package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.code_wizards.Sistema_Budget.dominio.statusAhorro;

import java.util.Date;

public record MetaAhorroDto(
        @Min(value = 0, message = "El ID de la MetaAhorro debe ser positivo")
        Long idSavingsGoal,

        @Min(value = 0, message = "El ID del presupuesto debe ser positivo")
        Integer idBudget,

        @NotBlank(message = "El nombre de la Meta es obligatorio")
        String goalName,

        @NotNull(message = "El monto objetivo es obligatorio")
        Double targetAmount,

        @NotNull(message = "El monto actual es obligatorio")
        Double currentAmount,

        @NotNull(message = "La fecha limite es obligatorio")
        Date deadLine,

        @NotNull(message = "El estado no puede ser negativo")
        statusAhorro status
) {}
