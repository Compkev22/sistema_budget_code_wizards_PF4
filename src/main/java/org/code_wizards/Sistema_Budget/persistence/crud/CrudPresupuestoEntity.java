package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.PresupuestoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudPresupuestoEntity extends CrudRepository<PresupuestoEntity, Long> {
    PresupuestoEntity findFirstByNombrePresupuesto(String nombrePresupuesto);
}
