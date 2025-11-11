package com.logitrack.repository;

import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.TipoOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByUsuarioId(Long usuarioId);
    List<Auditoria> findByTipoOperacion(TipoOperacion tipoOperacion);
    List<Auditoria> findByEntidadAfectada(String entidadAfectada);
    List<Auditoria> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}