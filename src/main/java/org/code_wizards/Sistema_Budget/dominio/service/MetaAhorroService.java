package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.MetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.dto.ModMetaAhorroDto;
import org.code_wizards.Sistema_Budget.dominio.repository.MetaAhorroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaAhorroService {

    private final MetaAhorroRepository metaAhorroRepository;

    public MetaAhorroService(MetaAhorroRepository metaAhorroRepository) {

        this.metaAhorroRepository = metaAhorroRepository;
    }

    public List<MetaAhorroDto> obtenerTodos() {

        return this.metaAhorroRepository.obtenerTodos();
    }

    public MetaAhorroDto buscarPorCodigos(Long codigo) {

        return this.metaAhorroRepository.buscarPorCodigos(codigo);
    }

    public MetaAhorroDto guardarAhorro(MetaAhorroDto categoriaDto) {
        return this.metaAhorroRepository.guardarAhorro(categoriaDto);
    }

    public MetaAhorroDto modificarAhorro(Long codigo, ModMetaAhorroDto modMetaAhorro) {
        return this.metaAhorroRepository.modificarAhorro(codigo, modMetaAhorro);
    }

    public void eliminarAhorro(Long codigo) {

        this.metaAhorroRepository.eliminarAhorro(codigo);
    }
}
