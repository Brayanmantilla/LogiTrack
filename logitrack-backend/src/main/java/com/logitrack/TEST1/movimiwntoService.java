package com.logitrack.TEST1;


import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;
import com.logitrack.repository.MovimientoRepository;


public class movimiwntoService {

    private final MovimientoRepository movimientoRepository = null;

    @Transactiona
        public List<Movimiento> listarRecientes(Integer cantidad) {
        return movimientoRepository.findTopNByOrderByFechaDesc(
        PageRequest.of(0, cantidad)
        );
        LocalDateTime hoy = LocalDate.now().atStartOfDay();
        LocalDateTime mañana = LocalDate.now().plusDays(1).atStartOfDay();
        resumen.put("movimientosHoy",
        movimientoRepository.countByFechaBetween(hoy, mañana));
        resumen.put("fecha", LocalDate.now());
        return resumen;
    }

    @Transactional
    List<Producto> findByPrecioBetween(Double min, Double max);
    @GetMapping("/rango-stock")
    public ResponseEntity<List<Producto>> obtenerPorRangoStock(
    @RequestParam Integer min,
    @RequestParam Integer max) {
    List<Producto> productos = productoService.obtenerPorRangoStock(min, max);
    return ResponseEntity.ok(productos);
    }

}
