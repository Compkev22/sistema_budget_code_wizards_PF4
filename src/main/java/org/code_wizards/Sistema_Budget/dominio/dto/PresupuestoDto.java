package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.code_wizards.Sistema_Budget.dominio.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PresupuestoDto (
        Long idPresupuesto,
        @NotNull(message = "El ID del usuario no puede estar vacío")
        Long idUsuario,
        @NotBlank(message = "El nombre del presupuesto no puede estar vacío")
        String nombrePresupuesto,
        String periodoPresupuesto,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        Status status,
        BigDecimal monto_TotalPlanificado

){}
