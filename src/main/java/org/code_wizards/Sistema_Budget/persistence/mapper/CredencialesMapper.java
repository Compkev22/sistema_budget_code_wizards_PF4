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

    @Mapping(source = "idUsuario", target = "UserID")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "contraseña", target = "password")
    @Mapping(source = "fechaRegistro", target = "dateRecord")
    CredencialesDto toDto(CredencialesEntity entity);

    List<CredencialesDto> toDto(Iterable<CredencialesEntity> entities);

    @Mapping(source = "UserID", target = "idUsuario")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "password", target = "contraseña")
    void modificarEntityFromDto(ModCredencialesDto mod, @MappingTarget CredencialesEntity entity);

}
