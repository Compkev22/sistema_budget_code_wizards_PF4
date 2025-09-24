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
    private Long idPresupuesto;
    @NotNull(message = "El ID del usuario no puede estar vacío")
    private Long idUser;
    @NotBlank(message = "El nombre del presupuesto no puede estar vacío")
    private String budgetName;
    private String budgetPeriod;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private BigDecimal totalPlannedAmount;
}