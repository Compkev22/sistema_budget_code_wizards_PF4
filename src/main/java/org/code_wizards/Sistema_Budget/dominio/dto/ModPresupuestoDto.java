package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ModPresupuestoDto (
    @NotBlank(message = "El nombre del presupuesto no puede estar vacío")
     String budgetName,
     String budgetPeriod,
     LocalDateTime startDate,
     LocalDateTime endDate,
     String status
){}
