package org.code_wizards.Sistema_Budget.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDtoWeb;
import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.service.UsuarioService;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioYaExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioNoExisteException;
import org.code_wizards.Sistema_Budget.dominio.exception.UsuarioNoEliminableException;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
@Data
@Named("usuarioControllerWeb")
public class UsuarioControllerWeb implements Serializable {

    @Autowired
    private UsuarioService usuarioService;

    private List<UsuarioDtoWeb> usuarios;
    private UsuarioDtoWeb usuarioSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioControllerWeb.class);

    @PostConstruct
    public void init() {
        cargarUsuarios();
    }

    public void cargarUsuarios() {
        try {
            List<UsuarioDto> usuariosDto = this.usuarioService.obtenerTodo();
            this.usuarios = usuariosDto.stream()
                    .map(this::convertirAWeb)
                    .collect(Collectors.toList());
            this.usuarios.forEach(usuario -> logger.info("Usuario cargado: " + usuario.toString()));
        } catch (Exception e) {
            logger.error("Error al cargar usuarios: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar usuarios");
        }
    }

    public void agregarUsuario() {
        this.usuarioSeleccionado = new UsuarioDtoWeb();
        logger.info("Preparando nuevo usuario");
    }

    public void guardarUsuario() {
        try {
            logger.info("Usuario a guardar: " + this.usuarioSeleccionado);

            if (this.usuarioSeleccionado.getCodigo() == null) {
                // Crear nuevo usuario
                UsuarioDto nuevoUsuario = new UsuarioDto(
                        null,
                        this.usuarioSeleccionado.getName(),
                        this.usuarioSeleccionado.getLastnameUser(),
                        this.usuarioSeleccionado.getTelephone(),
                        this.usuarioSeleccionado.getNitUser()
                );

                this.usuarioService.guardarUsuario(nuevoUsuario);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario agregado exitosamente");

            } else {
                // Modificar usuario existente
                ModUsuarioDto modUsuario = new ModUsuarioDto(
                        this.usuarioSeleccionado.getName(),
                        this.usuarioSeleccionado.getLastnameUser(),
                        this.usuarioSeleccionado.getTelephone(),
                        this.usuarioSeleccionado.getNitUser()
                );

                this.usuarioService.modificarUsuario(this.usuarioSeleccionado.getCodigo(), modUsuario);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado exitosamente");
            }

            cargarUsuarios();
            this.usuarioSeleccionado = null;

        } catch (UsuarioYaExisteException e) {
            mostrarMensaje(FacesMessage.SEVERITY_WARN, "Advertencia", e.getMessage());
        } catch (UsuarioNoExisteException e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        } catch (Exception e) {
            logger.error("Error al guardar usuario: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar usuario");
        }
    }

    public void eliminarUsuario() {
        try {
            logger.info("Usuario a eliminar: " + this.usuarioSeleccionado);
            this.usuarioService.eliminarUsuario(this.usuarioSeleccionado.getCodigo());
            this.usuarioSeleccionado = null;

            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario eliminado exitosamente");
            cargarUsuarios();

        } catch (UsuarioNoExisteException e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        } catch (UsuarioNoEliminableException e) {
            mostrarMensaje(FacesMessage.SEVERITY_WARN, "Advertencia", e.getMessage());
        } catch (Exception e) {
            logger.error("Error al eliminar usuario: " + e.getMessage());
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "No se puede eliminar el usuario porque tiene datos asociados");
        }
    }

    private UsuarioDtoWeb convertirAWeb(UsuarioDto dto) {
        return new UsuarioDtoWeb(
                dto.codigo(),
                dto.name(),
                dto.lastnameUser(),
                dto.telephone(),
                dto.nitUser()
        );
    }

    private void mostrarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
        PrimeFaces.current().ajax().update("formulario-usuarios:mensaje-emergente");
    }

    private void cerrarModal() {
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
        PrimeFaces.current().ajax().update("formulario-usuarios:tabla-usuarios");
    }
}
