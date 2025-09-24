package org.code_wizards.Sistema_Budget.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Presupuesto")
@Data
public class PresupuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresupuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Usuario", nullable = false)  // FK a UsuarioEntity
    private UsuarioEntity usuario;

    @Column(length = 100, nullable = false)
    private String nombrePresupuesto;

    @Column(length = 100, nullable = false)
    private String periodoPresupuesto;

    @CreationTimestamp
    @Column(name= "fechaInicio", nullable = false, updatable = false)
    private LocalDateTime fechaInicio;

    @CreationTimestamp
    @Column(name= "fechaFin", nullable = false, updatable = false)
    private LocalDateTime fechaFin;

    @Column(length = 50, nullable = false)
    private String estado;

    @Column(name = "monto_totalplanificado", precision = 10, scale = 2)
    private BigDecimal monto_totalPlanificado;
}
