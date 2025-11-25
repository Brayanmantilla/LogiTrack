package com.logitrack.TEST1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.model.Auditoria;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/Auditoria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class UditoriaController {
 @Query("SELECT SUM(p.stock) FROM Producto p")
Long calcularStockTotal();

@GetMapping("/productos-movidos-mes")
public ResponseEntity<List<Map<String, Object>>> obtenerProductosMovidosMes() {
List<Map<String, Object>> resultado =
reporteService.obtenerProductosMovidosUltimoMes();
return ResponseEntity.ok(resultado);
}

}
