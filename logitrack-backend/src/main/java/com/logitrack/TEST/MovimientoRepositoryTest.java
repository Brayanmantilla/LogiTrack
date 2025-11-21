package com.logitrack.TEST;

import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;

public interface MovimientoRepositoryTest extends JpaRepository<Movimiento, Long>  {

    // 1. PRIMER PUNTO PARCIAL
    @Query("SELECT m FROM Movimiento m ORDER BY m.fecha DESC")
        List<Movimiento> findTopNByOrderByFechaDesc(Pageable pageable);
    // 2. SEGUNDO PUNTO PARCIAL
    List<Movimiento> findByTipo(TipoMovimiento tipo);
}
