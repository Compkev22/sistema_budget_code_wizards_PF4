package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDto(
        @Min(value = 0, message = "El ID de la categoría debe ser positivo")
        Long categoryId,

        @Min(value = 0, message = "El ID del presupuesto debe ser positivo")
        Integer budgetId,

        @NotBlank(message = "El nombre de la categoría es obligatorio")
        String categoryName,

        @NotBlank(message = "El tipo de categoría es obligatorio")
        String categoryType,

        @NotBlank(message = "El color de identificación es obligatorio")
        String identificationColor,

        @Min(value = 0, message = "El monto planificado no puede ser negativo")
        Double plannedAmount,

        @Min(value = 0, message = "El monto actual no puede ser negativo")
        Double currentAmount
) {}
