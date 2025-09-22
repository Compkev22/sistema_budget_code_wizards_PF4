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

    // DTO → Entity
    @Mapping(source = "userID", target = "usuario.id_Usuario")  // 👈 ahora apunta al objeto usuario
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "contrasena")
    CredencialesEntity toEntity(CredencialesDto credencialesDto);

    // Entity → DTO
    @Mapping(source = "usuario.id_Usuario", target = "userID")  // 👈 saca el id desde la entidad Usuario
    @Mapping(source = "email", target = "email")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "dateRecord", target = "dateRecord")
    CredencialesDto toDto(CredencialesEntity entity);

    List<CredencialesDto> toDto(Iterable<CredencialesEntity> entities);

    // Para modificar
    @Mapping(source = "userID", target = "usuario.id_Usuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "contrasena")
    void modificarEntityFromDto(ModCredencialesDto mod, @MappingTarget CredencialesEntity entity);
}


