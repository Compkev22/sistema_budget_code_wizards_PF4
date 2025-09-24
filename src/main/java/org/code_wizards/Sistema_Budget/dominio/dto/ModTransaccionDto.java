package org.code_wizards.Sistema_Budget.dominio.dto;

import java.time.LocalDate;
import java.util.Date;

public record ModTransaccionDto (
        Integer idCategory,
        String descriptionTransaction,
        Double transactionAmount,
        LocalDate transactionDate,
        String typeTransaction
){}
