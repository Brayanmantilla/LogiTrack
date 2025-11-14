package com.logitrack.controller;

import com.logitrack.model.Movimiento;
import com.logitrack.model.enums.TipoMovimiento;
import com.logitrack.service.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    
    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.getAllMovimientos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.getMovimientoById(id));
    }
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Movimiento>> getMovimientosByTipo(@PathVariable TipoMovimiento tipo) {
        return ResponseEntity.ok(movimientoService.getMovimientosByTipo(tipo));
    }
    
    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Movimiento>> getMovimientosByFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(movimientoService.getMovimientosByFechas(inicio, fin));
    }
    
    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@Valid @RequestBody Movimiento movimiento) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(movimientoService.createMovimiento(movimiento));
    }
}