package org.code_wizards.Sistema_Budget.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.ModUsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.dto.SolicitudDto;
import org.code_wizards.Sistema_Budget.dominio.dto.UsuarioDto;
import org.code_wizards.Sistema_Budget.dominio.service.AiServiceBudget;
import org.code_wizards.Sistema_Budget.dominio.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios/")
@Tag(name = "Usuarios", description = "Operaciones Crud para Peliculas dentro del Sistema_Budget")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AiServiceBudget aiServiceBudget;

    public UsuarioController(UsuarioService usuarioService, AiServiceBudget aiServiceBudget) {
        this.usuarioService = usuarioService;
        this.aiServiceBudget = aiServiceBudget;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios() {
        return ResponseEntity.ok(this.usuarioService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener un usuario por su identificador",
            description = "Retorna el usuario que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario no encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
            }
    )

    public ResponseEntity<UsuarioDto> buscarPorCodigo
            (@Parameter(description = "Identificador del usuario a recuperar", example = "3") @PathVariable Long codigo) {
        return ResponseEntity.ok(this.usuarioService.buscarPorCodigo(codigo));
    }

    //Sugerir por medio de AI
    @PostMapping("/sugerir")
    public ResponseEntity<String> generarSugerenciaUsuario(@RequestBody SolicitudDto solicitudDto) {
        return ResponseEntity.ok(this.aiServiceBudget.generarSugerenciaTipoGassto(solicitudDto.preferencias()));
    }

    //Agregar
    @PostMapping
    public ResponseEntity<UsuarioDto> guardarUsuario
    (@RequestBody @Valid UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.usuarioService.guardarUsuario(usuarioDto));
    }

    //Modificar
    @PutMapping("{codigo}")
    public ResponseEntity<UsuarioDto> modifcarUsuario
    (@PathVariable Long codigo, @RequestBody ModUsuarioDto modUsuario) {
        return ResponseEntity.ok(this.usuarioService.modificarUsuario(codigo, modUsuario));
    }

    //Eliminar
    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long codigo) {
        this.usuarioService.eliminarUsuario(codigo);
        return ResponseEntity.ok().build();
    }
}
