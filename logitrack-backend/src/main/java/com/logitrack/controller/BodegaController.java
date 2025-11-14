package com.logitrack.controller;

import com.logitrack.model.Bodega;
import com.logitrack.service.BodegaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BodegaController {
    
    private final BodegaService bodegaService;
    
    @GetMapping
    public ResponseEntity<List<Bodega>> getAllBodegas() {
        return ResponseEntity.ok(bodegaService.getAllBodegas());
    }
    
    @GetMapping("/activas")
    public ResponseEntity<List<Bodega>> getBodegasActivas() {
        return ResponseEntity.ok(bodegaService.getBodegasActivas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> getBodegaById(@PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.getBodegaById(id));
    }
    
    @PostMapping
    public ResponseEntity<Bodega> createBodega(@Valid @RequestBody Bodega bodega) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(bodegaService.createBodega(bodega));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Bodega> updateBodega(
            @PathVariable Long id,
            @Valid @RequestBody Bodega bodega) {
        return ResponseEntity.ok(bodegaService.updateBodega(id, bodega));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBodega(@PathVariable Long id) {
        bodegaService.deleteBodega(id);
        return ResponseEntity.noContent().build();
    }
}