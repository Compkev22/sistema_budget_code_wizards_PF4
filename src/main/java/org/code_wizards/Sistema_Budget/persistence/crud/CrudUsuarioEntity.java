package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudUsuarioEntity
        extends CrudRepository <UsuarioEntity, Long> {

    UsuarioEntity findFirstByNombre(String nombre);

}
