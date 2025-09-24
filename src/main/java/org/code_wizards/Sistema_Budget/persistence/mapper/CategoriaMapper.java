package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.persistence.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(source = "categoryId", target = "idCategoria")
    @Mapping(source = "budgetId", target = "presupuesto.idPresupuesto")
    @Mapping(source = "categoryName", target = "nombreCategoria")
    @Mapping(source = "categoryType", target = "tipoCategoria")
    @Mapping(source = "identificationColor", target = "colorIdentificacion")
    @Mapping(source = "plannedAmount", target = "montoPlanificado")
    @Mapping(source = "currentAmount", target = "montoActual")
    CategoriaEntity toEntity(CategoriaDto dto);

    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "presupuesto.idPresupuesto", target = "budgetId")
    @Mapping(source = "nombreCategoria", target = "categoryName")
    @Mapping(source = "tipoCategoria", target = "categoryType")
    @Mapping(source = "colorIdentificacion", target = "identificationColor")
    @Mapping(source = "montoPlanificado", target = "plannedAmount")
    @Mapping(source = "montoActual", target = "currentAmount")
    CategoriaDto toDto(CategoriaEntity entity);

    List<CategoriaDto> toDto(Iterable<CategoriaEntity> entities);

    @Mapping(source = "budgetId", target = "presupuesto.idPresupuesto")
    @Mapping(source = "categoryName", target = "nombreCategoria")
    @Mapping(source = "categoryType", target = "tipoCategoria")
    @Mapping(source = "identificationColor", target = "colorIdentificacion")
    @Mapping(source = "plannedAmount", target = "montoPlanificado")
    @Mapping(source = "currentAmount", target = "montoActual")
    void modificarEntityFromDto(ModCategoriaDto mod, @MappingTarget CategoriaEntity entity);
}
