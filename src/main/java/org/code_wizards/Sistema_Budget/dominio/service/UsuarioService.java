package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) { this.usuarioRepository = usuarioRepository;}


    public List<UsuarioDto> obtenerTodo() {
        return this.usuarioRepository.obtenerTodo();
    }

    public UsuarioDto buscarPorCodigo(Long codigo) {
        return this.usuarioRepository.buscarPorCodigo(codigo);
    }

    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        return this.usuarioRepository.guardarUsuario(usuarioDto);
    }

    public UsuarioDto modificarUsuario(Long codigo, ModUsuarioDto modUsuarioDto) {
        return this.usuarioRepository.modficarUsuario(codigo, modUsuarioDto);

    }

    public void eliminarUsuario(Long codigo) {
        this.usuarioRepository.eliminarUsuario(codigo);
    }


}
