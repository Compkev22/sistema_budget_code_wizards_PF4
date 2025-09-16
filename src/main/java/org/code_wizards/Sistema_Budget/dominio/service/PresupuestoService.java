package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.dominio.repository.PresupuestoRepository;

import java.util.List;

public class PresupuestoService {
    private final PresupuestoRepository presupuestoRepository;

    public PresupuestoService(PresupuestoRepository presupuestoRepository) {this.presupuestoRepository = presupuestoRepository;}

    public List<PresupuestoDto> listarPresupuestos(){return this.presupuestoRepository.obtenerTodo();}

    public PresupuestoDto buscarPorCodigo(Long codigo) { return this.presupuestoRepository.buscarPorCodigo(codigo); }

    public PresupuestoDto guardarPresupuesto(PresupuestoDto presupuestoDto) { return this.presupuestoRepository.guardarPresupuesto(presupuestoDto); }

    public PresupuestoDto modificarPresupuesto(Long codigo, PresupuestoDto modPresupuesto) { return this.presupuestoRepository.modificarPresupuesto(codigo, modPresupuesto); }

    public void eliminarPresupuesto(Long codigo) { this.presupuestoRepository.eliminarPresupuesto(codigo); }
}
