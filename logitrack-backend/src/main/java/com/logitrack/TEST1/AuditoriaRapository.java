package com.logitrack.TEST1;
import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.productos;

public class AuditoriaRapository {
    
    @GetMapping("/productos-movidos-mes")
    public ResponseEntity<List<Map<String, Object>>> obtenerProductosMovidosMes() {
    List<Map<String, Object>> resultado =
    reporteService.obtenerProductosMovidosUltimoMes();
    return ResponseEntity.ok(resultado);
    }

}
