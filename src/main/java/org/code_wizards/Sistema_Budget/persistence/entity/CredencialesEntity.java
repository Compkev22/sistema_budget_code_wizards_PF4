package org.code_wizards.Sistema_Budget.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Credenciales")
@Data
public class CredencialesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Credencial")
    private Long idCredencial;

    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "contraseña", length = 100, nullable = false)
    private String contrasena;

    @Column(name = "fecha_Registro",updatable = false)
    @CreationTimestamp
    private LocalDateTime dateRecord;
}
