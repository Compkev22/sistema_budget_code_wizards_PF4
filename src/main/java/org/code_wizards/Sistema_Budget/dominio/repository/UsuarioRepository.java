package org.code_wizards.Sistema_Budget.dominio.repository;

import java.util.List;

public interface UsuarioRepository {

    List<UsuarioDto> obtenerTodo();
    public UsuarioDto buscarPorCodigo();
    UsuarioDto guardarUsuario(UsuarioDto usuarioDto);
    UsuarioDto modficarUsuario(Long codigo, ModUsuarioDto modUsuario);
    void eliminarUsuario(Long codigo);
}
