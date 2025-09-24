package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.service.CredencialesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Named("credencialesControllerWeb")
@ViewScoped
public class CredencialesControllerWeb implements Serializable {

    @Autowired
    private CredencialesService credencialesService;

    private List<CredencialesDtoWeb> listaCredenciales;

    // Campos para creación/edición
    private Long idCredencial;
    private Long userID;
    private String email;
    private String password;
    private LocalDateTime dateRecord;

    private boolean modoEdicion = false;

    @PostConstruct
    public void init() {
        cargarCredenciales();
    }

    public void cargarCredenciales() {
        List<CredencialesDto> creds = credencialesService.obtenerTodo();
        this.listaCredenciales = creds.stream()
                .map(c -> new CredencialesDtoWeb(
                        c.idCredencial(),
                        c.userID(),
                        c.email(),
                        c.password(),
                        c.dateRecord()
                ))
                .toList();
    }

    // Preparar formulario para nuevo registro
    public void prepararNuevaCredencial() {
        limpiarFormulario();
        this.modoEdicion = false;
    }

    // Preparar formulario para edición
    public void prepararEdicionCredencial(CredencialesDtoWeb cred) {
        this.modoEdicion = true;
        this.idCredencial = cred.getIdCredencial();
        this.userID = cred.getUserID();
        this.email = cred.getEmail();
        this.password = cred.getPassword();
        this.dateRecord = cred.getDateRecord();
    }

    // Guardar o actualizar credencial
    public void guardarCredencial() {
        try {
            if (modoEdicion) {
                ModCredencialesDto mod = new ModCredencialesDto(
                        userID != null ? userID.intValue() : null,
                        email,
                        password
                );
                credencialesService.modificarCredenciales(idCredencial, mod);
                mostrarMensaje("Credencial actualizada", email, FacesMessage.SEVERITY_INFO);
            } else {
                CredencialesDto nuevo = new CredencialesDto(
                        null,
                        userID,
                        email,
                        password,
                        LocalDateTime.now()
                );
                credencialesService.guardarCredenciales(nuevo);
                mostrarMensaje("Credencial agregada", email, FacesMessage.SEVERITY_INFO);
            }
            cargarCredenciales();
            limpiarFormulario();
        } catch (Exception e) {
            mostrarMensaje("Error al guardar credencial", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    // Eliminar credencial
    public void eliminarCredencial() {
        try {
            if (idCredencial != null) {
                credencialesService.eliminarCredenciales(idCredencial);
                cargarCredenciales();
                mostrarMensaje("Credencial eliminada", "ID: " + idCredencial, FacesMessage.SEVERITY_INFO);
                idCredencial = null;
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar credencial", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    // Limpiar formulario
    private void limpiarFormulario() {
        idCredencial = null;
        userID = null;
        email = null;
        password = null;
        dateRecord = null;
    }

    private void mostrarMensaje(String resumen, String detalle, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severidad, resumen, detalle));
    }

    // ========================
    // Getters y Setters
    // ========================
    public List<CredencialesDtoWeb> getListaCredenciales() { return listaCredenciales; }
    public boolean isModoEdicion() { return modoEdicion; }

    public Long getIdCredencial() { return idCredencial; }
    public void setIdCredencial(Long idCredencial) { this.idCredencial = idCredencial; }

    public Long getUserID() { return userID; }
    public void setUserID(Long userID) { this.userID = userID; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getDateRecord() { return dateRecord; }
    public void setDateRecord(LocalDateTime dateRecord) { this.dateRecord = dateRecord; }

    public String formatFecha(LocalDateTime fecha) {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
