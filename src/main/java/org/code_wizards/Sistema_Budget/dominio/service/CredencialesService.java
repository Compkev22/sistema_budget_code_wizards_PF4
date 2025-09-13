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

    public CredencialesDto guardarUsuario(CredencialesDto usuarioDto) {
        return this.credencialesRepository.guardarCredenciales(usuarioDto);
    }

    public CredencialesDto modificarUsuario(Long codigo, ModCredencialesDto modCredenciales) {
        return this.credencialesRepository.modficarCredenciales(codigo, modCredenciales);

    }

    public void eliminarUsuario(Long codigo) {
        this.credencialesRepository.eliminarCredenciales(codigo);
    }


}
