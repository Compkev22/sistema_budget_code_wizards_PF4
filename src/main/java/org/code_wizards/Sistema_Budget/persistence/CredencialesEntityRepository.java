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
    public CredencialesDto buscarPorCodigo(Long idCredencial) {
        return this.credencialesMapper.toDto(this.crudCredencialesEntity.findById(idCredencial).orElse(null));
    }

    @Override
    public CredencialesDto guardarCredenciales(CredencialesDto credencialesDto) {
        if (this.crudCredencialesEntity.findFirstByEmail(credencialesDto.correo()) != null) {
            throw new CredencialesYaExisteException(credencialesDto.correo());
        }

        CredencialesEntity credenciales = new CredencialesEntity();
        // usuario = this.usuarioMapper.toEntity(usuarioDto)
        this.crudCredencialesEntity.save(credenciales);
        return this.credencialesMapper.toDto(credenciales);
    }

    @Override
    public CredencialesDto modificarCredenciales(Long idCredencial, ModCredencialesDto modCredenciales) {
        CredencialesEntity credenciales = this.crudCredencialesEntity.findById(idCredencial).orElse(null);
        //Excepcion
        if (credenciales == null) {
            throw new CredencialesNoExisteException(idCredencial);
        }
        this.credencialesMapper.modificarEntityFromDto(modCredenciales, credenciales);
        return credencialesMapper.toDto(this.crudCredencialesEntity.save(credenciales));
    }

    @Override
    public void eliminarCredenciales(Long idCredencial) {
        CredencialesEntity credenciales = this.crudCredencialesEntity.findById(idCredencial).orElse(null);
        this.crudCredencialesEntity.delete(credenciales);
        //Excepcion
        if (credenciales == null) {
            throw new CredencialesNoExisteException(idCredencial);
        } else {
            this.crudCredencialesEntity.deleteById(idCredencial);
        }
    }
}
