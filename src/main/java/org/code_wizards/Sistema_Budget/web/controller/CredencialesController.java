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
    public ResponseEntity<List<CredencialesDto>> obtenerCredenciales() {
        return ResponseEntity.ok(this.credencialesService.obtenerTodo());
    }

    @GetMapping("/{idCredencial}")
    @Operation(
            summary = "Obtener las Credenciales por su identificador",
            description = "Retorna las credenciales que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Credenciales no encontradas"),
                    @ApiResponse(responseCode = "404", description = "Credenciales no encontradas", content = @Content)
            }
    )

    public ResponseEntity<CredencialesDto> buscarPorCodigo
            (@Parameter(description = "Identificador de las Credenciales a recuperar", example = "3") @PathVariable Long idCredencial) {
        return ResponseEntity.ok(this.credencialesService.buscarPorCodigo(idCredencial));
    }

    //Agregar
    @PostMapping
    public ResponseEntity<CredencialesDto> guardarCredenciales
    (@RequestBody @Valid CredencialesDto credencialesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.credencialesService.guardarCredenciales(credencialesDto));
    }

    //Modificar
    @PutMapping("/{idCredencial}")
    public ResponseEntity<CredencialesDto> modifcarCredenciales
    (@PathVariable Long idCredencial, @RequestBody ModCredencialesDto modCredenciales) {
        return ResponseEntity.ok(this.credencialesService.modificarCredenciales(idCredencial, modCredenciales));
    }

    //Eliminar
    @DeleteMapping("/{idCredencial}")
    public ResponseEntity<Void> eliminarCredenciales(@PathVariable Long idCredencial) {
        this.credencialesService.eliminarCredenciales(idCredencial);
        return ResponseEntity.ok().build();
    }
}
