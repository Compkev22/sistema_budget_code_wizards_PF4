package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModIngresosDto;
import org.code_wizards.Sistema_Budget.dominio.repository.IngresosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresosService {

    private final IngresosRepository ingresosRepository;

    public IngresosService(IngresosRepository ingresosRepository) {
        this.ingresosRepository = ingresosRepository;
    }

    public List<IngresosDto> obtenerTodo() {
        return this.ingresosRepository.obtenerTodo();
    }

    public IngresosDto buscarPorCodigo(Long codigo) {
        return this.ingresosRepository.buscarPorCodigo(codigo);
    }

    public IngresosDto guardarIngresos(IngresosDto ingresosDto) {
        return this.ingresosRepository.guardarIngresos(ingresosDto);
    }

    public IngresosDto modificarIngresos(Long codigo, ModIngresosDto modIngresos) {
        return this.ingresosRepository.modificarIngresos(codigo, modIngresos);
    }

    public void eliminarIngresos(Long codigo) {
        this.ingresosRepository.eliminarIngresos(codigo);
    }
}
