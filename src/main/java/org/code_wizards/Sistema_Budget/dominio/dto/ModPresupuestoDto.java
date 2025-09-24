package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import org.code_wizards.Sistema_Budget.dominio.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ModPresupuestoDto (
        Long idUser,
        @NotBlank(message = "El nombre del presupuesto no puede estar vacío")
        String budgetName,
        String budgetPeriod,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Status status,
        BigDecimal totalPlannedAmount) {
}
