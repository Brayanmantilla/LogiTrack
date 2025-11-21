package com.logitrack.TEST;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;
import com.logitrack.repository.MovimientoRepository;

public class MovimientoServiceTest {

    private final MovimientoRepository movimientoRepository = null;

    // 1. PRIMER PUNTO PARCIAL
    @Transactional
        public List<Movimiento> listarRecientes(Integer cantidad) {
        return movimientoRepository.findTopNByOrderByFechaDesc(
        PageRequest.of(0, cantidad)
        );
    }

    // 2. SEGUNDO PUNTO PARCIAL
    @Transactional
        public List<Movimiento> obtenerPorTipo(String tipo) throws RelationNotFoundException {
        try {
        TipoMovimiento tipoMovimiento = TipoMovimiento.valueOf(tipo.toUpperCase(
        ));
        return movimientoRepository.findByTipoMovimiento(tipoMovimiento);
        } catch (IllegalArgumentException e) {
        throw new RelationNotFoundException("Tipo inválido: " + tipo);
        }
    }
}
