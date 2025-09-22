package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModIngresosDto;
import org.code_wizards.Sistema_Budget.dominio.exception.IngresosNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.IngresosYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.IngresosRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudIngresosEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.IngresosEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.IngresosMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngresosEntityRepository implements IngresosRepository {

    private final CrudIngresosEntity crudIngresosEntity;
    private final IngresosMapper ingresosMapper;

    public IngresosEntityRepository(CrudIngresosEntity crudIngresosEntity, IngresosMapper ingresosMapper) {
        this.crudIngresosEntity = crudIngresosEntity;
        this.ingresosMapper = ingresosMapper;
    }

    @Override
    public List<IngresosDto> obtenerTodo() {
        return this.ingresosMapper.toDto(this.crudIngresosEntity.findAll());
    }

    @Override
    public IngresosDto buscarPorCodigo(Long codigo) {
        IngresosEntity entity = this.crudIngresosEntity.findById(codigo)
                .orElseThrow(() -> new IngresosNoExisteException(codigo));
        return this.ingresosMapper.toDto(entity);
    }

    @Override
    public IngresosDto guardarIngresos(IngresosDto ingresosDto) {

        // Validar si el Presupuesto existe
        // if (!presupuestoRepository.existsById(ingresosDto.idBudget())) {
        //    throw new PresupuestoNoExisteException(ingresosDto.idBudget());
       // }

        // Validar si la Categoria existe
       // if (!categoriaRepository.existsById(ingresosDto.idCategory())) {
            //throw new CategoriaNoExisteException(ingresosDto.idCategory());
       //  }

        // Validar si ya existe un ingreso con la misma descripción y presupuesto
        IngresosEntity existente = this.crudIngresosEntity.findFirstByPresupuestoIdPresupuestoAndDescripcionIngreso(
                ingresosDto.idBudget(),
                ingresosDto.descriptionEntry()
        );
        if (existente != null) {
            throw new IngresosYaExisteException(ingresosDto.idBudget(), ingresosDto.descriptionEntry());
        }

        // Convertir DTO a Entity y guardar
        IngresosEntity entity = this.ingresosMapper.toEntity(ingresosDto);
        entity = this.crudIngresosEntity.save(entity);

        return this.ingresosMapper.toDto(entity);
    }


    @Override
    public IngresosDto modificarIngresos(Long codigo, ModIngresosDto modIngresos) {
        IngresosEntity entity = this.crudIngresosEntity.findById(codigo)
                .orElseThrow(() -> new IngresosNoExisteException(codigo));

        this.ingresosMapper.modificarEntityFromDto(modIngresos, entity);
        entity = this.crudIngresosEntity.save(entity);

        return this.ingresosMapper.toDto(entity);
    }

    @Override
    public void eliminarIngresos(Long codigo) {
        IngresosEntity entity = this.crudIngresosEntity.findById(codigo)
                .orElseThrow(() -> new IngresosNoExisteException(codigo));
        this.crudIngresosEntity.delete(entity);
    }
}
