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

    // Entity → DTO
    @Mapping(source = "presupuesto.idPresupuesto", target = "idBudget")
    @Mapping(source = "categoria.idCategoria", target = "idCategory")
    @Mapping(source = "descripcionIngreso", target = "descriptionEntry")
    @Mapping(source = "montoIngreso", target = "incomeAmount")
    @Mapping(source = "fechaIngreso", target = "entryDate")
    IngresosDto toDto(IngresosEntity entity);

    List<IngresosDto> toDto(Iterable<IngresosEntity> entities);

    // DTO → Entity
    @Mapping(source = "idBudget", target = "presupuesto.idPresupuesto")
    @Mapping(source = "idCategory", target = "categoria.idCategoria")
    @Mapping(source = "descriptionEntry", target = "descripcionIngreso")
    @Mapping(source = "incomeAmount", target = "montoIngreso")
    @Mapping(source = "entryDate", target = "fechaIngreso")
    IngresosEntity toEntity(IngresosDto ingresosDto);

    // DTO de modificación → Entity existente
    @Mapping(source = "idBudget", target = "presupuesto.idPresupuesto")
    @Mapping(source = "idCategory", target = "categoria.idCategoria")
    @Mapping(source = "descriptionEntry", target = "descripcionIngreso")
    @Mapping(source = "incomeAmount", target = "montoIngreso")
    void modificarEntityFromDto(ModIngresosDto mod, @MappingTarget IngresosEntity entity);
}
