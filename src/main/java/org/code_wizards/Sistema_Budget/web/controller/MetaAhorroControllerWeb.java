package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.dto.ModMetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.service.MetaAhorroService;
import org.code_wizards.Sistema_Budget.dominio.statusAhorro;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Named("metaAhorroControllerWeb")
@ViewScoped

public class MetaAhorroControllerWeb {

    @Autowired
    private MetaAhorroService metaAhorroService;

    private List<MetaAhorroDtoWeb> listaMetaAhorro;

    // Campos para creación/edición
    private Long idSavingsGoal;
    private Integer idBudget;
    private String goalName;
    private Double targetAmount;
    private Double currentAmount;
    private Date deadLine;
    private statusAhorro status;

    private boolean modoEdicion = false;

    @PostConstruct
    public void init(){
        cargarMetaAhorro();
    }

    public void cargarMetaAhorro() {
        List<MetaAhorroDto> met = metaAhorroService.obtenerTodos();
        this.listaMetaAhorro = met.stream()
                .map(m -> new MetaAhorroDtoWeb(
                        m.idSavingsGoal(),
                        m.idBudget(),
                        m.goalName(),
                        m.targetAmount(),
                        m.currentAmount(),
                        m.deadLine(),
                        m.status()
                ))
                .toList();
    }

    public void prepararNuevaMetaAhorro() {
        limpiarFormulario();
        this.modoEdicion = false;
    }

    public void prepararEdicionMetaAhorro(MetaAhorroDtoWeb met) {
        this.modoEdicion = true;
        this.idSavingsGoal = met.getIdSavingsGoal();
        this.idBudget = met.getIdBudget();
        this.goalName = met.getGoalName();
        this.targetAmount = met.getTargetAmount();
        this.currentAmount = met.getCurrentAmount();
        this.deadLine = met.getDeadLine();
        this.status = met.getStatus();
    }


    private void limpiarFormulario() {
        idSavingsGoal = null;
        idBudget = null;
        goalName = null;
        targetAmount = null;
        currentAmount = null;
        deadLine = null;
        status = null;
    }

    public void guardarMetaAhorro() {
        try {
            if (modoEdicion) {
                ModMetaAhorroDto mod = new ModMetaAhorroDto(
                        idBudget != null ? idBudget.intValue() : null,
                        goalName,
                        targetAmount,
                        currentAmount,
                        deadLine,
                        status
                );
                metaAhorroService.modificarAhorro(idSavingsGoal, mod);
                mostrarMensaje("Meta Ahorro actualizada", goalName, FacesMessage.SEVERITY_INFO);
            } else {
                MetaAhorroDto nuevo = new MetaAhorroDto(
                        null,
                        idBudget,
                        goalName,
                        targetAmount,
                        currentAmount,
                        deadLine,
                        status
                );
                metaAhorroService.guardarAhorro(nuevo);
                mostrarMensaje("Meta Ahorro agregada", goalName, FacesMessage.SEVERITY_INFO);
            }
            cargarMetaAhorro();
            limpiarFormulario();
        } catch (Exception e) {
            mostrarMensaje("Error al guardar Meta Ahorro", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarMetaAhorro() {
        try {
            if (idSavingsGoal != null) {
                metaAhorroService.eliminarAhorro(idSavingsGoal);
                cargarMetaAhorro();
                mostrarMensaje("Meta Ahorro eliminada", "ID: " + idSavingsGoal, FacesMessage.SEVERITY_INFO);
                idSavingsGoal = null;
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar Meta Ahorro", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    private void mostrarMensaje(String resumen, String detalle, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severidad, resumen, detalle));
    }

    public MetaAhorroService getMetaAhorroService() {
        return metaAhorroService;
    }

    public void setMetaAhorroService(MetaAhorroService metaAhorroService) {
        this.metaAhorroService = metaAhorroService;
    }

    public List<MetaAhorroDtoWeb> getListaMetasAhorro() {
        return listaMetaAhorro;
    }

    public void setListaMetasAhorro(List<MetaAhorroDtoWeb> listaMetasAhorro) {
        this.listaMetaAhorro = listaMetasAhorro;
    }

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

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }


}
