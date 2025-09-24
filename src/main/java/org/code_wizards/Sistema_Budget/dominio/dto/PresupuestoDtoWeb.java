package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.code_wizards.Sistema_Budget.dominio.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoDtoWeb {
    Long idPresupuesto;
    @NotNull(message = "El ID del usuario no puede estar vacío")
    Long idUser;
    @NotBlank(message = "El nombre del presupuesto no puede estar vacío")
    String budgetName;
    String budgetPeriod;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Status status;
    BigDecimal totalPlannedAmount;
}