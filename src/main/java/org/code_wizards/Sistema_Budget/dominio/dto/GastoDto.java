package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GastoDto (
        Long idGasto,
        @NotNull(message = "El ID del presupuesto no puede estar vacío")
        Long idPresupuesto,
        @NotNull(message = "El ID de la categoría no puede estar vacío")
        Long idCategoria,
        @NotBlank(message = "La descripción del gasto no puede estar vacía")
        String descripcionGasto,
        String categoriaGasto,
        BigDecimal montoGasto,
        LocalDate fechaGasto
){}