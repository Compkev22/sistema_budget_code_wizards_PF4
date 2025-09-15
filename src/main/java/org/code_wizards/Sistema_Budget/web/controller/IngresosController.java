package org.code_wizards.Sistema_Budget.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.IngresosDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModIngresosDto;
import org.code_wizards.Sistema_Budget.dominio.service.IngresosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingreso")
@Tag(name = "Ingreso", description = "Operaciones Crud para Ingresos dentro del Sistema_Budget")
public class IngresosController {
    private final IngresosService ingresosService;

    public IngresosController(IngresosService ingresosService) {
        this.ingresosService = ingresosService;
    }

    @GetMapping
    public ResponseEntity<List<IngresosDto>> obtenerIngresos() {
        return ResponseEntity.ok(this.ingresosService.obtenerTodo());
    }

    @GetMapping("/{idIngreso}")
    @Operation(
            summary = "Obtener los Ingresos por su identificador",
            description = "Retorna los Ingresos que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ingresos no encontrados"),
                    @ApiResponse(responseCode = "404", description = "Ingresos no encontrados", content = @Content)
            }
    )

    public ResponseEntity<IngresosDto> buscarPorCodigo
            (@Parameter(description = "Identificador de los Ingresos a recuperar", example = "3") @PathVariable Long idIngreso) {
        return ResponseEntity.ok(this.ingresosService.buscarPorCodigo(idIngreso));
    }

    //Agregar
    @PostMapping
    public ResponseEntity<IngresosDto> guardarIngresos
    (@RequestBody @Valid IngresosDto ingresosDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.ingresosService.guardarIngresos(ingresosDto));
    }

    //Modificar
    @PutMapping("/{idIngreso}")
    public ResponseEntity<IngresosDto> modificarIngresos
    (@PathVariable Long idIngreso, @RequestBody ModIngresosDto modIngresos) {
        return ResponseEntity.ok(this.ingresosService.modificarIngresos(idIngreso, modIngresos));
    }

    //Eliminar
    @DeleteMapping("/{idIngreso}")
    public ResponseEntity<Void> eliminarCredenciales(@PathVariable Long idIngreso) {
        this.ingresosService.eliminarIngresos(idIngreso);
        return ResponseEntity.ok().build();
    }
}
