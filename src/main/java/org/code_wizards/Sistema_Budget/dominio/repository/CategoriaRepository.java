package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.typeCategory;
import org.code_wizards.Sistema_Budget.persistence.entity.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    List<CategoriaDto> obtenerTodo();
    public CategoriaDto buscarPorCodigo(Long codigo);
    CategoriaDto guardarCategoria(CategoriaDto credencialesDto);
    CategoriaDto modificarCategoria(Long codigo, ModCategoriaDto modCategoria);
    void eliminarCategoria(Long codigo);
}