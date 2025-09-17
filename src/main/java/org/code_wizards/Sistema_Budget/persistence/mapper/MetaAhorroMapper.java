package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModMetaAhorroDto;
import org.code_wizards.Sistema_Budget.persistence.entity.MetaAhorroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetaAhorroMapper {

    @Mapping(source = "idSavingsGoal", target = "idAhorro")
    @Mapping(source = "idBudget", target = "idPresupuesto")
    @Mapping(source = "goalName", target = "nombreMeta")
    @Mapping(source = "targetAmount", target = "montoObjetivo")
    @Mapping(source = "currentAmount", target = "montoActual")
    @Mapping(source = "deadLine", target = "fechaLimite")
    @Mapping(source = "status", target = "estado")
    MetaAhorroEntity toEntity(MetaAhorroDto dto);

    @Mapping(source = "idAhorro", target = "idSavingsGoal")
    @Mapping(source = "idPresupuesto", target = "idBudget")
    @Mapping(source = "nombreMeta", target = "goalName")
    @Mapping(source = "montoObjetivo", target = "targetAmount")
    @Mapping(source = "montoActual", target = "currentAmount")
    @Mapping(source = "fechaLimite", target = "deadLine")
    @Mapping(source = "estado", target = "status")
    MetaAhorroDto toDto(MetaAhorroEntity entity);

    List<MetaAhorroDto> toDto(Iterable<MetaAhorroEntity> entities);

    @Mapping(source = "budgetId", target = "idPresupuesto")
    @Mapping(source = "goalName", target = "nombreMeta")
    @Mapping(source = "targetAmount", target = "montoObjetivo")
    @Mapping(source = "currentAmount", target = "montoActual")
    @Mapping(source = "deadLine", target = "fechaLimite")
    @Mapping(source = "status", target = "estado")
    void modificarEntityFromDtoAhorro(ModMetaAhorroDto dto, @MappingTarget MetaAhorroEntity entity);
}
