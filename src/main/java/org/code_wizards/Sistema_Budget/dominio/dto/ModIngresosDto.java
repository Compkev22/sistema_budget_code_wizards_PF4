package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record ModIngresosDto(
        Integer idBudget,
        Integer idCategory,
        String descriptionEntry,
        Double incomeAmount,
        Date entryDate
) {}
