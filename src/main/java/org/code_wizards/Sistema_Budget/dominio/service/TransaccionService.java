package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;


    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<TransaccionDto> obtenerTodo() {
        return this.transaccionRepository.obtenerTodo();
    }

    public TransaccionDto buscarPorCodigo(Long codigo) {
        return this.transaccionRepository.buscarPorCodigo(codigo);
    }

    public TransaccionDto guardarTransaccion(TransaccionDto transaccionDto) {
        return this.transaccionRepository.guardarTransaccion(transaccionDto);
    }

    public TransaccionDto modificarTransaccion(Long codigo, ModTransaccionDto modTransaccion) {
        return this.transaccionRepository.modificarTransaccion(codigo, modTransaccion);
    }

    public void eliminarTransaccion(Long codigo) {
        this.transaccionRepository.eliminarTransaccion(codigo);
    }


}
