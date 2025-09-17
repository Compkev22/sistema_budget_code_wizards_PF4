package org.code_wizards.Sistema_Budget.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import  java.time.LocalDate;

public record TransaccionDto (
   Long idTransaccion,
   @NotNull(message = "El id de la categoria es obligatorio")
   Integer idCategory,
   @NotBlank(message = "La descripcion de la Transaccion es obligatoria")
   String descriptionTransaction,
   @NotNull(message = "El monto de la Transaccion es obligatorio")
   @Min(value = 0, message = "El monto de la Transaccion no puede ser menor que 0")
   Double transactionAmount,
   @PastOrPresent(message = "La fecha de la Transaccion no puede ser posterior a Hoy")
   LocalDate transactionDate,
   @NotBlank(message = "El tipo de Transaccion es obligatorio")
   String typeTransaction
) {}
