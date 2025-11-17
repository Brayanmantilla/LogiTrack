package com.logitrack.audit;

import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.TipoOperacion;
import com.logitrack.repository.AuditoriaRepository;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class AuditListener {

    private static AuditoriaRepository auditoriaRepository;

    @Autowired
    public void setAuditoriaRepository(AuditoriaRepository auditoriaRepository) {
        AuditListener.auditoriaRepository = auditoriaRepository;
    }

    @PostPersist
    public void afterCreate(Object entity) {
        log.info("Entidad creada: {}", entity.getClass().getSimpleName());
        registrarAuditoria(entity, TipoOperacion.INSERT);
    }

    @PostUpdate
    public void afterUpdate(Object entity) {
        log.info("Entidad actualizada: {}", entity.getClass().getSimpleName());
        registrarAuditoria(entity, TipoOperacion.UPDATE);
    }

    @PostRemove
    public void afterDelete(Object entity) {
        log.info("Entidad eliminada: {}", entity.getClass().getSimpleName());
        registrarAuditoria(entity, TipoOperacion.DELETE);
    }

    private void registrarAuditoria(Object entity, TipoOperacion tipoOperacion) {
        try {
            // No auditar las auditorías (evitar recursión infinita)
            if (entity instanceof Auditoria) {
                return;
            }

            Auditoria auditoria = new Auditoria();
            auditoria.setEntidad(entity.getClass().getSimpleName());
            auditoria.setTipoOperacion(tipoOperacion);
            auditoria.setFechaOperacion(LocalDateTime.now());
            auditoria.setDetalles(entity.toString());
            
            // Por ahora sin usuario (lo agregaremos después con Spring Security)
            auditoria.setUsuarioId(1L); // Usuario por defecto

            if (auditoriaRepository != null) {
                auditoriaRepository.save(auditoria);
                log.info("Auditoría registrada: {} - {}", tipoOperacion, entity.getClass().getSimpleName());
            }
        } catch (Exception e) {
            log.error("Error registrando auditoría: {}", e.getMessage());
        }
    }
}