package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.dominio.exception.PresupuestoNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.PresupuestoYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.PresupuestoRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudPresupuestoEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.PresupuestoEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.PresupuestoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PresupuestoEntityRepository implements PresupuestoRepository {
    private final CrudPresupuestoEntity crudPresupuestoEntity;
    private final PresupuestoMapper presupuestoMapper;

    public PresupuestoEntityRepository(CrudPresupuestoEntity crudPresupuestoEntity, PresupuestoMapper presupuestoMapper) {
        this.crudPresupuestoEntity = crudPresupuestoEntity;
        this.presupuestoMapper = presupuestoMapper;
    }

    @Override
    public List<PresupuestoDto> obtenerTodo() { return this.presupuestoMapper.toDto(this.crudPresupuestoEntity.findAll()); }

    @Override
    public PresupuestoDto buscarPorCodigo(Long idPresupuesto) {
        PresupuestoEntity entity = this.crudPresupuestoEntity.findById(idPresupuesto)
                .orElseThrow(() -> new PresupuestoNoExisteException(idPresupuesto));
        return this.presupuestoMapper.toDto(entity);
    }

    @Override
    public PresupuestoDto guardarPresupuesto(PresupuestoDto presupuestoDto) {
        // 1. Validar si ya existe un presupuesto con el mismo nombre
        // Se utiliza el metodo que ya definiste en tu CrudPresupuestoEntity
        PresupuestoEntity existente = this.crudPresupuestoEntity.findFirstByNombrePresupuesto(
                presupuestoDto.nombrePresupuesto() // Asegúrate de que este sea el getter correcto
        );

        // 2. Si el presupuesto ya existe, lanzar una excepción
        if (existente != null) {
            throw new PresupuestoYaExisteException("El presupuesto con el nombre '" + presupuestoDto.nombrePresupuesto() + "' ya existe.");
        }

        // 3. Convertir el DTO de entrada a una entidad
        PresupuestoEntity entity = this.presupuestoMapper.toEntity(presupuestoDto);

        // 4. Guardar la entidad en la base de datos
        entity = this.crudPresupuestoEntity.save(entity);

        // 5. Convertir la entidad guardada de vuelta a un DTO y retornarla
        return this.presupuestoMapper.toDto(entity);
    }

    @Override
    public PresupuestoDto modificarPresupuesto(Long idPresupuesto, PresupuestoDto modPresupuesto) {
        PresupuestoEntity presupuesto = this.crudPresupuestoEntity.findById(idPresupuesto).orElse(null);
        if (presupuesto == null) {
            throw new PresupuestoNoExisteException(idPresupuesto);
        } else {
            this.presupuestoMapper.modificarEntityFromDto(modPresupuesto, presupuesto);
            return presupuestoMapper.toDto(this.crudPresupuestoEntity.save(presupuesto));
        }
    }

    @Override
    public void eliminarPresupuesto(Long idPresupuesto) {
        this.crudPresupuestoEntity.deleteById(idPresupuesto);
    }
}
