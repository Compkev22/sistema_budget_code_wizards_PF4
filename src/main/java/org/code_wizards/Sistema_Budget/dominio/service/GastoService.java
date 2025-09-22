package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.GastoDto;
import org.code_wizards.Sistema_Budget.dominio.repository.GastoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastoService {
    private final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    public List<GastoDto> listarGastos(){
        return this.gastoRepository.obtenerTodo();
    }

    public List<GastoDto> listarGastosPorPresupuesto(Long idPresupuesto) {
        return this.gastoRepository.obtenerPorPresupuesto(idPresupuesto);
    }

    public GastoDto buscarPorCodigo(Long codigo) {
        return this.gastoRepository.buscarPorCodigo(codigo);
    }

    public GastoDto guardarGasto(GastoDto gastoDto) {
        return this.gastoRepository.guardarGasto(gastoDto);
    }

    public GastoDto modificarGasto(Long codigo, GastoDto modGasto) {
        return this.gastoRepository.modificarGasto(codigo, modGasto);
    }

    public void eliminarGasto(Long codigo) {
        this.gastoRepository.eliminarGasto(codigo);
    }
}