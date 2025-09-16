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
//import org.code_wizards.Sistema_Budget.dominio.service.AiServiceBudget;
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
  //  private final AiServiceBudget aiServiceBudget;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
       // this.aiServiceBudget = aiServiceBudget;
    }
    @GetMapping
    @Operation(
            summary = "Obtener todos los Usuarios existentes",
            description = "Retorna los usuarios que existan en la Base de Datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
                    @ApiResponse(responseCode = "404", description = "Usuarios no encontrados", content = @Content)
            }
    )

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

//    //Sugerir por medio de AI
//    @PostMapping("/sugerir")
//    public ResponseEntity<String> generarSugerenciaUsuario(@RequestBody SolicitudDto solicitudDto) {
//        return ResponseEntity.ok(this.aiServiceBudget.generarSugerenciaTipoGassto(solicitudDto.preferencias()));
//    }

    @PostMapping
    @Operation(
            summary = "Agregar Usuarios Nuevos",
            description = "Retorna y Envia los datos Nuevos a Agregar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios Agregados"),
                    @ApiResponse(responseCode = "404", description = "Usuarios no Agregados", content = @Content)
            }
    )
    //Agregar
    public ResponseEntity<UsuarioDto> guardarUsuario
    (@RequestBody @Valid UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.usuarioService.guardarUsuario(usuarioDto));
    }

    @PutMapping("/{codigo}")
    @Operation(
            summary = "Actualizar Usuarios existentes segun su Identificador ",
            description = "Retorna y envia los datos que seran sustituidos por los anteriores pertenecientes a ese Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario Actualizado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no Actualizado", content = @Content)
            }
    )

    //Modificar
    public ResponseEntity<UsuarioDto> modifcarUsuario
    (@PathVariable Long codigo, @RequestBody ModUsuarioDto modUsuario) {
        return ResponseEntity.ok(this.usuarioService.modificarUsuario(codigo, modUsuario));
    }

    @DeleteMapping("/{codigo}")
    @Operation(
            summary = "Eliminar Usuario existente segun su Identificador ",
            description = "No retorna ningun mensaje, pero ejecuta la accion de Eliminacion segun su Identifiacador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales Eliminadas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no Eliminadas", content = @Content)
            }
    )

    //Eliminar
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long codigo) {
        this.usuarioService.eliminarUsuario(codigo);
        return ResponseEntity.ok().build();
    }
}
