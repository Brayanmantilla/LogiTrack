package com.logitrack.controller;

import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.TipoOperacion;
import com.logitrack.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auditorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuditoriaController {
    
    private final AuditoriaService auditoriaService;
    
    @GetMapping
    public ResponseEntity<List<Auditoria>> getAllAuditorias() {
        return ResponseEntity.ok(auditoriaService.getAllAuditorias());
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Auditoria>> getAuditoriasByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(auditoriaService.getAuditoriasByUsuario(usuarioId));
    }
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Auditoria>> getAuditoriasByTipo(@PathVariable TipoOperacion tipo) {
        return ResponseEntity.ok(auditoriaService.getAuditoriasByTipo(tipo));
    }
    
    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Auditoria>> getAuditoriasByFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(auditoriaService.getAuditoriasByFechas(inicio, fin));
    }
}