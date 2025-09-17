package org.code_wizards.Sistema_Budget.persistence.crud;

import org.code_wizards.Sistema_Budget.persistence.entity.CategoriaEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.MetaAhorroEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CrudMetaAhorroEntity extends CrudRepository<MetaAhorroEntity, Long> {
    MetaAhorroEntity findFirstByNombreMeta (String nombreMeta);
}
