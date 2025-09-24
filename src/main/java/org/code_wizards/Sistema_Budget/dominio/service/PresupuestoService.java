package org.code_wizards.Sistema_Budget.dominio.service;

import org.code_wizards.Sistema_Budget.dominio.dto.PresupuestoDto;
import org.code_wizards.Sistema_Budget.dominio.exception.*;
import org.code_wizards.Sistema_Budget.dominio.repository.PresupuestoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoService {
    private final PresupuestoRepository presupuestoRepository;

    public PresupuestoService(PresupuestoRepository presupuestoRepository) {this.presupuestoRepository = presupuestoRepository;}

    public List<PresupuestoDto> listarPresupuestos(){return this.presupuestoRepository.obtenerTodo();}

    public PresupuestoDto buscarPorCodigo(Long codigo) { return this.presupuestoRepository.buscarPorCodigo(codigo); }

    public PresupuestoDto guardarPresupuesto(PresupuestoDto presupuestoDto) { return this.presupuestoRepository.guardarPresupuesto(presupuestoDto); }

    public PresupuestoDto modificarPresupuesto(Long codigo, PresupuestoDto modPresupuesto) { return this.presupuestoRepository.modificarPresupuesto(codigo, modPresupuesto); }

    public void eliminarPresupuesto(Long codigo) {
        try {
            this.presupuestoRepository.eliminarPresupuesto(codigo);
        } catch (DataIntegrityViolationException ex) {
            String causa = ex.getMostSpecificCause().getMessage().toLowerCase();
            System.out.println("Mensaje exacto de la excepción: " + causa); // para depuración

            if (causa.contains("categoria")) {
                throw new PresupuestoConCategoriaException(codigo);
            } else if (causa.contains("ingreso")) {
                throw new PresupuestoConIngresoException(codigo);
            } else if (causa.contains("meta_ahorro")) {
                throw new PresupuestoConMetaAhorroException(codigo);
            } else {
                throw new PresupuestoNoEliminableException(
                        "Error de relación en la base de datos: no se puede eliminar este presupuesto porque tiene datos asociados.",
                        "presupuesto-no-eliminable"
                );
            }
        }
    }

}
