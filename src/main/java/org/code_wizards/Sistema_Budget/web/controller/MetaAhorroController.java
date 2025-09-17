package org.code_wizards.Sistema_Budget.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModMetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.service.CategoriaService;
import org.code_wizards.Sistema_Budget.dominio.service.MetaAhorroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MetaAhorro")
@Tag(name = "MetaAhorro", description = "Operaciones Crud para MetaAhorro dentro del Sistema_Budget")
public class MetaAhorroController {
    private final MetaAhorroService metaAhorroService;

    public MetaAhorroController(MetaAhorroService metaAhorroService) {
        this.metaAhorroService = metaAhorroService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las MetaAhorro existentes",
            description = "Retorna las MetaAhorro que existan en la Base de Datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "MetaAhorro encontradas"),
                    @ApiResponse(responseCode = "404", description = "MetaAhorro no encontradas", content = @Content)
            }
    )


    public ResponseEntity<List<MetaAhorroDto>> obtenerTodo() {
        return ResponseEntity.ok(this.metaAhorroService.obtenerTodos());
    }

    @GetMapping("/{idAhorro}")
    @Operation(
            summary = "Obtener Meta Ahorro por su identificador",
            description = "Retorna las Meta Ahorr que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta Ahorr encontrada"),
                    @ApiResponse(responseCode = "404", description = "Meta Ahorr no encontrada", content = @Content)
            }
    )
    public ResponseEntity<MetaAhorroDto> buscarPorCodigos(
            @Parameter(description = "Identificador de las Metas Ahorro a recuperar", example = "3")
            @PathVariable Long idAhorro) {
        return ResponseEntity.ok(this.metaAhorroService.buscarPorCodigos(idAhorro));
    }


    @PostMapping
    @Operation(
            summary = "Agregar Meta Ahorro Nuevas",
            description = "Retorna y Envia los datos Nuevos a Agregar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta Ahorro Agregadas"),
                    @ApiResponse(responseCode = "404", description = "Meta Ahorro no Agregadas", content = @Content)
            }
    )

    //Agregar
    public ResponseEntity<MetaAhorroDto> guardarAhorro
            (@RequestBody @Valid MetaAhorroDto metaAhorroDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.metaAhorroService.guardarAhorro(metaAhorroDto));
    }


    @PutMapping("/{idAhorro}")
    @Operation(
            summary = "Actualizar Meta Ahorro existentes segun su Identificador ",
            description = "Retorna y envia los datos que seran sustituidos por los anteriores pertenecientes a ese Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta Ahorro Actualizadas"),
                    @ApiResponse(responseCode = "404", description = "Meta Ahorro no Actualizadas", content = @Content)
            }
    )
    //Modificar

    public ResponseEntity<MetaAhorroDto> modificarAhorro
            (@PathVariable Long idAhorro, @RequestBody ModMetaAhorroDto modMetaAhorro) {
        return ResponseEntity.ok(this.metaAhorroService.modificarAhorro(idAhorro, modMetaAhorro));
    }


    @DeleteMapping("/{idAhorro}")
    @Operation(
            summary = "Eliminar Meta Ahorro existentes segun su Identificador ",
            description = "No retorna ningun mensaje, pero ejecuta la accion de Eliminacion segun su Identifiacador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta Ahorro Eliminadas"),
                    @ApiResponse(responseCode = "404", description = "Meta Ahorro no Eliminadas", content = @Content)
            }
    )

    //Eliminar
    public ResponseEntity<Void> eliminarAhorro(@PathVariable Long idAhorro) {
        this.metaAhorroService.eliminarAhorro(idAhorro);
        return ResponseEntity.ok().build();
    }
}
