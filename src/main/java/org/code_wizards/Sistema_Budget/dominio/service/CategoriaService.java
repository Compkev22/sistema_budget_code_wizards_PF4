package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.CategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModCategoriaDto;
import org.code_wizards.Sistema_Budget.dominio.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {

        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDto> obtenerTodo() {

        return this.categoriaRepository.obtenerTodo();
    }

    public CategoriaDto buscarPorCodigo(Long codigo) {

        return this.categoriaRepository.buscarPorCodigo(codigo);
    }

    public CategoriaDto guardarCategoria(CategoriaDto categoriaDto) {
        return this.categoriaRepository.guardarCategoria(categoriaDto);
    }

    public CategoriaDto modificarCategoria(Long codigo, ModCategoriaDto modCategoria) {
        return this.categoriaRepository.modificarCategoria(codigo, modCategoria);
    }

    public void eliminarCategoria(Long codigo) {

        this.categoriaRepository.eliminarCategoria(codigo);
    }
}
