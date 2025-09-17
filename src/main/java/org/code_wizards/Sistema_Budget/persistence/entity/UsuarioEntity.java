package org.code_wizards.Sistema_Budget.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Usuario")
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario")
    private Long id_Usuario;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 100, nullable = false)
    private String apellido;
    @Column(length = 15, nullable = false)
    private String telefono;
    @Column(length = 10,  nullable = false)
    private String nit;

}
