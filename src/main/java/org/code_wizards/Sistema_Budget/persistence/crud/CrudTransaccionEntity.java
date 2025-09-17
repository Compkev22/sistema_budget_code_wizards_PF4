package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.TransaccionEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudTransaccionEntity extends CrudRepository<TransaccionEntity, Long> {
    TransaccionEntity findFirstByIdTransaccionAndDescripcionTransaccion(Integer idTransaccion, String descripcionTransaccion);
}
