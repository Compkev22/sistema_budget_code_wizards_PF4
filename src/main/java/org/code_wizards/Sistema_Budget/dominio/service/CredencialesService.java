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

    public CredencialesDto guardarCredenciales(CredencialesDto usuarioDto) {
        return this.credencialesRepository.guardarCredenciales(usuarioDto);
    }

    public CredencialesDto modificarCredenciales(Long idCredencial, ModCredencialesDto modCredenciales) {
        return this.credencialesRepository.modificarCredenciales(idCredencial, modCredenciales);

    }

    public void eliminarCredenciales(Long idCredencial) {
        this.credencialesRepository.eliminarCredenciales(idCredencial);
    }


}
