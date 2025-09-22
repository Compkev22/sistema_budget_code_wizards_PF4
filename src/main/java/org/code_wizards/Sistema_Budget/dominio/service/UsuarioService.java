package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioConCredencialesException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioConPresupuestosException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioNoEliminableException;
import org.code_wizards.Sistema_Budget.dominio.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            this.usuarioRepository.eliminarUsuario(codigo);
        } catch (DataIntegrityViolationException ex) {
            // Analizamos el mensaje de la excepción para ver la FK
            String causa = ex.getMostSpecificCause().getMessage().toLowerCase();

            if (causa.contains("credencial")) {
                throw new UsuarioConCredencialesException(codigo);
            } else if (causa.contains("presupuesto")) {
                throw new UsuarioConPresupuestosException(codigo);
            } else {
                throw new UsuarioNoEliminableException(
                        "Error de relación en la base de datos: no se puede eliminar este usuario porque tiene datos asociados.",
                        "usuario-no-eliminable"
                );
            }
        }
    }



}
