package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.exception.CredencialesNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.CredencialesYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.CredencialesRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudCredencialesEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.CredencialesEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.CredencialesMapper;
import org.hibernate.mapping.UnionSubclass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CredencialesEntityRepository implements CredencialesRepository {
    private final CrudCredencialesEntity crudCredencialesEntity;
    private final CredencialesMapper credencialesMapper;


    public CredencialesEntityRepository(CrudCredencialesEntity crudCredencialesEntity, CredencialesMapper credencialesMapper) {
        this.crudCredencialesEntity = crudCredencialesEntity;
        this.credencialesMapper = credencialesMapper;
    }

    @Override
    public List<CredencialesDto> obtenerTodo() {

        return this.credencialesMapper.toDto(this.crudCredencialesEntity.findAll());
    }

    @Override
    public CredencialesDto buscarPorCodigo(Long codigo) {
        return this.credencialesMapper.toDto(this.crudCredencialesEntity.findById(codigo).orElse(null));
    }

    @Override
    public CredencialesDto guardarCredenciales(CredencialesDto credencialesDto) {
        // Validar si ya existe
        CredencialesEntity existente = this.crudCredencialesEntity.findFirstByEmail(credencialesDto.email());
        if (existente != null) {
            throw new CredencialesYaExisteException(Math.toIntExact(credencialesDto.userID()), credencialesDto.email());
        }

        // Mapear y guardar en base de datos
        CredencialesEntity credenciales = this.credencialesMapper.toEntity(credencialesDto);
        this.crudCredencialesEntity.save(credenciales);

        // Retornar DTO
        return this.credencialesMapper.toDto(credenciales);
    }


    @Override
    public CredencialesDto modificarCredenciales(Long codigo, ModCredencialesDto modCredenciales) {
        CredencialesEntity credenciales = this.crudCredencialesEntity.findById(codigo).orElse(null);
        //Excepcion
        if (credenciales == null) {
            throw new CredencialesNoExisteException(codigo);
        }
        this.credencialesMapper.modificarEntityFromDto(modCredenciales, credenciales);
        return credencialesMapper.toDto(this.crudCredencialesEntity.save(credenciales));
    }

    @Override
    public void eliminarCredenciales(Long codigo) {
        CredencialesEntity credenciales = this.crudCredencialesEntity.findById(codigo).orElse(null);
        this.crudCredencialesEntity.delete(credenciales);
        //Excepcion
        if (credenciales == null) {
            throw new CredencialesNoExisteException(codigo);
        } else {
            this.crudCredencialesEntity.deleteById(codigo);
        }
    }
}
