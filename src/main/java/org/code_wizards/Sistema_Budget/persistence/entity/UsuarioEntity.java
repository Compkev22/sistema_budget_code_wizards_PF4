package org.code_wizards.Sistema_Budget.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Usuario")
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(length = 100, unique = true, nullable = false)
    private String nombre;
    @Column(length = 100, unique = true, nullable = false)
    private String apellido;
    @Column(length = 15, unique = true, nullable = false)
    private String telefono;
    @Column(length = 10, unique = true, nullable = false)
    private String nit;

}
