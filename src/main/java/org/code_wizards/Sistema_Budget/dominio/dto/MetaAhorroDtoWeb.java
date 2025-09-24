package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.code_wizards.Sistema_Budget.dominio.statusAhorro;

import java.util.Date;

public class MetaAhorroDtoWeb {
    @Min(value = 0, message = "El ID de la MetaAhorro debe ser positivo")
    private Long idSavingsGoal;

    @Min(value = 0, message = "El ID del presupuesto debe ser positivo")
    private Integer idBudget;

    @NotBlank(message = "El nombre de la Meta es obligatorio")
    private String goalName;

    @NotNull(message = "El monto objetivo es obligatorio")
    private Double targetAmount;

    @NotNull(message = "El monto actual es obligatorio")
    private Double currentAmount;

    @NotNull(message = "La fecha limite es obligatorio")
    private Date deadLine;

    @NotNull(message = "El estado no puede ser negativo")
    private statusAhorro status;

    public MetaAhorroDtoWeb() {}

    public MetaAhorroDtoWeb(Long idSavingsGoal, Integer idBudget,
                            String goalName, Double targetAmount,
                            Double currentAmount, Date deadLine, statusAhorro status) {
        this.idSavingsGoal = idSavingsGoal;
        this.idBudget = idBudget;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadLine = deadLine;
        this.status = status;
    }

    // Getters y Setters
    public Long getIdSavingsGoal() {
        return idSavingsGoal;
    }

    public void setIdSavingsGoal(Long idSavingsGoal) {
        this.idSavingsGoal = idSavingsGoal;
    }

    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public statusAhorro getStatus() {
        return status;
    }

    public void setStatus(statusAhorro status) {
        this.status = status;
    }
}
