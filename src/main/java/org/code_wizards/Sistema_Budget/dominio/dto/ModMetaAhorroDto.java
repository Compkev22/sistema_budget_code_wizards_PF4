package org.code_wizards.Sistema_Budget.dominio.dto;

import org.code_wizards.Sistema_Budget.dominio.statusAhorro;
import java.time.LocalDate;
import java.util.Date;

public record ModMetaAhorroDto (
        Integer budgetId,
        String goalName,
        Double targetAmount,
        Double currentAmount,
        Date deadLine,
        statusAhorro status
){

}