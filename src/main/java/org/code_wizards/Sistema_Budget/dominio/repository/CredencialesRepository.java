package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCredencialesDto;

import java.util.List;

public interface CredencialesRepository {

    List<CredencialesDto> obtenerTodo();
    public CredencialesDto buscarPorCodigo(Long idCredencial);
    CredencialesDto guardarCredenciales(CredencialesDto usuarioDto);
    CredencialesDto modificarCredenciales(Long idCredencial, ModCredencialesDto modCredenciales);
    void eliminarCredenciales(Long idCredencial);
}
