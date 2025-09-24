package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.code_wizards.Sistema_Budget.dominio.statusAhorro;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name= "MetaAhorro")
@Data
public class MetaAhorroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAhorro;

    @ManyToOne
    @JoinColumn(name = "idPresupuesto", nullable = false)
    private PresupuestoEntity presupuesto;

    @Column(length = 200, nullable = false)
    private String nombreMeta;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montoObjetivo;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montoActual;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private statusAhorro estado;
}
