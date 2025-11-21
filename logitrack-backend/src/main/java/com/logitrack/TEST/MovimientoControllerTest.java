package com.logitrack.TEST;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.model.Movimiento;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MovimientoControllerTest {
    
    private final MovimientoServiceTest movimientoServicetest;


    // 1. PRIMER PUNTO PARCIAL
    @GetMapping("/ultimos")
    public ResponseEntity<List<Movimiento>> listarRecientes(
        @RequestParam(defaultValue = "10") Integer cantidad) {
        List<Movimiento> movimientos =
        movimientoServicetest.listarRecientes(cantidad);
        return ResponseEntity.ok(movimientos);
    }

    // 2. SEGUNDO PUNTO PARCIAL
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Movimiento>> obtenerPorTipo(
        @PathVariable String tipo) throws RelationNotFoundException {
        List<Movimiento> movimientos = movimientoServicetest.obtenerPorTipo(tipo);
        return ResponseEntity.ok(movimientos);
    }
}
