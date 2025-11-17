package com.logitrack.service;

import com.logitrack.model.Auditoria;
import com.logitrack.model.Usuario;
import com.logitrack.model.enums.TipoOperacion;
import com.logitrack.repository.AuditoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void registrarAuditoria(
            TipoOperacion tipoOperacion,
            String entidadAfectada,
            Long entidadId,
            Usuario usuario,
            Object valorAnterior,
            Object valorNuevo,
            String direccionIp) {
        try {
            Auditoria auditoria = new Auditoria();
            auditoria.setTipoOperacion(tipoOperacion);
            auditoria.setEntidadAfectada(entidadAfectada);
            auditoria.setEntidadId(entidadId);
            auditoria.setUsuario(usuario);
            auditoria.setDireccionIp(direccionIp);

            if (valorAnterior != null) {
                auditoria.setValorAnterior(objectMapper.writeValueAsString(valorAnterior));
            }

            if (valorNuevo != null) {
                auditoria.setValorNuevo(objectMapper.writeValueAsString(valorNuevo));
            }

            auditoriaRepository.save(auditoria);
            log.info("Auditoría registrada: {} - {} ID: {}", tipoOperacion, entidadAfectada, entidadId);
        } catch (Exception e) {
            log.error("Error al registrar auditoría", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Auditoria> getAllAuditorias() {
        return auditoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Auditoria> getAuditoriasByUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioId(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<Auditoria> getAuditoriasByTipo(TipoOperacion tipo) {
        return auditoriaRepository.findByTipoOperacion(tipo);
    }

    @Transactional(readOnly = true)
public List<Auditoria> getAuditoriasPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
    return auditoriaRepository.findByFechaOperacionBetween(inicio, fin);
}
}