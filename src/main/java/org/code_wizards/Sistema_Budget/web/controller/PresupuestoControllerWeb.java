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
import org.code_wizards.Sistema_Budget.dominio.exception.PresupuestoConMetaAhorroException;
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
        this.estadosDisponibles = Arrays.asList(Status.values());
    }

    public void cargarPresupuestos() {
        try {
            // 1. Obtiene la lista de DTOs (records) del servicio
            List<PresupuestoDto> presupuestosDto = presupuestoService.listarPresupuestos();

            // 2. Convierte cada DTO (record) en un DTOWeb (clase con getters/setters)
            //    y asigna el resultado a la lista que la vista consumirá.
            this.presupuestos = presupuestosDto.stream()
                    .map(this::convertirAWeb) // Aquí se usa el metodo que convierte
                    .collect(Collectors.toList());

            this.presupuestos.forEach(p -> logger.info("Presupuesto cargado: " + p.toString()));
        } catch (Exception e) {
            logger.error("Error al cargar presupuestos: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar los presupuestos");
        }
    }

    public void agregarPresupuesto() {
        this.presupuestoSeleccionado = new PresupuestoDtoWeb();

        // Set a default status for a new budget
        this.presupuestoSeleccionado.setStatus(Status.ACTIVO);

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

        }catch (PresupuestoConMetaAhorroException e) {
            // Captura la excepción específica y muestra el mensaje predefinido
            logger.warn("Intento de eliminar presupuesto con metas de ahorro: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_WARN, "Advertencia", e.getMessage());
        } catch (Exception e) {
            logger.error("Error al eliminar presupuesto: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar el presupuesto: " + e.getMessage());
        }
    }

    private void mostrarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
        PrimeFaces.current().ajax().update("formulario-presupuestos:mensaje-emergente");
    }

    public void prepararParaEdicion(PresupuestoDtoWeb presupuesto) {
        // Crea una nueva instancia para evitar problemas de estado con el objeto de la tabla.
        // Esto asegura que se refresque por completo el modelo en el diálogo.
        this.presupuestoSeleccionado = new PresupuestoDtoWeb();
        this.presupuestoSeleccionado.setIdPresupuesto(presupuesto.getIdPresupuesto());
        this.presupuestoSeleccionado.setIdUser(presupuesto.getIdUser());
        this.presupuestoSeleccionado.setBudgetName(presupuesto.getBudgetName());
        this.presupuestoSeleccionado.setBudgetPeriod(presupuesto.getBudgetPeriod());
        this.presupuestoSeleccionado.setStartDate(presupuesto.getStartDate());
        this.presupuestoSeleccionado.setEndDate(presupuesto.getEndDate());
        this.presupuestoSeleccionado.setStatus(presupuesto.getStatus());
        this.presupuestoSeleccionado.setTotalPlannedAmount(presupuesto.getTotalPlannedAmount());

        logger.info("Preparando presupuesto para edición: " + this.presupuestoSeleccionado.getBudgetName());
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