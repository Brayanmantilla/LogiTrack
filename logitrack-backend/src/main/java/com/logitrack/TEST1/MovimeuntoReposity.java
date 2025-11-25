package com.logitrack.TEST1;

import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;

public interface MovimientoRepositoryTest extends JpaRepository<Movimiento, Long>  {

    @Query("SELECT m FROM Movimiento m ORDER BY m.fecha DESC")
    List<Movimiento> findTopNByOrderByFechaDesc()
    @GetMapping("/rango-precio")
    public ResponseEntity<List<Producto>> obtenerPorRangoPrecio(
    @RequestParam Double min,
    @RequestParam Double max) {
    List<Producto> productos = productoService.obtenerPorRangoPrecio(min, max);
    return ResponseEntity.ok(productos);
    
}}