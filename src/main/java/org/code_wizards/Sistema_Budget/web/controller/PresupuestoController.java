package org.code_wizards.Sistema_Budget.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.ModPresupuestoDto;
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

    public PresupuestoController(PresupuestoService presupuestoService) {this.presupuestoService = presupuestoService;}

    @GetMapping
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

    // Agregar
    @PostMapping
    public ResponseEntity<PresupuestoDto> guardarPresupuesto(
            @RequestBody @Valid PresupuestoDto presupuestoDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.presupuestoService.guardarPresupuesto(presupuestoDto));
    }

    // Modificar
    @PutMapping("/{idPresupuesto}")
    public ResponseEntity<PresupuestoDto> modificarPresupuesto(
            @PathVariable Long idPresupuesto,
            @RequestBody @Valid PresupuestoDto modPresupuesto
    ) {
        return ResponseEntity.ok(this.presupuestoService.modificarPresupuesto(idPresupuesto, modPresupuesto));
    }

    // Eliminar
    @DeleteMapping("/{idPresupuesto}")
    public ResponseEntity<Void> eliminarPresupuesto(@PathVariable Long idPresupuesto) {
        this.presupuestoService.eliminarPresupuesto(idPresupuesto);
        return ResponseEntity.ok().build();
    }
}