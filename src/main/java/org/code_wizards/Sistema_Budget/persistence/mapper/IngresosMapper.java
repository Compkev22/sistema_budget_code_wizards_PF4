package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModIngresosDto;
import org.code_wizards.Sistema_Budget.persistence.entity.IngresosEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngresosMapper {

    @Mapping(source = "idPresupuesto", target = "idBudget")
    @Mapping(source = "idCategoria", target = "idCategory")
    @Mapping(source = "descripcionIngreso", target = "descriptionEntry")
    @Mapping(source = "montoIngreso", target = "incomeAmount")
    @Mapping(source = "fechaIngreso", target = "entryDate")
    IngresosDto toDto(IngresosEntity entity);

    List<IngresosDto> toDto(Iterable<IngresosEntity> entities);

    @InheritInverseConfiguration
    IngresosEntity toEntity(IngresosDto ingresosDto);

    @Mapping(source = "idBudget", target = "idPresupuesto")
    @Mapping(source = "idCategory", target = "idCategoria")
    @Mapping(source = "descriptionEntry", target = "descripcionIngreso")
    @Mapping(source = "incomeAmount", target = "montoIngreso")
    void modificarEntityFromDto(ModIngresosDto mod, @MappingTarget IngresosEntity entity);
}