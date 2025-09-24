package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDtoWeb {
    private Long idTransaccion;

    @NotNull(message = "El id de la categoría es obligatorio")
    private Integer idCategory;

    @NotBlank(message = "La descripción de la Transacción es obligatoria")
    private String descriptionTransaction;

    @NotNull(message = "El monto de la Transacción es obligatorio")
    @Min(value = 0, message = "El monto de la Transacción no puede ser menor que 0")
    private Double transactionAmount;

    @PastOrPresent(message = "La fecha de la Transacción no puede ser posterior a Hoy")
    private LocalDate transactionDate;

    @NotBlank(message = "El tipo de Transacción es obligatorio")
    private String typeTransaction;
}
