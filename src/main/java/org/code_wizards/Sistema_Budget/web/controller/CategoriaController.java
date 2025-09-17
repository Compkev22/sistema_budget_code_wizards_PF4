package org.code_wizards.Sistema_Budget.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Categoria")
@Tag(name = "Categoria", description = "Operaciones Crud para Categoria dentro del Sistema_Budget")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las Categoria existentes",
            description = "Retorna las categoria que existan en la Base de Datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria encontradas"),
                    @ApiResponse(responseCode = "404", description = "Categoria no encontradas", content = @Content)
            }
    )


    public ResponseEntity<List<CategoriaDto>> obtenerTodo() {
        return ResponseEntity.ok(this.categoriaService.obtenerTodo());
    }

    @GetMapping("/{idCategoria}")
    @Operation(
            summary = "Obtener Categoria por su identificador",
            description = "Retorna las Categoria que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
                    @ApiResponse(responseCode = "404", description = "Categoria no encontrada", content = @Content)
            }
    )
    public ResponseEntity<CategoriaDto> buscarPorCodigo(
            @Parameter(description = "Identificador de las Categorías a recuperar", example = "3")
            @PathVariable Long idCategoria) {  // Cambié idCredencial por idCategoria
        return ResponseEntity.ok(this.categoriaService.buscarPorCodigo(idCategoria));
    }


    @PostMapping
    @Operation(
            summary = "Agregar Categoria Nuevas",
            description = "Retorna y Envia los datos Nuevos a Agregar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categorias Agregadas"),
                    @ApiResponse(responseCode = "404", description = "Categorias no Agregadas", content = @Content)
            }
    )

    //Agregar
    public ResponseEntity<CategoriaDto> guardarCategoria
            (@RequestBody @Valid CategoriaDto categoriaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.categoriaService.guardarCategoria(categoriaDto));
    }


    @PutMapping("/{idCategoria}")
    @Operation(
            summary = "Actualizar Categoria existentes segun su Identificador ",
            description = "Retorna y envia los datos que seran sustituidos por los anteriores pertenecientes a ese Identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria Actualizadas"),
                    @ApiResponse(responseCode = "404", description = "Categoria no Actualizadas", content = @Content)
            }
    )
    //Modificar

    public ResponseEntity<CategoriaDto> modificarCategoria
            (@PathVariable Long idCategoria, @RequestBody ModCategoriaDto modCategoria) {
        return ResponseEntity.ok(this.categoriaService.modificarCategoria(idCategoria, modCategoria));
    }


    @DeleteMapping("/{idCategoria}")
    @Operation(
            summary = "Eliminar Categoria existentes segun su Identificador ",
            description = "No retorna ningun mensaje, pero ejecuta la accion de Eliminacion segun su Identifiacador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoria Eliminadas"),
                    @ApiResponse(responseCode = "404", description = "Categoria no Eliminadas", content = @Content)
            }
    )

    //Eliminar
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long idCategoria) {
        this.categoriaService.eliminarCategoria(idCategoria);
        return ResponseEntity.ok().build();
    }
}
