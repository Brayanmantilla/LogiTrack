package com.logitrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.EntityListeners;


import com.logitrack.audit.AuditListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "bodegas")
@EntityListeners(AuditListener.class) 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bodega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 200, message = "La ubicación no puede exceder 200 caracteres")
    @Column(nullable = false, length = 200)
    private String ubicacion;
    
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer capacidad;
    
    @NotBlank(message = "El encargado es obligatorio")
    @Size(max = 100, message = "El nombre del encargado no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String encargado;
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}