package com.logitrack.model;

import com.logitrack.model.enums.TipoOperacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    @Column(nullable = false, length = 100)
    private String entidad;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacion", nullable = false, length = 20)
    private TipoOperacion tipoOperacion;
    
    @Column(name = "fecha_operacion", nullable = false)
    private LocalDateTime fechaOperacion;
    
    @Column(length = 1000)
    private String detalles;
    
    @Column(name = "usuario_id")
    private Long usuarioId;

    // Setters adicionales para compatibilidad con AuditoriaService
    public void setEntidadAfectada(String entidad) {
        this.entidad = entidad;
    }

    public void setEntidadId(Long entidadId) {
        // Campo no existe en la BD actual, ignorar
    }

    public void setUsuario(Object usuario) {
        if (usuario instanceof Usuario) {
            this.usuarioId = ((Usuario) usuario).getId();
        }
    }

    public void setDireccionIp(String ip) {
        // Campo no existe en la BD actual, ignorar
    }

    public void setValorAnterior(String valor) {
        // Usar detalles para guardar esta info
        if (this.detalles == null) {
            this.detalles = "Anterior: " + valor;
        } else {
            this.detalles += " | Anterior: " + valor;
        }
    }

    public void setValorNuevo(String valor) {
        // Usar detalles para guardar esta info
        if (this.detalles == null) {
            this.detalles = "Nuevo: " + valor;
        } else {
            this.detalles += " | Nuevo: " + valor;
        }
    }
}