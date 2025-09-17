package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.GastoDto;

import java.util.List;

public interface GastoRepository {
    List<GastoDto> obtenerTodo();
    List<GastoDto> obtenerPorPresupuesto(Long idPresupuesto);
    GastoDto buscarPorCodigo(Long idGasto);
    GastoDto guardarGasto(GastoDto gastoDto);
    GastoDto modificarGasto(Long idGasto, GastoDto modGasto);
    void eliminarGasto(Long idGasto);
}