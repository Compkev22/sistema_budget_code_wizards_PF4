package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.*;
import org.code_wizards.Sistema_Budget.dominio.exception.AuthException;
import org.code_wizards.Sistema_Budget.dominio.exception.CredencialesYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.repository.UsuarioRepository;
import org.code_wizards.Sistema_Budget.dominio.repository.CredencialesRepository;
import org.code_wizards.Sistema_Budget.persistence.crud.CrudCredencialesEntity;
import org.code_wizards.Sistema_Budget.persistence.entity.CredencialesEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final CredencialesRepository credencialesRepository;
    private final CrudCredencialesEntity crudCredencialesEntity;

    public AuthService(UsuarioRepository usuarioRepository,
                       CredencialesRepository credencialesRepository,
                       CrudCredencialesEntity crudCredencialesEntity) {
        this.usuarioRepository = usuarioRepository;
        this.credencialesRepository = credencialesRepository;
        this.crudCredencialesEntity = crudCredencialesEntity;
    }

    //Metodo para verficar
    public AuthResponse register(RegisterRequest request) {
        try {
            // Verificar si el email ya existe
            if (crudCredencialesEntity.findFirstByEmail(request.email()) != null) {
                throw new CredencialesYaExisteException(request.email());
            }

            // Crear nuevo usuario usando tu DTO existente
            UsuarioDto nuevoUsuario = new UsuarioDto(
                    null, // el ID se genera automáticamente
                    request.name(),
                    request.lastnameUser(),
                    request.telephone(),
                    request.nitUser()
            );

            // Guardar usuario usando tu servicio existente
            UsuarioDto usuarioGuardado = usuarioRepository.guardarUsuario(nuevoUsuario);

            // Crear credenciales usando tu DTO existente
            CredencialesDto nuevasCredenciales = new CredencialesDto(
                    null, // el ID se genera automáticamente
                    usuarioGuardado.codigo().intValue(), // convertir Long a Integer
                    request.email(),
                    request.password(), // Sin encriptar
                    LocalDateTime.now()
            );

            // Guardar credenciales usando tu servicio existente
            credencialesRepository.guardarCredenciales(nuevasCredenciales);

            return AuthResponse.success(
                    "Usuario registrado exitosamente",
                    usuarioGuardado.codigo(),
                    usuarioGuardado.name()
            );

        } catch (CredencialesYaExisteException e) {
            throw e; // Re-lanzar para que sea manejada por el handler existente
        } catch (Exception e) {
            throw new AuthException("Error al registrar usuario: " + e.getMessage());
        }
    }

    //Metodo para el login
    public AuthResponse login(AuthRequest request) {
        try {
            // Buscar credenciales por email usando tu CRUD existente
            CredencialesEntity credenciales = crudCredencialesEntity.findFirstByEmail(request.email());

            if (credenciales == null) {
                throw new AuthException("Email no encontrado");
            }

            // Verificar contraseña (sin encriptar)
            if (!credenciales.getContrasena().equals(request.password())) {
                throw new AuthException("Contraseña incorrecta");
            }

            // Buscar datos del usuario usando tu servicio existente
            UsuarioDto usuario = usuarioRepository.buscarPorCodigo(credenciales.getIdUsuario().longValue());

            if (usuario == null) {
                throw new AuthException("Usuario no encontrado");
            }

            return AuthResponse.success(
                    "Login exitoso",
                    usuario.codigo(),
                    usuario.name()
            );

        } catch (Exception e) {
            throw new AuthException("Error en login: " + e.getMessage());
        }
    }
}
