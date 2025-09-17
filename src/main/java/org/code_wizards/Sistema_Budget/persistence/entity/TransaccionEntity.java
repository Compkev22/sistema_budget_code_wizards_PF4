package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "Transaccion")
@Data
public class TransaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    @Column(nullable = false)
    private Integer idCategoria;

    @Column(length = 200, nullable = false)
    private String descripcionTransaccion;

    @Column(nullable = false)
    private Double montoTransaccion;

    @CreationTimestamp
    @Column(name = "fechaTransaccion", updatable = false)
    private LocalDateTime fechaTransaccion;

    @Column(length = 50, nullable = false)
    private String tipoTransaccion;

}
