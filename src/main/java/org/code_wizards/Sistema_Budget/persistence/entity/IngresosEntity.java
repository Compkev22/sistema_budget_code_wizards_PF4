package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name="Ingreso")
@Data
public class IngresosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Presupuesto", nullable = false)  // FK hacia PresupuestoEntity
    private PresupuestoEntity presupuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria", nullable = false)   // FK hacia CategoriaEntity
    private CategoriaEntity categoria;

    @Column(length = 200, nullable = false)
    private String descripcionIngreso;

    @Column(nullable = false)
    private Double montoIngreso;

    @CreationTimestamp
    @Column(name = "fechaIngreso", updatable = false)
    private LocalDateTime fechaIngreso;
}
