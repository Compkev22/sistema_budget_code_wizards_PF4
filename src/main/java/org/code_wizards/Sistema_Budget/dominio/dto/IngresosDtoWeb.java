package org.code_wizards.Sistema_Budget.dominio.dto;

import java.time.LocalDate;

public class IngresosDtoWeb {

    private Long idIngreso;
    private Long idBudget;
    private Integer idCategory;
    private String descriptionEntry;
    private Double incomeAmount;
    private LocalDate entryDate;

    public IngresosDtoWeb() {
    }

    public IngresosDtoWeb(Long idIngreso, Long idBudget, Integer idCategory,
                          String descriptionEntry, Double incomeAmount, LocalDate entryDate) {
        this.idIngreso = idIngreso;
        this.idBudget = idBudget;
        this.idCategory = idCategory;
        this.descriptionEntry = descriptionEntry;
        this.incomeAmount = incomeAmount;
        this.entryDate = entryDate;
    }

    public Long getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Long idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Long getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Long idBudget) {
        this.idBudget = idBudget;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescriptionEntry() {
        return descriptionEntry;
    }

    public void setDescriptionEntry(String descriptionEntry) {
        this.descriptionEntry = descriptionEntry;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
