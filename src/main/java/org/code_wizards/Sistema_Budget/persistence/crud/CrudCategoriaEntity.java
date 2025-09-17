package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CrudCategoriaEntity extends CrudRepository<CategoriaEntity, Long> {
    CategoriaEntity findFirstByNombreCategoria (String nombreCategoria);
}
