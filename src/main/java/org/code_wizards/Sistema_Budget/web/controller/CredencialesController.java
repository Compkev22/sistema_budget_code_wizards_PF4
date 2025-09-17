package org.code_wizards.Sistema_Budget.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.CredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCredencialesDto;
import org.code_wizards.Sistema_Budget.dominio.service.CredencialesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credenciales")
@Tag(name = "Credenciales", description = "Operaciones Crud para Credenciales dentro del Sistema_Budget")
public class CredencialesController {
    private final CredencialesService credencialesService;

    public CredencialesController(CredencialesService credencialesService) {
        this.credencialesService = credencialesService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las Credenciales existentes",
            description = "Retorna las credenciales que existan en la Base de Datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales encontradas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no encontradas", content = @Content)
            }
    )


    public ResponseEntity<List<CredencialesDto>> obtenerCredenciales() {
        return ResponseEntity.ok(this.credencialesService.obtenerTodo());
    }

    @GetMapping("/{idCredencial}")
    @Operation(
            summary = "Obtener Credenciales por su identificador",
            description = "Retorna las credenciales que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales encontradas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no encontradas", content = @Content)
            }
    )

    public ResponseEntity<CredencialesDto> buscarPorCodigo
            (@Parameter(description = "Identificador de las Credenciales a recuperar", example = "3") @PathVariable Long idCredencial) {
        return ResponseEntity.ok(this.credencialesService.buscarPorCodigo(idCredencial));
    }


    @PostMapping
    @Operation(
            summary = "Agregar Credenciales Nuevas",
            description = "Retorna y Envia los datos Nuevos a Agregar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales Agregadas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no Agregadas", content = @Content)
            }
    )

    //Agregar
    public ResponseEntity<CredencialesDto> guardarCredenciales
            (@RequestBody @Valid CredencialesDto credencialesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.credencialesService.guardarCredenciales(credencialesDto));
    }


    @PutMapping("/{idCredencial}")
    @Operation(
            summary = "Actualizar Credenciales existentes segun su Identificador ",
            description = "Retorna y envia los datos que seran sustituidos por los anteriores pertenecientes a ese Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales Actualizadas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no Actualizadas", content = @Content)
            }
    )
    //Modificar

    public ResponseEntity<CredencialesDto> modifcarCredenciales
            (@PathVariable Long idCredencial, @RequestBody ModCredencialesDto modCredenciales) {
        return ResponseEntity.ok(this.credencialesService.modificarCredenciales(idCredencial, modCredenciales));
    }


    @DeleteMapping("/{idCredencial}")
    @Operation(
            summary = "Eliminar Credenciales existentes segun su Identificador ",
            description = "No retorna ningun mensaje, pero ejecuta la accion de Eliminacion segun su Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales Eliminadas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no Eliminadas", content = @Content)
            }
    )

    //Eliminar

    public ResponseEntity<Void> eliminarCredenciales(@PathVariable Long idCredencial) {
        this.credencialesService.eliminarCredenciales(idCredencial);
        return ResponseEntity.ok().build();
    }
}
