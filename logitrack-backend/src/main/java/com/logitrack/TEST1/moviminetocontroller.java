
package com.logitrack.TEST1;
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
public class moviminetocontroller {
private final MovimientoServiceTest movimientoServicetest;
// movimeintos de inventario 
@GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Movimiento>> obtenerPorTipo(
        @PathVariable String tipo) throws RelationNotFoundException {
        List<Movimiento> movimientos = movimientoServicetest.obtenerPorTipo(tipo);
        return ResponseEntity.ok(movimientos);

@GetMapping("/bodega-origen/{bodegaId}")
    public ResponseEntity<List<Movimiento>> obtenerPorBodegaOrigen(
@PathVariable Long bodegaId) {
    List<Movimiento> movimientos =
    movimientoService.obtenerPorBodegaOrigen(bodegaId);
    return ResponseEntity.ok(movimientos);
    }
}}



