package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;
import org.code_wizards.Sistema_Budget.persistence.entity.UsuarioEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastnameUser")
    @Mapping(source = "telefono", target = "telephone")
    @Mapping(source = "nit", target = "nitUser")
    UsuarioDto toDto(UsuarioEntity entity);

    List<UsuarioDto> toDto(Iterable<UsuarioEntity> entities);

    @InheritInverseConfiguration
    UsuarioEntity toEntity(UsuarioDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastnameUser", target = "apellido")
    @Mapping(source = "telephone", target = "telefono")
    @Mapping(source = "nitUser", target = "nit")
    void modificarEntityFromDto(ModUsuarioDto mod, @MappingTarget UsuarioEntity entity);

}
