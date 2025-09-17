package org.code_wizards.Sistema_Budget.dominio.dto;

public record ModCategoriaDto(
        Integer budgetId,
        String categoryName,
        String categoryType,
        String identificationColor,
        Double plannedAmount,
        Double currentAmount
) {
}

