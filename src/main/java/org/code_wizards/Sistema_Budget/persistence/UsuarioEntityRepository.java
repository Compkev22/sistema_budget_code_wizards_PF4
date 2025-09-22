package org.code_wizards.Sistema_Budget.persistence;


import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioNoEliminableException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.UsuarioRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudUsuarioEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.UsuarioEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.UsuarioMapper;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioEntityRepository implements UsuarioRepository {
    private final CrudUsuarioEntity crudUsuarioEntity;
    private final UsuarioMapper usuarioMapper;


    public UsuarioEntityRepository(CrudUsuarioEntity crudUsuarioEntity, UsuarioMapper usuarioMapper) {
        this.crudUsuarioEntity = crudUsuarioEntity;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDto> obtenerTodo() {

        return this.usuarioMapper.toDto(this.crudUsuarioEntity.findAll());
    }

    @Override
    public UsuarioDto buscarPorCodigo(Long codigo) {
        return this.usuarioMapper.toDto(this.crudUsuarioEntity.findById(codigo).orElse(null));
    }

    @Override
    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        UsuarioEntity existente = this.crudUsuarioEntity.findFirstByNombre(
                usuarioDto.name()
        );
        if (existente != null) {
            throw new UsuarioYaExisteException(usuarioDto.name());
        }

        UsuarioEntity entity = this.usuarioMapper.toEntity(usuarioDto);
        entity = this.crudUsuarioEntity.save(entity);

        return this.usuarioMapper.toDto(entity);
    }

    @Override
    public UsuarioDto modficarUsuario(Long codigo, ModUsuarioDto modUsuario) {
        UsuarioEntity usuario = this.crudUsuarioEntity.findById(codigo).orElse(null);
        //Excepcion
        if (usuario == null) {
            throw new UsuarioNoExisteException(codigo);
        }
        this.usuarioMapper.modificarEntityFromDto(modUsuario, usuario);
        return usuarioMapper.toDto(this.crudUsuarioEntity.save(usuario));
    }

    @Override
    public void eliminarUsuario(Long codigo) {
        UsuarioEntity usuario = this.crudUsuarioEntity.findById(codigo).orElse(null);
        this.crudUsuarioEntity.delete(usuario);
        //Excepcion
        if (usuario == null) {
            throw new UsuarioNoExisteException(codigo);
        } else {
            this.crudUsuarioEntity.deleteById(codigo);
        }
    }


}
