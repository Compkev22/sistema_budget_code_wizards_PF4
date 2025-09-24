package org.code_wizards.Sistema_Budget.web.controller;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named("categoriaControllerWeb")
@ViewScoped

public class CategoriaControllerWeb implements Serializable {

    @Autowired
    private CategoriaService categoriaService;

    private List<CategoriaDtoWeb> listaCategorias;

    // Campos para creación/edición
    private Long categoryId;
    private Integer budgetId;
    private String categoryName;
    private String categoryType;
    private String identificationColor;
    private Double plannedAmount;
    private Double currentAmount;

    private boolean modoEdicion = false;

    @PostConstruct
    public void init(){
        cargarCategoria();
    }

    public void cargarCategoria() {
        List<CategoriaDto> cat = categoriaService.obtenerTodo();
        this.listaCategorias = cat.stream()
                .map(c -> new CategoriaDtoWeb(
                        c.categoryId(),
                        c.budgetId(),
                        c.categoryName(),
                        c.categoryType(),
                        c.identificationColor(),
                        c.plannedAmount(),
                        c.currentAmount()
                ))
                .toList();
    }

    public void prepararNuevaCategoria() {
        limpiarFormulario();
        this.modoEdicion = false;
    }

    public void prepararEdicionCategoria(CategoriaDtoWeb cat) {
        this.modoEdicion = true;
        this.categoryId = cat.getCategoryId();
        this.budgetId = cat.getBudgetId();
        this.categoryName = cat.getCategoryName();
        this.categoryType = cat.getCategoryType();
        this.identificationColor = cat.getIdentificationColor();
        this.plannedAmount = cat.getPlannedAmount();
        this.currentAmount = cat.getCurrentAmount();
    }

    public void guardarCategoria() {
        try {
            if (modoEdicion) {
                ModCategoriaDto mod = new ModCategoriaDto(
                        budgetId != null ? budgetId.intValue() : null,
                        categoryName,
                        categoryType,
                        identificationColor,
                        plannedAmount,
                        currentAmount
                );
                categoriaService.modificarCategoria(categoryId, mod);
                mostrarMensaje("Categoria actualizada", categoryName, FacesMessage.SEVERITY_INFO);
            } else {
                CategoriaDto nuevo = new CategoriaDto(
                        null,
                        budgetId,
                        categoryName,
                        categoryType,
                        identificationColor,
                        plannedAmount,
                        currentAmount
                );
                categoriaService.guardarCategoria(nuevo);
                mostrarMensaje("Categoria agregada", categoryName, FacesMessage.SEVERITY_INFO);
            }
            cargarCategoria();
            limpiarFormulario();
        } catch (Exception e) {
            mostrarMensaje("Error al guardar categoria", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarCategoria() {
        try {
            if (categoryId != null) {
                categoriaService.eliminarCategoria(categoryId);
                cargarCategoria();
                mostrarMensaje("Categoria eliminada", "ID: " + categoryId, FacesMessage.SEVERITY_INFO);
                categoryId = null;
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar categoria", e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    private void limpiarFormulario() {
        categoryId = null;
        budgetId = null;
        categoryName = null;
        categoryType = null;
        identificationColor = null;
        plannedAmount = null;
        currentAmount = null;
    }

    private void mostrarMensaje(String resumen, String detalle, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severidad, resumen, detalle));
    }

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public List<CategoriaDtoWeb> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaDtoWeb> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

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

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }
}
