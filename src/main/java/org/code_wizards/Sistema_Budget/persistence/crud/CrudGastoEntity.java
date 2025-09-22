package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.GastoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudGastoEntity extends CrudRepository<GastoEntity, Long> {
    List<GastoEntity> findAllByIdPresupuesto(Long idPresupuesto);
}