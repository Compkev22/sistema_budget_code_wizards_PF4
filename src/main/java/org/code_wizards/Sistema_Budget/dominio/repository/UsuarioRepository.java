package org.code_wizards.Sistema_Budget.dominio.repository;

import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;

import java.util.List;

public interface UsuarioRepository {

    List<UsuarioDto> obtenerTodo();
    public UsuarioDto buscarPorCodigo(Long codigo);
    UsuarioDto guardarUsuario(UsuarioDto usuarioDto);
    UsuarioDto modficarUsuario(Long codigo, ModUsuarioDto modUsuario);
    void eliminarUsuario(Long codigo);
}
