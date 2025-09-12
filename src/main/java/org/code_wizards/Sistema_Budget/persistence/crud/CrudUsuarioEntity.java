package org.code_wizards.Sistema_Budget.persistence.crud;

import org.springframework.data.repository.CrudRepository;

public interface CrudUsuarioEntity
        extends CrudRepository <UsuarioEntity, Long> {

    UsuarioEntity findFirstByNombre(String nombre);

}
