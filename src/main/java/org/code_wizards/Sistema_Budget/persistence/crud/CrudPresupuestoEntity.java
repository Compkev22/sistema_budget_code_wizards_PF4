package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.persistence.entity.PresupuestoEntity;

public interface CrudPresupuestoEntity {
    PresupuestoEntity findFirstByNombrePresupuesto(String nombrePresupuesto);
}
