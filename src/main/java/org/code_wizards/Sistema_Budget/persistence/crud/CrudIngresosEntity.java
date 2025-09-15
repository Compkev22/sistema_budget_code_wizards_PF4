package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.IngresosEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudIngresosEntity extends CrudRepository<IngresosEntity, Long> {

    // Busca un ingreso por idPresupuesto y descripcionIngreso
    IngresosEntity findFirstByIdPresupuestoAndDescripcionIngreso(Integer idPresupuesto, String descripcionIngreso);
}
