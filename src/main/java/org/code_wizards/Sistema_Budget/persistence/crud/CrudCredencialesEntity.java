package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.CredencialesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudCredencialesEntity extends CrudRepository<CredencialesEntity, Long> {

    CredencialesEntity findFirstByEmail(String email);
}
