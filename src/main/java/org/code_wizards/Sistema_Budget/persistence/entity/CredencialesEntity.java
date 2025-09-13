package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Credenciales")
@Data
public class CredencialesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredenciales;
    @Column(nullable = false)
    private Integer idUsuario;
    @Column(length = 100, unique = true, nullable = false)
    private String correo;
    @Column(length = 100, unique = true, nullable = false)
    private String contraseña;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaRegistro;

}
