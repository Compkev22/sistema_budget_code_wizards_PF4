package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.ModTransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDto;
import org.code_wizards.Sistema_Budget.persistence.entity.TransaccionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    @Mapping(source = "idCategoria", target = "idCategory")
    @Mapping(source = "descripcionTransaccion", target = "descriptionTransaction")
    @Mapping(source = "montoTransaccion", target = "transactionAmount")
    @Mapping(source = "fechaTransaccion", target = "transactionDate")
    @Mapping(source = "tipoTransaccion", target = "typeTransaction")
    TransaccionDto toDto(TransaccionEntity entity);

    @InheritInverseConfiguration
    TransaccionEntity toEntity(TransaccionDto transaccionDto);

    @Mapping(source = "idCategory", target = "idCategoria")
    @Mapping(source = "descriptionTransaction", target = "descripcionTransaccion")
    @Mapping(source = "transactionAmount", target = "montoTransaccion")
    void modificarEntityFromDto(ModTransaccionDto mod, @MappingTarget TransaccionEntity entity);
}
