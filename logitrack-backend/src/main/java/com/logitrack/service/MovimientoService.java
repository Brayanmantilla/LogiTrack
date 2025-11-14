package com.logitrack.service;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;
import com.logitrack.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoService {
    
    private final MovimientoRepository movimientoRepository;
    
    @Transactional(readOnly = true)
    public List<Movimiento> getAllMovimientos() {
        log.info("Obteniendo todos los movimientos");
        return movimientoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Movimiento getMovimientoById(Long id) {
        log.info("Obteniendo movimiento con id: {}", id);
        return movimientoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con id: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<Movimiento> getMovimientosByFechas(LocalDateTime inicio, LocalDateTime fin) {
        log.info("Obteniendo movimientos entre {} y {}", inicio, fin);
        return movimientoRepository.findByFechaBetween(inicio, fin);
    }
    
    @Transactional(readOnly = true)
    public List<Movimiento> getMovimientosByTipo(TipoMovimiento tipo) {
        log.info("Obteniendo movimientos de tipo: {}", tipo);
        return movimientoRepository.findByTipoMovimiento(tipo);
    }
    
    @Transactional
    public Movimiento createMovimiento(Movimiento movimiento) {
        log.info("Creando nuevo movimiento: {}", movimiento.getTipoMovimiento());
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(LocalDateTime.now());
        }
        return movimientoRepository.save(movimiento);
    }
}