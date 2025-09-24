package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.dto.ModTransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.service.TransaccionService;
import org.code_wizards.Sistema_Budget.dominio.exception.TransaccionYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.TransaccionNoExisteException;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Component
@ViewScoped
@Data
@Named("transaccionControllerWeb")
public class TransaccionControllerWeb implements Serializable {

    @Autowired
    private TransaccionService transaccionService;

    private List<TransaccionDtoWeb> transacciones;
    private TransaccionDtoWeb transaccionSeleccionada;
    private static final Logger logger = LoggerFactory.getLogger(TransaccionControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarTransacciones();
    }

    public void cargarTransacciones() {
        try {
            List<TransaccionDto> transaccionesDto = this.transaccionService.obtenerTodo();
            this.transacciones = transaccionesDto.stream()
                    .map(this::convertirAWeb)
                    .collect(Collectors.toList());
            this.transacciones.forEach(transaccion -> logger.info("Transacción cargada: " + transaccion.toString()));
        } catch (Exception e) {
            logger.error("Error al cargar transacciones: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar transacciones");
        }
    }

    public void agregarTransaccion() {
        this.transaccionSeleccionada = new TransaccionDtoWeb();
        logger.info("Preparando nueva transacción");
    }

    public void guardarTransaccion() {
        try {
            logger.info("Transacción a guardar: " + this.transaccionSeleccionada);

            if (this.transaccionSeleccionada.getIdTransaccion() == null) {
                TransaccionDto nuevaTransaccion = new TransaccionDto(
                        null,
                        this.transaccionSeleccionada.getIdCategory(),
                        this.transaccionSeleccionada.getDescriptionTransaction(),
                        this.transaccionSeleccionada.getTransactionAmount(),
                        this.transaccionSeleccionada.getTransactionDate(),
                        this.transaccionSeleccionada.getTypeTransaction()
                );

                this.transaccionService.guardarTransaccion(nuevaTransaccion);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Transacción agregada exitosamente");

            } else {
                ModTransaccionDto modTransaccion = new ModTransaccionDto(
                        this.transaccionSeleccionada.getIdCategory(),
                        this.transaccionSeleccionada.getDescriptionTransaction(),
                        this.transaccionSeleccionada.getTransactionAmount(),
                        new Date(), // Convertir LocalDate a Date si es necesario
                        this.transaccionSeleccionada.getTypeTransaction()
                );

                this.transaccionService.modificarTransaccion(this.transaccionSeleccionada.getIdTransaccion(), modTransaccion);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Transacción actualizada exitosamente");
            }

            cargarTransacciones();
            this.transaccionSeleccionada = null;

        } catch (TransaccionYaExisteException e) {
            mostrarMensaje(FacesMessage.SEVERITY_WARN, "Advertencia", e.getMessage());
        } catch (TransaccionNoExisteException e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        } catch (Exception e) {
            logger.error("Error al guardar transacción: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar transacción");
        }
    }

    public void eliminarTransaccion() {
        try {
            logger.info("Transacción a eliminar: " + this.transaccionSeleccionada);
            this.transaccionService.eliminarTransaccion(this.transaccionSeleccionada.getIdTransaccion());
            this.transaccionSeleccionada = null;

            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Transacción eliminada exitosamente");
            cargarTransacciones();

        } catch (TransaccionNoExisteException e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        } catch (Exception e) {
            logger.error("Error al eliminar transacción: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar transacción");
        }
    }

    private TransaccionDtoWeb convertirAWeb(TransaccionDto dto) {
        return new TransaccionDtoWeb(
                dto.idTransaccion(),
                dto.idCategory(),
                dto.descriptionTransaction(),
                dto.transactionAmount(),
                dto.transactionDate(),
                dto.typeTransaction()
        );
    }

    private void mostrarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
        PrimeFaces.current().ajax().update("formulario-transacciones:mensaje-emergente");
    }

    private void cerrarModal() {
        PrimeFaces.current().executeScript("PF('ventanaModalTransaccion').hide()");
        PrimeFaces.current().ajax().update("formulario-transacciones:tabla-transacciones");
    }
}

