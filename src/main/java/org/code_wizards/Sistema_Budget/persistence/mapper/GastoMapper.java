package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.GastoDto;
import org.code_wizards.Sistema_Budget.persistence.entity.GastoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GastoMapper {

    @Mapping(source = "idGasto", target = "idGasto")
    @Mapping(source = "idPresupuesto", target = "idPresupuesto")
    @Mapping(source = "idCategoria", target = "idCategoria")
    @Mapping(source = "descripcionGasto", target = "descripcionGasto")
    @Mapping(source = "categoriaGasto", target = "categoriaGasto")
    @Mapping(source = "montoGasto", target = "montoGasto")
    @Mapping(source = "fechaGasto", target = "fechaGasto")
    GastoDto toDto(GastoEntity entity);

    List<GastoDto> toDto(List<GastoEntity> entities);

    @InheritInverseConfiguration
    GastoEntity toEntity(GastoDto dto);

    @Mapping(target = "idGasto", ignore = true)
    @Mapping(target = "idPresupuesto", ignore = true)
    @Mapping(target = "idCategoria", ignore = true)
    void modificarEntityFromDto(GastoDto mod, @MappingTarget GastoEntity entity);
}