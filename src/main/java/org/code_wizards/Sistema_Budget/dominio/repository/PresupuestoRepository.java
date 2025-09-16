package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;

import java.util.List;

public interface PresupuestoRepository {
    List<PresupuestoDto> obtenerTodo();
    PresupuestoDto buscarPorCodigo(Long idPresupuesto);
    PresupuestoDto guardarPresupuesto(PresupuestoDto presupuestoDto);
    PresupuestoDto modificarPresupuesto(Long idPresupuesto, PresupuestoDto modPresupuesto );
    void eliminarPresupuesto(Long idPresupuesto);
}
