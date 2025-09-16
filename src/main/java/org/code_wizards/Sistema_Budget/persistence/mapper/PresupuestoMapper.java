package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.persistence.entity.PresupuestoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StatusMapper.class})
public interface PresupuestoMapper {
    @Mapping(source = "idUsuario", target="idUser")
    @Mapping(source="nombrePresupuesto", target="budgetName")
    @Mapping(source="periodoPresupuesto", target="budgetPeriod")
    @Mapping(source="fechaInicio", target="startDate")
    @Mapping(source="fechaFin", target="endDate")
    @Mapping(source="estado", target="status", qualifiedByName = "generarStatus")
    @Mapping(source = "monto_totalPlanificado", target = "totalPlannedAmount")
    PresupuestoDto toDto(PresupuestoEntity entity);

    List<PresupuestoDto> toDto(List<PresupuestoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "status", target="estado", qualifiedByName = "generarEstado")
    PresupuestoEntity toEntity(PresupuestoDto presupuestoDto);

    @Mapping(source = "budgetName", target="nombrePresupuesto")
    @Mapping(source = "budgetPeriod", target="periodoPresupuesto")
    @Mapping(source = "startDate", target="fechaInicio")
    @Mapping(source = "endDate", target="fechaFin")
    void modificarEntityFromDto(PresupuestoDto mod, @MappingTarget PresupuestoEntity entity);

}
