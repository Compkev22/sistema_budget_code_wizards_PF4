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

    //Agregar
    @PostMapping
    public ResponseEntity<TransaccionDto> guardarTransaccion
    (@RequestBody @Valid TransaccionDto transaccionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.transaccionService.guardarTransaccion(transaccionDto));
    }

    //Modificar
    @PutMapping("/{idTransaccion}")
    public ResponseEntity<TransaccionDto> modificarTransaccion
    (@PathVariable Long idTransaccion, @RequestBody ModTransaccionDto modTransaccion) {
        return ResponseEntity.ok(this.transaccionService.modificarTransaccion(idTransaccion, modTransaccion));
    }

    //Eliminar
    @DeleteMapping("/{idTransaccion}")
    public ResponseEntity<Void> eliminarCredenciales(@PathVariable Long idTransaccion) {
        this.transaccionService.eliminarTransaccion(idTransaccion);
        return ResponseEntity.ok().build();
    }
}
