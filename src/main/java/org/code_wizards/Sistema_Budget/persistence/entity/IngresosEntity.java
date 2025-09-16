package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
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

    @Column(nullable = false)
    private Integer idPresupuesto;

    @Column(nullable = false)
    private Integer idCategoria;

    @Column(length = 200, nullable = false)
    private String descripcionIngreso;

    @Column(nullable = false)
    private Double montoIngreso;

    @CreationTimestamp
    @Column(name = "fechaIngreso", updatable = false)
    private LocalDateTime fechaIngreso;
}