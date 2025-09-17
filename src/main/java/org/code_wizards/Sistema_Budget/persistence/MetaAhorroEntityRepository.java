package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModMetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.exception.MetaAhorroExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.MetaAhorroNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.MetaAhorroRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudMetaAhorroEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.MetaAhorroEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.MetaAhorroMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetaAhorroEntityRepository implements MetaAhorroRepository {

    private final CrudMetaAhorroEntity crudMetaAhorroEntity;
    private final MetaAhorroMapper metaAhorroMapper;

    public MetaAhorroEntityRepository(CrudMetaAhorroEntity crudMetaAhorroEntity, MetaAhorroMapper metaAhorroMapper) {
        this.crudMetaAhorroEntity = crudMetaAhorroEntity;
        this.metaAhorroMapper = metaAhorroMapper;
    }

    @Override
    public List<MetaAhorroDto> obtenerTodos() {
        return metaAhorroMapper.toDto(crudMetaAhorroEntity.findAll());
    }

    @Override
    public MetaAhorroDto buscarPorCodigos(Long codigo) {
        MetaAhorroEntity ahorro = crudMetaAhorroEntity.findById(codigo).orElse(null);
        if (ahorro == null) {
            throw new MetaAhorroNoExisteException(codigo);
        }
        return metaAhorroMapper.toDto(ahorro);
    }

    @Override
    public MetaAhorroDto guardarAhorro(MetaAhorroDto metaAhorroDto) {
        // Validar si ya existe
        if (this.crudMetaAhorroEntity.findFirstByNombreMeta(metaAhorroDto.goalName()) != null) {
            throw new MetaAhorroExisteException(metaAhorroDto.goalName());
        }
        MetaAhorroEntity ahorros = this.metaAhorroMapper.toEntity(metaAhorroDto);

        // Guardar en base de datos
        this.crudMetaAhorroEntity.save(ahorros);

        // Retornar DTO
        return this.metaAhorroMapper.toDto(ahorros);
    }

    @Override
    public MetaAhorroDto modificarAhorro(Long codigo, ModMetaAhorroDto modMetaAhorro) {
        MetaAhorroEntity metaAhorro = this.crudMetaAhorroEntity.findById(codigo).orElse(null);
        //Excepcion
        if (metaAhorro == null) {
            throw new MetaAhorroNoExisteException(codigo);
        }
        this.metaAhorroMapper.modificarEntityFromDtoAhorro(modMetaAhorro, metaAhorro);
        return metaAhorroMapper.toDto(this.crudMetaAhorroEntity.save(metaAhorro));
    }

    @Override
    public void eliminarAhorro(Long idAhorro) {
        MetaAhorroEntity existente = crudMetaAhorroEntity.findById(idAhorro).orElse(null);
        if (existente == null) {
            throw new MetaAhorroNoExisteException(idAhorro);
        }

        crudMetaAhorroEntity.deleteById(idAhorro);
    }
}
