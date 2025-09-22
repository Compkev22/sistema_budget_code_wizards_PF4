package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Gasto")
@Data
public class GastoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGasto;
    @Column(name = "id_presupuesto")
    private Long idPresupuesto;
    @Column(name = "id_Categoria")
    private Long idCategoria;
    @Column(name = "descripcion_Gasto", length = 200, nullable = false)
    private String descripcionGasto;
    @Column(name = "categoria_Gasto", length = 100)
    private String categoriaGasto;
    @Column(name = "monto_Gasto", precision = 10, scale = 2)
    private BigDecimal montoGasto;
    @Column(name = "fecha_Gasto")
    private LocalDate fechaGasto;
}