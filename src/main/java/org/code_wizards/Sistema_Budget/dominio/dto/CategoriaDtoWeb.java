package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoriaDtoWeb {

    @Min(value = 0, message = "El ID de la categoría debe ser positivo")
    private Long categoryId;

    @Min(value = 0, message = "El ID del presupuesto debe ser positivo")
    private Integer budgetId;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String categoryName;

    @NotBlank(message = "El tipo de categoría es obligatorio")
    private String categoryType;

    @NotBlank(message = "El color de identificación es obligatorio")
    private String identificationColor;

    @Min(value = 0, message = "El monto planificado no puede ser negativo")
    private Double plannedAmount;

    @Min(value = 0, message = "El monto actual no puede ser negativo")
    private Double currentAmount;

    // Constructor por defecto
    public CategoriaDtoWeb() {}

    // Constructor con parámetros
    public CategoriaDtoWeb(Long categoryId, Integer budgetId, String categoryName,
                           String categoryType, String identificationColor,
                           Double plannedAmount, Double currentAmount) {
        this.categoryId = categoryId;
        this.budgetId = budgetId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.identificationColor = identificationColor;
        this.plannedAmount = plannedAmount;
        this.currentAmount = currentAmount;
    }

    // Getters y Setters
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getIdentificationColor() {
        return identificationColor;
    }

    public void setIdentificationColor(String identificationColor) {
        this.identificationColor = identificationColor;
    }

    public Double getPlannedAmount() {
        return plannedAmount;
    }

    public void setPlannedAmount(Double plannedAmount) {
        this.plannedAmount = plannedAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
