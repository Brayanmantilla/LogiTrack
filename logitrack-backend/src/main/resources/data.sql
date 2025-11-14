-- ========================================
-- LOGITRACK - DATOS DE PRUEBA
-- ========================================

-- USUARIOS (Contraseñas encriptadas con BCrypt)
-- admin123 = $2a$10$X5wFWMLsYMW8CoNRVlW5GuPs4rIiXH7UOC5I7mL8WxRvgYJYZjjQC
-- empleado123 = $2a$10$Y6xHVNMsZNX9DpOSWmX6HuQt5sJjYI8VPD6J9nM9XySwhzKZYkkRC

INSERT INTO usuarios (nombre, email, password, rol, activo, fecha_creacion, fecha_actualizacion) VALUES
('Administrador Principal', 'admin@logitrack.com', '$2a$10$X5wFWMLsYMW8CoNRVlW5GuPs4rIiXH7UOC5I7mL8WxRvgYJYZjjQC', 'ADMIN', TRUE, NOW(), NOW()),
('Juan Pérez', 'juan.perez@logitrack.com', '$2a$10$Y6xHVNMsZNX9DpOSWmX6HuQt5sJjYI8VPD6J9nM9XySwhzKZYkkRC', 'EMPLEADO', TRUE, NOW(), NOW()),
('María García', 'maria.garcia@logitrack.com', '$2a$10$Y6xHVNMsZNX9DpOSWmX6HuQt5sJjYI8VPD6J9nM9XySwhzKZYkkRC', 'EMPLEADO', TRUE, NOW(), NOW()),
('Carlos Rodríguez', 'carlos.rodriguez@logitrack.com', '$2a$10$Y6xHVNMsZNX9DpOSWmX6HuQt5sJjYI8VPD6J9nM9XySwhzKZYkkRC', 'EMPLEADO', TRUE, NOW(), NOW());

-- BODEGAS
INSERT INTO bodegas (nombre, ubicacion, capacidad, encargado, activa, fecha_creacion, fecha_actualizacion) VALUES
('Bodega Central', 'Bogotá, Calle 100 #50-30', 5000, 'Juan Pérez', TRUE, NOW(), NOW()),
('Bodega Norte', 'Barranquilla, Av. Olaya Herrera #45-67', 3000, 'María García', TRUE, NOW(), NOW()),
('Bodega Sur', 'Cali, Carrera 5 #15-20', 4000, 'Carlos Rodríguez', TRUE, NOW(), NOW()),
('Bodega Pacífico', 'Buenaventura, Puerto Industrial', 2500, 'Ana Martínez', TRUE, NOW(), NOW());

-- PRODUCTOS
INSERT INTO productos (nombre, categoria, stock, precio, descripcion, activo, fecha_creacion, fecha_actualizacion) VALUES
-- Electrónica
('Laptop Dell Inspiron', 'Electrónica', 45, 2500000.00, 'Laptop empresarial con procesador i5', TRUE, NOW(), NOW()),
('Mouse Logitech', 'Electrónica', 8, 45000.00, 'Mouse inalámbrico ergonómico', TRUE, NOW(), NOW()),
('Teclado Mecánico', 'Electrónica', 120, 180000.00, 'Teclado mecánico RGB para gaming', TRUE, NOW(), NOW()),
('Monitor LG 24"', 'Electrónica', 67, 650000.00, 'Monitor Full HD IPS', TRUE, NOW(), NOW()),

-- Oficina
('Silla Ergonómica', 'Oficina', 5, 450000.00, 'Silla de oficina con soporte lumbar', TRUE, NOW(), NOW()),
('Escritorio Ejecutivo', 'Oficina', 89, 850000.00, 'Escritorio en L con cajones', TRUE, NOW(), NOW()),
('Lámpara LED', 'Oficina', 156, 75000.00, 'Lámpara de escritorio ajustable', TRUE, NOW(), NOW()),

-- Almacenamiento
('Estantería Metálica', 'Almacenamiento', 3, 320000.00, 'Estantería industrial 2m altura', TRUE, NOW(), NOW()),
('Cajas Plásticas', 'Almacenamiento', 456, 12000.00, 'Cajas organizadoras apilables', TRUE, NOW(), NOW()),

-- Tecnología
('Router WiFi 6', 'Tecnología', 34, 250000.00, 'Router de alta velocidad', TRUE, NOW(), NOW()),
('Switch 24 Puertos', 'Tecnología', 7, 890000.00, 'Switch administrable gigabit', TRUE, NOW(), NOW()),
('Cámara Seguridad', 'Tecnología', 67, 320000.00, 'Cámara IP con visión nocturna', TRUE, NOW(), NOW());

-- MOVIMIENTOS DE EJEMPLO
INSERT INTO movimientos (fecha, tipo_movimiento, usuario_id, bodega_origen_id, bodega_destino_id, producto_id, cantidad, observaciones, fecha_creacion) VALUES
-- Entradas
(NOW(), 'ENTRADA', 2, NULL, 1, 1, 50, 'Compra inicial de laptops', NOW()),
(NOW(), 'ENTRADA', 2, NULL, 1, 3, 150, 'Stock nuevo de teclados', NOW()),
(NOW(), 'ENTRADA', 3, NULL, 2, 4, 80, 'Monitores para sucursal', NOW()),

-- Transferencias
(NOW(), 'TRANSFERENCIA', 2, 1, 2, 1, 10, 'Transfer para nueva oficina', NOW()),
(NOW(), 'TRANSFERENCIA', 3, 1, 3, 3, 30, 'Reubicación de inventario', NOW()),

-- Salidas
(NOW(), 'SALIDA', 4, 2, NULL, 2, 5, 'Venta a cliente corporativo', NOW()),
(NOW(), 'SALIDA', 2, 1, NULL, 7, 20, 'Instalación oficinas', NOW());