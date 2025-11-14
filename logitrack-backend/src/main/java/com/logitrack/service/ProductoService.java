package com.logitrack.service;

import com.logitrack.model.Producto;
import com.logitrack.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    @Transactional(readOnly = true)
    public List<Producto> getAllProductos() {
        log.info("Obteniendo todos los productos");
        return productoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Producto> getProductosActivos() {
        log.info("Obteniendo productos activos");
        return productoRepository.findByActivoTrue();
    }
    
    @Transactional(readOnly = true)
    public List<Producto> getProductosConStockBajo() {
        log.info("Obteniendo productos con stock bajo");
        return productoRepository.findProductosConStockBajo();
    }
    
    @Transactional(readOnly = true)
    public Producto getProductoById(Long id) {
        log.info("Obteniendo producto con id: {}", id);
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
    
    @Transactional
    public Producto createProducto(Producto producto) {
        log.info("Creando nuevo producto: {}", producto.getNombre());
        return productoRepository.save(producto);
    }
    
    @Transactional
    public Producto updateProducto(Long id, Producto productoDetails) {
        log.info("Actualizando producto con id: {}", id);
        Producto producto = getProductoById(id);
        
        producto.setNombre(productoDetails.getNombre());
        producto.setCategoria(productoDetails.getCategoria());
        producto.setStock(productoDetails.getStock());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setDescripcion(productoDetails.getDescripcion());
        
        return productoRepository.save(producto);
    }
    
    @Transactional
    public void deleteProducto(Long id) {
        log.info("Eliminando producto con id: {}", id);
        Producto producto = getProductoById(id);
        producto.setActivo(false);
        productoRepository.save(producto);
    }
}