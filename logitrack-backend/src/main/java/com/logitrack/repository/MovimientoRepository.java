package com.logitrack.repository;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByTipoMovimiento(TipoMovimiento tipoMovimiento);
    
    @Query("SELECT m FROM Movimiento m WHERE m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByFechaBetween(
        @Param("fechaInicio") LocalDateTime fechaInicio,
        @Param("fechaFin") LocalDateTime fechaFin
    );
    
    List<Movimiento> findByUsuarioId(Long usuarioId);
    List<Movimiento> findByProductoId(Long productoId);
}