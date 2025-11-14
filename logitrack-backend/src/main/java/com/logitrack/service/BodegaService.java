package com.logitrack.service;

import com.logitrack.model.Bodega;
import com.logitrack.repository.BodegaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BodegaService {
    
    private final BodegaRepository bodegaRepository;
    
    @Transactional(readOnly = true)
    public List<Bodega> getAllBodegas() {
        log.info("Obteniendo todas las bodegas");
        return bodegaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Bodega> getBodegasActivas() {
        log.info("Obteniendo bodegas activas");
        return bodegaRepository.findByActivaTrue();
    }
    
    @Transactional(readOnly = true)
    public Bodega getBodegaById(Long id) {
        log.info("Obteniendo bodega con id: {}", id);
        return bodegaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));
    }
    
    @Transactional
    public Bodega createBodega(Bodega bodega) {
        log.info("Creando nueva bodega: {}", bodega.getNombre());
        return bodegaRepository.save(bodega);
    }
    
    @Transactional
    public Bodega updateBodega(Long id, Bodega bodegaDetails) {
        log.info("Actualizando bodega con id: {}", id);
        Bodega bodega = getBodegaById(id);
        
        bodega.setNombre(bodegaDetails.getNombre());
        bodega.setUbicacion(bodegaDetails.getUbicacion());
        bodega.setCapacidad(bodegaDetails.getCapacidad());
        bodega.setEncargado(bodegaDetails.getEncargado());
        
        return bodegaRepository.save(bodega);
    }
    
    @Transactional
    public void deleteBodega(Long id) {
        log.info("Eliminando bodega con id: {}", id);
        Bodega bodega = getBodegaById(id);
        bodega.setActiva(false);
        bodegaRepository.save(bodega);
    }
}