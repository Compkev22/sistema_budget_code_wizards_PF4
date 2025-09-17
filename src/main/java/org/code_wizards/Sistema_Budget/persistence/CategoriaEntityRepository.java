package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.exception.CategoriaExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.CategoriaNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.CategoriaRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudCategoriaEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.CategoriaEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.CategoriaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaEntityRepository implements CategoriaRepository {

    private final CrudCategoriaEntity crudCategoriaEntity;
    private final CategoriaMapper categoriaMapper;

    public CategoriaEntityRepository(CrudCategoriaEntity crudCategoriaEntity, CategoriaMapper categoriaMapper) {
        this.crudCategoriaEntity = crudCategoriaEntity;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaDto> obtenerTodo() {
        return categoriaMapper.toDto(crudCategoriaEntity.findAll());
    }

    @Override
    public CategoriaDto buscarPorCodigo(Long idCategoria) {
        CategoriaEntity categoria = crudCategoriaEntity.findById(idCategoria).orElse(null);
        if (categoria == null) {
            throw new CategoriaNoExisteException(idCategoria);
        }
        return categoriaMapper.toDto(categoria);
    }

    @Override
    public CategoriaDto guardarCategoria(CategoriaDto categoriaDto) {
        // Validación: ya existe una categoría con mismo nombre dentro del mismo presupuesto
        CategoriaEntity existente = crudCategoriaEntity
                .findFirstByNombreCategoria(
                        categoriaDto.categoryName()
                );

        if (existente != null) {
            throw new CategoriaExisteException(categoriaDto.categoryName());
        }

        CategoriaEntity nueva = categoriaMapper.toEntity(categoriaDto);
        crudCategoriaEntity.save(nueva);
        return categoriaMapper.toDto(nueva);
    }

    @Override
    public CategoriaDto modificarCategoria(Long idCategoria, ModCategoriaDto modCategoria) {
        CategoriaEntity existente = crudCategoriaEntity.findById(idCategoria).orElse(null);
        if (existente == null) {
            throw new CategoriaNoExisteException(idCategoria);
        }
        this.categoriaMapper.modificarEntityFromDto(modCategoria, existente);
        return categoriaMapper.toDto(this.crudCategoriaEntity.save(existente));
    }

    @Override
    public void eliminarCategoria(Long idCategoria) {
        CategoriaEntity existente = crudCategoriaEntity.findById(idCategoria).orElse(null);
        if (existente == null) {
            throw new CategoriaNoExisteException(idCategoria);
        }

        crudCategoriaEntity.deleteById(idCategoria);
    }
}
