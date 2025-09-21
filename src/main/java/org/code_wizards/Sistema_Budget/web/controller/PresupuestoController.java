package org.code_wizards.Sistema_Budget.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.dominio.service.PresupuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presupuesto")
@Tag(name = "Presupuesto", description = "Operaciones CRUD para Presupuestos dentro del Sistema_Budget")
public class PresupuestoController {

    private final PresupuestoService presupuestoService;

    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los presupuestos",
            description = "Retorna una lista de todos los presupuestos existentes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de presupuestos obtenida exitosamente")
            }
    )
    public ResponseEntity<List<PresupuestoDto>> obtenerPresupuestos() {
        return ResponseEntity.ok(this.presupuestoService.listarPresupuestos());
    }

    @GetMapping("/{idPresupuesto}")
    @Operation(
            summary = "Obtener un Presupuesto por su identificador",
            description = "Retorna el Presupuesto que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Presupuesto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Presupuesto no encontrado", content = @Content)
            }
    )
    public ResponseEntity<PresupuestoDto> buscarPorCodigo(
            @Parameter(description = "Identificador del Presupuesto a recuperar", example = "3") @PathVariable Long idPresupuesto
    ) {
        return ResponseEntity.ok(this.presupuestoService.buscarPorCodigo(idPresupuesto));
    }

    @PostMapping
    @Operation(
            summary = "Guardar un nuevo presupuesto",
            description = "Crea y retorna un nuevo presupuesto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Presupuesto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de presupuesto inválidos", content = @Content)
            }
    )
    public ResponseEntity<PresupuestoDto> guardarPresupuesto(
            @RequestBody @Valid PresupuestoDto presupuestoDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.presupuestoService.guardarPresupuesto(presupuestoDto));
    }

    @PutMapping("/{idPresupuesto}")
    @Operation(
            summary = "Modificar un presupuesto existente",
            description = "Actualiza la información de un presupuesto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Presupuesto modificado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Presupuesto a modificar no encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos de presupuesto inválidos", content = @Content)
            }
    )
    public ResponseEntity<PresupuestoDto> modificarPresupuesto(
            @Parameter(description = "Identificador del presupuesto a modificar", example = "3") @PathVariable Long idPresupuesto,
            @RequestBody @Valid PresupuestoDto modPresupuesto
    ) {
        return ResponseEntity.ok(this.presupuestoService.modificarPresupuesto(idPresupuesto, modPresupuesto));
    }

    @DeleteMapping("/{idPresupuesto}")
    @Operation(
            summary = "Eliminar un presupuesto por su identificador",
            description = "Elimina un presupuesto de forma permanente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Presupuesto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Presupuesto a eliminar no encontrado", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarPresupuesto(@PathVariable Long idPresupuesto) {
        this.presupuestoService.eliminarPresupuesto(idPresupuesto);
        return ResponseEntity.ok().build();
    }
}