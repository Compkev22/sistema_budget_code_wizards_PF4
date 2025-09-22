package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.GastoDto;
import org.code_wizards.Sistema_Budget.dominio.exception.GastoNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.GastoRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudGastoEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.GastoEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.GastoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GastoEntityRepository implements GastoRepository {
    private final CrudGastoEntity crudGastoEntity;
    private final GastoMapper gastoMapper;

    public GastoEntityRepository(CrudGastoEntity crudGastoEntity, GastoMapper gastoMapper) {
        this.crudGastoEntity = crudGastoEntity;
        this.gastoMapper = gastoMapper;
    }

    @Override
    public List<GastoDto> obtenerTodo() {
        return this.gastoMapper.toDto((List<GastoEntity>) this.crudGastoEntity.findAll());
    }

    @Override
    public List<GastoDto> obtenerPorPresupuesto(Long idPresupuesto) {
        List<GastoEntity> gastos = this.crudGastoEntity.findAllByIdPresupuesto(idPresupuesto);
        return this.gastoMapper.toDto(gastos);
    }

    @Override
    public GastoDto buscarPorCodigo(Long idGasto) {
        GastoEntity entity = this.crudGastoEntity.findById(idGasto)
                .orElseThrow(() -> new GastoNoExisteException(idGasto));
        return this.gastoMapper.toDto(entity);
    }

    @Override
    public GastoDto guardarGasto(GastoDto gastoDto) {
        GastoEntity entity = this.gastoMapper.toEntity(gastoDto);
        entity = this.crudGastoEntity.save(entity);
        return this.gastoMapper.toDto(entity);
    }

    @Override
    public GastoDto modificarGasto(Long idGasto, GastoDto modGasto) {
        GastoEntity gasto = this.crudGastoEntity.findById(idGasto).orElse(null);
        if (gasto == null) {
            throw new GastoNoExisteException(idGasto);
        } else {
            this.gastoMapper.modificarEntityFromDto(modGasto, gasto);
            return gastoMapper.toDto(this.crudGastoEntity.save(gasto));
        }
    }

    @Override
    public void eliminarGasto(Long idGasto) {
        if (!this.crudGastoEntity.existsById(idGasto)) {
            throw new GastoNoExisteException(idGasto);
        }
        this.crudGastoEntity.deleteById(idGasto);
    }
}