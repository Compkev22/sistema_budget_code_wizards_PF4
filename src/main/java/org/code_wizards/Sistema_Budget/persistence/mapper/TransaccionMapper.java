package org.code_wizards.Sistema_Budget.persistence.mapper;

import org.code_wizards.Sistema_Budget.dominio.dto.ModTransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDto;
import org.code_wizards.Sistema_Budget.persistence.entity.TransaccionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    @Mapping(source = "categoria.idCategoria", target = "idCategory")
    @Mapping(source = "descripcionTransaccion", target = "descriptionTransaction")
    @Mapping(source = "montoTransaccion", target = "transactionAmount")
    @Mapping(source = "fechaTransaccion", target = "transactionDate")
    @Mapping(source = "tipoTransaccion", target = "typeTransaction")
    TransaccionDto toDto(TransaccionEntity entity);

    List<TransaccionDto> toDto(Iterable<TransaccionEntity> entities);

    @InheritInverseConfiguration
    TransaccionEntity toEntity(TransaccionDto transaccionDto);

    @Mapping(source = "idCategory", target = "categoria.idCategoria")
    @Mapping(source = "descriptionTransaction", target = "descripcionTransaccion")
    @Mapping(source = "transactionAmount", target = "montoTransaccion")
    @Mapping(source = "transactionDate", target = "fechaTransaccion")
    void modificarEntityFromDto(ModTransaccionDto mod, @MappingTarget TransaccionEntity entity);
}
