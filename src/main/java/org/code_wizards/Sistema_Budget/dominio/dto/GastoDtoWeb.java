package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoDtoWeb implements Serializable {
    private Long idGasto;
    @NotNull(message = "El ID del presupuesto no puede estar vacío")
    private Long idPresupuesto;
    @NotNull(message = "El ID de la categoría no puede estar vacío")
    private Long idCategoria;
    @NotBlank(message = "La descripción del gasto no puede estar vacía")
    private String descripcionGasto;
    private String categoriaGasto;
    private BigDecimal montoGasto;
    private LocalDate fechaGasto;
}