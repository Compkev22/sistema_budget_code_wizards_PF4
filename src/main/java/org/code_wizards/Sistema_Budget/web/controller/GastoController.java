package org.code_wizards.Sistema_Budget.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.GastoDto;
import org.code_wizards.Sistema_Budget.dominio.service.GastoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
@Tag(name = "Gasto", description = "Operaciones CRUD para Gastos dentro del Sistema_Budget")
public class GastoController {

    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los gastos",
            description = "Retorna una lista de todos los gastos existentes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de gastos obtenida exitosamente")
            }
    )
    public ResponseEntity<List<GastoDto>> obtenerGastos() {
        return ResponseEntity.ok(this.gastoService.listarGastos());
    }

    @GetMapping("/presupuesto/{idPresupuesto}")
    @Operation(
            summary = "Obtener gastos por ID de presupuesto",
            description = "Retorna una lista de gastos asociados a un ID de presupuesto específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de gastos por presupuesto obtenida exitosamente")
            }
    )
    public ResponseEntity<List<GastoDto>> obtenerGastosPorPresupuesto(
            @Parameter(description = "ID del presupuesto para filtrar los gastos", example = "1") @PathVariable Long idPresupuesto
    ) {
        return ResponseEntity.ok(this.gastoService.listarGastosPorPresupuesto(idPresupuesto));
    }


    @GetMapping("/{idGasto}")
    @Operation(
            summary = "Obtener un Gasto por su identificador",
            description = "Retorna el Gasto que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gasto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Gasto no encontrado", content = @Content)
            }
    )
    public ResponseEntity<GastoDto> buscarPorCodigo(
            @Parameter(description = "Identificador del Gasto a recuperar", example = "5") @PathVariable Long idGasto
    ) {
        return ResponseEntity.ok(this.gastoService.buscarPorCodigo(idGasto));
    }

    @PostMapping
    @Operation(
            summary = "Guardar un nuevo gasto",
            description = "Crea y retorna un nuevo gasto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Gasto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de gasto inválidos", content = @Content)
            }
    )
    public ResponseEntity<GastoDto> guardarGasto(
            @RequestBody @Valid GastoDto gastoDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.gastoService.guardarGasto(gastoDto));
    }

    @PutMapping("/{idGasto}")
    @Operation(
            summary = "Modificar un gasto existente",
            description = "Actualiza la información de un gasto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gasto modificado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Gasto a modificar no encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos de gasto inválidos", content = @Content)
            }
    )
    public ResponseEntity<GastoDto> modificarGasto(
            @Parameter(description = "Identificador del gasto a modificar", example = "5") @PathVariable Long idGasto,
            @RequestBody @Valid GastoDto modGasto
    ) {
        return ResponseEntity.ok(this.gastoService.modificarGasto(idGasto, modGasto));
    }

    @DeleteMapping("/{idGasto}")
    @Operation(
            summary = "Eliminar un gasto por su identificador",
            description = "Elimina un gasto de forma permanente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Gasto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Gasto a eliminar no encontrado", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarGasto(@PathVariable Long idGasto) {
        this.gastoService.eliminarGasto(idGasto);
        return ResponseEntity.ok().build();
    }
}