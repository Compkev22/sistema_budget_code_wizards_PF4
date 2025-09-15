package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModIngresosDto;

import java.util.List;

public interface IngresosRepository {

    List<IngresosDto> obtenerTodo();

    IngresosDto buscarPorCodigo(Long codigo);

    IngresosDto guardarIngresos(IngresosDto ingresosDto);

    IngresosDto modificarIngresos(Long codigo, ModIngresosDto modIngresos);

    void eliminarIngresos(Long codigo);
}
