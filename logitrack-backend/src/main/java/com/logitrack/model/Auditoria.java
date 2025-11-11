package com.logitrack.model;

import com.logitrack.model.enums.TipoOperacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacion", nullable = false, length = 20)
    private TipoOperacion tipoOperacion;
    
    @Column(name = "entidad_afectada", nullable = false, length = 100)
    private String entidadAfectada;
    
    @Column(name = "entidad_id", nullable = false)
    private Long entidadId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;
    
    @Column(name = "valor_nuevo", columnDefinition = "TEXT")
    private String valorNuevo;
    
    @CreationTimestamp
    @Column(name = "fecha_hora", nullable = false, updatable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "direccion_ip", length = 45)
    private String direccionIp;
}