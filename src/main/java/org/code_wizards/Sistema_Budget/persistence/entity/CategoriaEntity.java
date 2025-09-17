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

    @Column(nullable = false)
    private Integer idPresupuesto;

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

