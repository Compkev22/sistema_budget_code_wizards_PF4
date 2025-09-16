package org.code_wizards.Sistema_Budget.dominio.repository;

public interface TransaccionRepository {

    List<TransaccionDto> obtenerTodo();

    TransaccionDto buscarPorCodigo(Long codigo);

    TransaccionDto guardarTransaccion(TransaccionDto transaccionDto);

    TransaccionDto modificarTransaccion(Long codigo, ModTransaccionDto modTransaccion);

    void eliminarTransaccion(Long codigo);
}
