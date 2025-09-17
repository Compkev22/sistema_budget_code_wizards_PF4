package org.code_wizards.Sistema_Budget.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.ModTransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.dto.TransaccionDto;
import org.code_wizards.Sistema_Budget.dominio.service.TransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaccion")
@Tag(name = "Transaccion", description = "Operaciones Crud para Ingresos dentro del Sistema Budget")
public class TransaccionController {
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las Transacciones existentes",
            description = "Retorna las Transacciones que existan en la Base de Datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones encontradas"),
                    @ApiResponse(responseCode = "404", description = "Transaccionesn no encontrados", content = @Content)
            }
    )

    public ResponseEntity<List<TransaccionDto>> obtenerTransaccion() {
        return ResponseEntity.ok(this.transaccionService.obtenerTodo());
    }

    @GetMapping("/{idTransaccion}")
    @Operation(
            summary = "Obtener las Transacciones por su identificador",
            description = "Retorna las Transacciones que coincide con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones no encontrados"),
                    @ApiResponse(responseCode = "404", description = "Transacciones no encontraodos", content = @Content)
            }
    )

    public ResponseEntity<TransaccionDto> buscarPorCodigo
            (@Parameter(description = "Identificador de las Transacciones a recuperar", example = "3") @PathVariable Long idTransaccion) {
        return ResponseEntity.ok(this.transaccionService.buscarPorCodigo(idTransaccion));
    }

    @PostMapping
    @Operation(
            summary = "Agregar Transacciones Nuevas",
            description = "Retorna y Envia los datos Nuevos a Agregar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones Agregadas"),
                    @ApiResponse(responseCode = "404", description = "Transacciones no Agregadas", content = @Content)
            }
    )

    //Agregar
    public ResponseEntity<TransaccionDto> guardarTransaccion
    (@RequestBody @Valid TransaccionDto transaccionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.transaccionService.guardarTransaccion(transaccionDto));
    }

    @PutMapping("/{idTransaccion}")
    @Operation(
            summary = "Actualizar Transacciones existentes segun su Identificador ",
            description = "Retorna y envia los datos que seran sustituidos por los anteriores pertenecientes a ese Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones Actualizadas"),
                    @ApiResponse(responseCode = "404", description = "Transacciones no Actualizadas", content = @Content)
            }
    )

    //Modificar
    public ResponseEntity<TransaccionDto> modificarTransaccion
    (@PathVariable Long idTransaccion, @RequestBody ModTransaccionDto modTransaccion) {
        return ResponseEntity.ok(this.transaccionService.modificarTransaccion(idTransaccion, modTransaccion));
    }

    @DeleteMapping("/{idTransaccion}")
    @Operation(
            summary = "Eliminar Transacciones existente segun su Identificador ",
            description = "No retorna ningun mensaje, pero ejecuta la accion de Eliminacion segun su Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones Eliminadas"),
                    @ApiResponse(responseCode = "404", description = "Transacciones no Eliminadas", content = @Content)
            }
    )

    //Eliminar
    public ResponseEntity<Void> eliminarCredenciales(@PathVariable Long idTransaccion) {
        this.transaccionService.eliminarTransaccion(idTransaccion);
        return ResponseEntity.ok().build();
    }
}
