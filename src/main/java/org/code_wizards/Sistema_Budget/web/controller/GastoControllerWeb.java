package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Sistema_Budget.dominio.dto.GastoDto;
import org.code_wizards.Sistema_Budget.dominio.dto.GastoDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.service.GastoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
@Data
@Named("gastoControllerWeb")
public class GastoControllerWeb implements Serializable {

    @Autowired
    private GastoService gastoService;

    private List<GastoDtoWeb> gastos;
    private GastoDtoWeb gastoSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(GastoControllerWeb.class);

    @PostConstruct
    public void init() {
        this.cargarGastos();
    }

    public void cargarGastos() {
        try {
            List<GastoDto> gastosDto = gastoService.listarGastos();

            this.gastos = gastosDto.stream()
                    .map(this::convertirAWeb)
                    .collect(Collectors.toList());

            this.gastos.forEach(g -> logger.info("Gasto cargado: " + g.toString()));
        } catch (Exception e) {
            logger.error("Error al cargar gastos: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar los gastos");
        }
    }

    public void agregarGasto() {
        this.gastoSeleccionado = new GastoDtoWeb();
        logger.info("Preparando nuevo gasto");
    }

    public void guardarGasto() {
        try {
            logger.info("Gasto a guardar: " + this.gastoSeleccionado);
            GastoDto gastoParaGuardar = new GastoDto(
                    this.gastoSeleccionado.getIdGasto(),
                    this.gastoSeleccionado.getIdPresupuesto(),
                    this.gastoSeleccionado.getIdCategoria(),
                    this.gastoSeleccionado.getDescripcionGasto(),
                    this.gastoSeleccionado.getCategoriaGasto(),
                    this.gastoSeleccionado.getMontoGasto(),
                    this.gastoSeleccionado.getFechaGasto()
            );

            if (this.gastoSeleccionado.getIdGasto() == null) {
                this.gastoService.guardarGasto(gastoParaGuardar);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Gasto creado correctamente.");
            } else {
                this.gastoService.modificarGasto(gastoParaGuardar.idGasto(), gastoParaGuardar);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Gasto modificado correctamente.");
            }

            this.cargarGastos();
            this.gastoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar gasto: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar el gasto: " + e.getMessage());
        }
    }

    public void eliminarGasto() {
        try {
            logger.info("Gasto a eliminar: " + this.gastoSeleccionado);
            this.gastoService.eliminarGasto(this.gastoSeleccionado.getIdGasto());
            this.gastoSeleccionado = null;

            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Gasto eliminado exitosamente.");
            this.cargarGastos();

        } catch (Exception e) {
            logger.error("Error al eliminar gasto: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar el gasto: " + e.getMessage());
        }
    }

    private void mostrarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
        PrimeFaces.current().ajax().update("formulario-gastos:mensaje-emergente");
    }

    private GastoDtoWeb convertirAWeb(GastoDto dto) {
        return new GastoDtoWeb(
                dto.idGasto(),
                dto.idPresupuesto(),
                dto.idCategoria(),
                dto.descripcionGasto(),
                dto.categoriaGasto(),
                dto.montoGasto(),
                dto.fechaGasto()
        );
    }
}