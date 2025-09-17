package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModMetaAhorroDto;
import org.code_wizards.Sistema_Budget.persistence.entity.MetaAhorroEntity;
import java.util.List;

public interface MetaAhorroRepository {
    List<MetaAhorroDto> obtenerTodos();
    public MetaAhorroDto buscarPorCodigos(Long codigo);
    MetaAhorroDto guardarAhorro(MetaAhorroDto metaAhorroDto);
    MetaAhorroDto modificarAhorro(Long codigo, ModMetaAhorroDto modMetaAhorro);
    void eliminarAhorro(Long codigo);
}