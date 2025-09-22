package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "Categoria")
@Data
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPresupuesto", nullable = false) // FK hacia PresupuestoEntity
    private PresupuestoEntity presupuesto;

    @Column(length = 200, nullable = false)
    private String nombreCategoria;

    @Column(length = 200, nullable = false)
    private String tipoCategoria;

    @Column(length = 200, nullable = false)
    private String colorIdentificacion;

    @Column(nullable = false)
    private Double montoPlanificado;

    @Column(nullable = false)
    private Double montoActual;
}
