package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCredencialesDto;
import org.code_wizards.Sistema_Budget.persistence.entity.CredencialesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CredencialesMapper {

    @Mapping(source = "userID", target = "idUsuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "contrasena")  // clave para llenar la columna
    CredencialesEntity toEntity(CredencialesDto credencialesDto);

    @Mapping(source = "idUsuario", target = "userID")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "dateRecord", target = "dateRecord")
    CredencialesDto toDto(CredencialesEntity entity);

    List<CredencialesDto> toDto(Iterable<CredencialesEntity> entities);

    @Mapping(source = "userID", target = "idUsuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "contrasena")
    void modificarEntityFromDto(ModCredencialesDto mod, @MappingTarget CredencialesEntity entity);
}

