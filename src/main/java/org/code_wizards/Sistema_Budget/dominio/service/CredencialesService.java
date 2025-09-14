package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.repository.CredencialesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredencialesService {
    private final CredencialesRepository credencialesRepository;

    public CredencialesService(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;}


    public List<CredencialesDto> obtenerTodo() {

        return this.credencialesRepository.obtenerTodo();
    }

    public CredencialesDto buscarPorCodigo(Long codigo) {

        return this.credencialesRepository.buscarPorCodigo(codigo);
    }

    public CredencialesDto guardarCredenciales(CredencialesDto credencialesDto) {
        return this.credencialesRepository.guardarCredenciales(credencialesDto);
    }

    public CredencialesDto modificarCredenciales(Long codigo, ModCredencialesDto modCredenciales) {
        return this.credencialesRepository.modificarCredenciales(codigo, modCredenciales);

    }

    public void eliminarCredenciales(Long codigo) {
        this.credencialesRepository.eliminarCredenciales(codigo);
    }


}
