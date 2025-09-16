package org.code_wizards.Sistema_Budget.persistence;

import org.code_wizards.Sistema_Budget.dominio.dto.ModTransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.repository.TransaccionRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudTransaccionEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.TransaccionEntity;
import org.code_wizards.Sistema_Budget.persistence.mapper.TransaccionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransaccionEntityRepository implements TransaccionRepository {

    private final CrudTransaccionEntity crudTransaccionEntity;
    private final TransaccionMapper transaccionMapper;

    public TransaccionEntityRepository(CrudTransaccionEntity crudTransaccionEntity, TransaccionMapper transaccionMapper) {
        this.crudTransaccionEntity = crudTransaccionEntity;
        this.transaccionMapper = transaccionMapper;
    }


    @Override
    public List<TransaccionDto> obtenerTodo() {
        return this.transaccionMapper.toDto(this.crudTransaccionEntity.findAll());
    }

    @Override
    public TransaccionDto buscarPorCodigo(Long codigo) {
        TransaccionEntity entity = this.crudTransaccionEntity.findById(codigo)
                .orElseThrow(() -> new TransaccionNoExisteException(codigo));
        return this.transaccionMapper.toDto(entity);
    }

    @Override
    public TransaccionDto guardarTransaccion(TransaccionDto transaccionDto) {
        TransaccionEntity existente = this.crudTransaccionEntity.findFirstByIdTransaccionAndDescripcionTransaccion(
                TransaccionDto.idCategory(),
                TransaccionDto.descriptionTransaction()
        );
        if (existente != null) {
            throw new TransaccionYaExisteException(transaccionDto.idCategory(), transaccionDto.descriptionTransaction());
        }

        TransaccionEntity entity = this.transaccionMapper.toEntity(transaccionDto);
        entity = this.crudTransaccionEntity.save(entity);

        return this.transaccionMapper.toDto(entity)
    }

    @Override
    public TransaccionDto modificarTransaccion(Long codigo, ModTransaccionDto modTransaccion) {
        TransaccionEntity entity = this.crudTransaccionEntity.findById(codigo)
                .orElseThrow(() -> new TransaccionNoExisteException(codigo));
        this.transaccionMapper.modificarEntityFromDto(modTransaccion, entity);
        entity = this.crudTransaccionEntity.save(entity);

        return this.transaccionMapper.toDto(entity)
    }

    @Override
    public void eliminarTransaccion(Long codigo) {
        TransaccionEntity entity = this.crudTransaccionEntity.findById(codigo)
                .orElseThrow(() -> new TransaccionNoExisteException(codigo));
        this.crudTransaccionEntity.delete(entity);

    }
}
