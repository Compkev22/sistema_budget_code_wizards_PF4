package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.code_wizards.Sistema_Budget.dominio.statusAhorro;

import java.sql.Date;

@Entity
@Table(name= "MetaAhorro")
@Data
public class MetaAhorroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAhorro;

    @Column(nullable = false)
    private Integer idPresupuesto;

    @Column(length = 200, nullable = false)
    private String nombreMeta;

    @Column(length = 200, nullable = false)
    private Double montoObjetivo;

    @Column(length = 200, nullable = false)
    private Double montoActual;

    @Column(nullable = false)
    private Date fechaLimite;

    @Column(nullable = false)
    private statusAhorro estado;
}

