package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDto;
import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.dto.ModIngresosDto;
import org.code_wizards.Sistema_Budget.dominio.service.IngresosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Named("ingresosControllerWeb")
@ViewScoped
public class IngresosControllerWeb implements Serializable {

    @Autowired
    private IngresosService ingresosService;

    private List<IngresosDtoWeb> listaIngresos;

    // Campos para creación/edición
    private Long idIngreso;
    private Long idBudget;
    private Integer idCategory;
    private String descriptionEntry;
    private Double incomeAmount;
    private LocalDate entryDate;

    private boolean modoEdicion = false;

    @PostConstruct
    public void init() {
        cargarIngresos();
    }

    public void cargarIngresos() {
        List<IngresosDto> ingresos = ingresosService.obtenerTodo();
        this.listaIngresos = ingresos.stream()
                .map(i -> new IngresosDtoWeb(
                        i.idIngreso(),
                        i.idBudget(),
                        i.idCategory(),
                        i.descriptionEntry(),
                        i.incomeAmount(),
                        i.entryDate()
                ))
                .toList();
    }

    // Preparar formulario para nuevo ingreso
    public void prepararNuevoIngreso() {
        limpiarFormulario();
        this.modoEdicion = false;
    }

    // Preparar formulario para edición
    public void prepararEdicionIngreso(IngresosDtoWeb ingreso) {
        this.modoEdicion = true;
        this.idIngreso = ingreso.getIdIngreso();
        this.idBudget = ingreso.getIdBudget();
        this.idCategory = ingreso.getIdCategory();
        this.descriptionEntry = ingreso.getDescriptionEntry();
        this.incomeAmount = ingreso.getIncomeAmount();
        this.entryDate = ingreso.getEntryDate();
    }

    // Guardar o actualizar ingreso
    public void guardarIngreso() {
        try {
            if (modoEdicion) {
                ModIngresosDto mod = new ModIngresosDto(
                        idBudget != null ? idBudget.intValue() : null,
                        idCategory,
                        descriptionEntry,
                        incomeAmount
                );
                ingresosService.modificarIngresos(idIngreso, mod);
                mostrarMensaje("Ingreso actualizado", descriptionEntry, FacesMessage.SEVERITY_INFO);
            } else {
                IngresosDto nuevo = new IngresosDto(
                        null,
                        idBudget,
                        idCategory,
                        descriptionEntry,
                        incomeAmount,
                        LocalDate.now()
                );
                ingresosService.guardarIngresos(nuevo);
                mostrarMensaje("Ingreso agregado", descriptionEntry, FacesMessage.SEVERITY_INFO);
            }
            cargarIngresos();
            limpiarFormulario();
        } catch (Exception e) {
            mostrarMensaje("Error al guardar ingreso", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    // Eliminar ingreso
    public void eliminarIngreso() {
        try {
            if (idIngreso != null) {
                ingresosService.eliminarIngresos(idIngreso);
                cargarIngresos();
                mostrarMensaje("Ingreso eliminado", "ID: " + idIngreso, FacesMessage.SEVERITY_INFO);
                idIngreso = null;
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar ingreso", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    // Limpiar formulario
    private void limpiarFormulario() {
        idIngreso = null;
        idBudget = null;
        idCategory = null;
        descriptionEntry = null;
        incomeAmount = null;
        entryDate = null;
    }

    private void mostrarMensaje(String resumen, String detalle, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severidad, resumen, detalle));
    }

    public Double getTotalIngresos() {
        if (listaIngresos == null) return 0.0;
        return listaIngresos.stream()
                .mapToDouble(i -> i.getIncomeAmount() != null ? i.getIncomeAmount() : 0.0)
                .sum();
    }

    // ========================
    // Getters y Setters
    // ========================
    public List<IngresosDtoWeb> getListaIngresos() { return listaIngresos; }
    public boolean isModoEdicion() { return modoEdicion; }

    public Long getIdIngreso() { return idIngreso; }
    public void setIdIngreso(Long idIngreso) { this.idIngreso = idIngreso; }

    public Long getIdBudget() { return idBudget; }
    public void setIdBudget(Long idBudget) { this.idBudget = idBudget; }

    public Integer getIdCategory() { return idCategory; }
    public void setIdCategory(Integer idCategory) { this.idCategory = idCategory; }

    public String getDescriptionEntry() { return descriptionEntry; }
    public void setDescriptionEntry(String descriptionEntry) { this.descriptionEntry = descriptionEntry; }

    public Double getIncomeAmount() { return incomeAmount; }
    public void setIncomeAmount(Double incomeAmount) { this.incomeAmount = incomeAmount; }

    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    public String formatFecha(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
