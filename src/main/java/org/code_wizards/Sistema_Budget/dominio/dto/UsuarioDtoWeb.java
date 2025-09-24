package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoWeb {
    private Long codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastnameUser;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telephone;

    @NotBlank(message = "El nit es obligatorio")
    private String nitUser;
}
