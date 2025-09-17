package org.code_wizards.Sistema_Budget.dominio.dto;

import java.util.Date;

public record ModTransaccionDto (
        Integer idCategory,
        String descriptionTransaction,
        Double transactionAmount,
        Date transactionDate,
        String typeTransaction
){}
