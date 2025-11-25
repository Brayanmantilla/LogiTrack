package com.logitrack.TEST1;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.logitrack.model.Auditoria;
import com.logitrack.model.enums.productos;
import com.logitrack.repository.AuditoriaRapository;

public class AuditoriaSevicie {

    public Map<String, Object> obtenerResumenCompleto() {
        Map<String, Object> resumen = new HashMap<>();
    resumen.put("totalBodegas", bodegaRepository.count());
    resumen.put("totalProductos", productoRepository.count());
r   esumen.put("productosStockBajo",
    productoRepository.countByStockLessThan(10));
    resumen.put("valorInventarioTotal"),

    public Map<String, Object> calcularStockTotal() {
        Long total = productoRepository.calcularStockTotal();
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("stockTotal", total != null ? total : 0L);
        return resultado;
    }

    public List<Map<String, Object>> obtenerProductosMovidosUltimoMes() {
        LocalDateTime hace30Dias = LocalDateTime.now().minusDays(30);
        List<Object[]> resultados = movimientoRepository
        .findTopProductosByFechaAfter(hace30Dias, PageRequest.of(0, 10));
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] resultado : resultados) {
        Map<String, Object> item = new HashMap<>();
        item.put("productoId", resultado[0]);
        item.put("productoNombre", resultado[1]);
        item.put("totalMovimientos", resultado[2]);
        lista.add(item);
        }
        return lista;
        }
}
}