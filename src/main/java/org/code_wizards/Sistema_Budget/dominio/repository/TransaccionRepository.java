package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.ModTransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDto;

import java.util.List;

public interface TransaccionRepository {

    List<TransaccionDto> obtenerTodo();

    TransaccionDto buscarPorCodigo(Long codigo);

    TransaccionDto guardarTransaccion(TransaccionDto transaccionDto);

    TransaccionDto modificarTransaccion(Long codigo, ModTransaccionDto modTransaccion);

    void eliminarTransaccion(Long codigo);
}
