package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.*;
import org.code_wizards.Sistema_Budget.dominio.exception.AuthException;
import org.code_wizards.Sistema_Budget.dominio.exception.CredencialesYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.UsuarioRepository;
import org.code_wizards.Sistema_Budget.dominio.repository.CredencialesRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudCredencialesEntity;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudUsuarioEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.CredencialesEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final CredencialesRepository credencialesRepository;
    private final CrudCredencialesEntity crudCredencialesEntity;
    private final CrudUsuarioEntity crudUsuarioEntity;

    public AuthService(UsuarioRepository usuarioRepository,
                       CredencialesRepository credencialesRepository,
                       CrudCredencialesEntity crudCredencialesEntity,
                       CrudUsuarioEntity crudUsuarioEntity) {
        this.usuarioRepository = usuarioRepository;
        this.credencialesRepository = credencialesRepository;
        this.crudCredencialesEntity = crudCredencialesEntity;
        this.crudUsuarioEntity = crudUsuarioEntity;
    }

    // Método para registrar usuario
    public AuthResponse register(RegisterRequest request) {
        try {
            CredencialesEntity credencialesExistentes = crudCredencialesEntity.findFirstByEmail(request.email());
            if (credencialesExistentes != null) {
                throw new CredencialesYaExisteException(credencialesExistentes.getUsuario().getId_Usuario().intValue(), request.email());
            }

            UsuarioDto nuevoUsuario = new UsuarioDto(
                    null, // el ID se genera automáticamente
                    request.name(),
                    request.lastnameUser(),
                    request.telephone(),
                    request.nitUser()
            );

            UsuarioDto usuarioGuardado = usuarioRepository.guardarUsuario(nuevoUsuario);

            UsuarioEntity usuarioEntity = crudUsuarioEntity.findById(usuarioGuardado.codigo()).orElse(null);
            if (usuarioEntity == null) {
                throw new AuthException("Error al recuperar usuario guardado");
            }

            CredencialesEntity nuevasCredenciales = new CredencialesEntity();
            nuevasCredenciales.setUsuario(usuarioEntity); // Relación con UsuarioEntity
            nuevasCredenciales.setEmail(request.email());
            nuevasCredenciales.setContrasena(request.password()); // Sin encriptar
            nuevasCredenciales.setDateRecord(LocalDateTime.now());

            crudCredencialesEntity.save(nuevasCredenciales);

            return AuthResponse.success(
                    "Usuario registrado exitosamente",
                    usuarioGuardado.codigo(),
                    usuarioGuardado.name()
            );

        } catch (CredencialesYaExisteException e) {
            throw e; //
        } catch (Exception e) {
            throw new AuthException("Error al registrar usuario: " + e.getMessage());
        }
    }

    public AuthResponse login(AuthRequest request) {
        try {
            CredencialesEntity credenciales = crudCredencialesEntity.findFirstByEmail(request.email());

            if (credenciales == null) {
                throw new AuthException("Email no encontrado");
            }

            if (!credenciales.getContrasena().equals(request.password())) {
                throw new AuthException("Contraseña incorrecta");
            }
            
            UsuarioEntity usuarioEntity = credenciales.getUsuario();

            if (usuarioEntity == null) {
                throw new AuthException("Usuario no encontrado");
            }

            return AuthResponse.success(
                    "Login exitoso",
                    usuarioEntity.getId_Usuario(),
                    usuarioEntity.getNombre()
            );

        } catch (Exception e) {
            throw new AuthException("Error en login: " + e.getMessage());
        }
    }
}