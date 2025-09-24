package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Sistema_Budget.dominio.Status;
import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.service.PresupuestoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
@Data
@Named("presupuestoControllerWeb")
public class PresupuestoControllerWeb implements Serializable {

    @Autowired
    private PresupuestoService presupuestoService;

    private List<PresupuestoDtoWeb> presupuestos; // Cambia el tipo de la lista
    private PresupuestoDtoWeb presupuestoSeleccionado;
    private List<Status> estadosDisponibles;
    private static final Logger logger = LoggerFactory.getLogger(PresupuestoControllerWeb.class);

    @PostConstruct
    public void init() {
        this.cargarPresupuestos();
    }

    public void cargarPresupuestos() {
        try {
            // 1. Obtiene la lista de DTOs (records) del servicio
            List<PresupuestoDto> presupuestosDto = presupuestoService.listarPresupuestos();

            // 2. Convierte cada DTO (record) en un DTOWeb (clase con getters/setters)
            //    y asigna el resultado a la lista que la vista consumirá.
            this.presupuestos = presupuestosDto.stream()
                    .map(this::convertirAWeb) // Aquí se usa el método que convierte
                    .collect(Collectors.toList());

            this.presupuestos.forEach(p -> logger.info("Presupuesto cargado: " + p.toString()));
        } catch (Exception e) {
            logger.error("Error al cargar presupuestos: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar los presupuestos");
        }
    }

    public void agregarPresupuesto() {
        this.presupuestoSeleccionado = new PresupuestoDtoWeb();
        logger.info("Preparando nuevo presupuesto");
    }

    public void guardarPresupuesto() {
        try {
            logger.info("Presupuesto a guardar: " + this.presupuestoSeleccionado);
            PresupuestoDto presupuestoParaGuardar = new PresupuestoDto(
                    this.presupuestoSeleccionado.getIdPresupuesto(),
                    this.presupuestoSeleccionado.getIdUser(),
                    this.presupuestoSeleccionado.getBudgetName(),
                    this.presupuestoSeleccionado.getBudgetPeriod(),
                    this.presupuestoSeleccionado.getStartDate(),
                    this.presupuestoSeleccionado.getEndDate(),
                    this.presupuestoSeleccionado.getStatus(),
                    this.presupuestoSeleccionado.getTotalPlannedAmount()
            );

            if (this.presupuestoSeleccionado.getIdPresupuesto() == null) {
                this.presupuestoService.guardarPresupuesto(presupuestoParaGuardar);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Presupuesto creado correctamente.");
            } else {
                this.presupuestoService.modificarPresupuesto(presupuestoParaGuardar.idPresupuesto(), presupuestoParaGuardar);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Presupuesto modificado correctamente.");
            }

            this.cargarPresupuestos();
            this.presupuestoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar presupuesto: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar el presupuesto: " + e.getMessage());
        }
    }

    public void eliminarPresupuesto() {
        try {
            logger.info("Presupuesto a eliminar: " + this.presupuestoSeleccionado);
            this.presupuestoService.eliminarPresupuesto(this.presupuestoSeleccionado.getIdPresupuesto());
            this.presupuestoSeleccionado = null;

            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Presupuesto eliminado exitosamente.");
            this.cargarPresupuestos();

        } catch (Exception e) {
            logger.error("Error al eliminar presupuesto: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar el presupuesto: " + e.getMessage());
        }
    }

    private void mostrarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
        PrimeFaces.current().ajax().update("formulario-presupuestos:mensaje-emergente");
    }


    public PresupuestoDtoWeb convertirAWeb(PresupuestoDto dto) {
        return new PresupuestoDtoWeb(
                dto.idPresupuesto(),
                dto.idUser(),
                dto.budgetName(),
                dto.budgetPeriod(),
                dto.startDate(),
                dto.endDate(),
                dto.status(),
                dto.totalPlannedAmount()
        );
    }
}