-- ============================================
-- LOGITRACK - SCHEMA SQL
-- Sistema de Gestión de Bodegas
-- ============================================

-- Eliminar tablas si existen (orden inverso por foreign keys)
DROP TABLE IF EXISTS auditorias;
DROP TABLE IF EXISTS movimientos;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS bodegas;
DROP TABLE IF EXISTS usuarios;

-- ============================================
-- TABLA: usuarios
-- ============================================
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'EMPLEADO') NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    fecha_creacion DATETIME(6) NOT NULL,
    fecha_actualizacion DATETIME(6) NOT NULL,
    INDEX idx_email (email),
    INDEX idx_rol (rol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: bodegas
-- ============================================
CREATE TABLE bodegas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(200) NOT NULL,
    capacidad INT NOT NULL,
    encargado VARCHAR(100) NOT NULL,
    activa BIT(1) NOT NULL DEFAULT 1,
    fecha_creacion DATETIME(6) NOT NULL,
    fecha_actualizacion DATETIME(6) NOT NULL,
    INDEX idx_nombre (nombre),
    INDEX idx_activa (activa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: productos
-- ============================================
CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    descripcion VARCHAR(500),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    activo BIT(1) NOT NULL DEFAULT 1,
    fecha_creacion DATETIME(6) NOT NULL,
    fecha_actualizacion DATETIME(6) NOT NULL,
    INDEX idx_nombre (nombre),
    INDEX idx_categoria (categoria),
    INDEX idx_stock (stock),
    INDEX idx_activo (activo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: movimientos
-- ============================================
CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimiento ENUM('ENTRADA', 'SALIDA', 'TRANSFERENCIA') NOT NULL,
    producto_id BIGINT NOT NULL,
    bodega_origen_id BIGINT,
    bodega_destino_id BIGINT,
    cantidad INT NOT NULL,
    observaciones VARCHAR(500),
    usuario_id BIGINT NOT NULL,
    fecha DATETIME(6) NOT NULL,
    fecha_creacion DATETIME(6) NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE RESTRICT,
    FOREIGN KEY (bodega_origen_id) REFERENCES bodegas(id) ON DELETE RESTRICT,
    FOREIGN KEY (bodega_destino_id) REFERENCES bodegas(id) ON DELETE RESTRICT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE RESTRICT,
    INDEX idx_tipo_movimiento (tipo_movimiento),
    INDEX idx_producto_id (producto_id),
    INDEX idx_fecha (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: auditorias
-- ============================================
CREATE TABLE auditorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    entidad VARCHAR(100) NOT NULL,
    tipo_operacion ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    fecha_operacion DATETIME(6) NOT NULL,
    detalles VARCHAR(1000),
    usuario_id BIGINT,
    INDEX idx_entidad (entidad),
    INDEX idx_tipo_operacion (tipo_operacion),
    INDEX idx_fecha_operacion (fecha_operacion),
    INDEX idx_usuario_id (usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;