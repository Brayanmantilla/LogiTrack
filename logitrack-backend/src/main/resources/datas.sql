-- ============================================
-- LOGITRACK - DATA SQL
-- Datos de prueba para el sistema
-- ============================================

-- ============================================
-- USUARIOS (Password: admin123 - Hash BCrypt)
-- ============================================
INSERT INTO usuarios (nombre, email, password, rol, activo, fecha_creacion, fecha_actualizacion) 
VALUES 
('Administrador Principal', 'admin@logitrack.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 1, NOW(), NOW()),
('Juan Pérez', 'juan@logitrack.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'EMPLEADO', 1, NOW(), NOW()),
('María García', 'maria@logitrack.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'EMPLEADO', 1, NOW(), NOW());

-- ============================================
-- BODEGAS
-- ============================================
INSERT INTO bodegas (nombre, ubicacion, capacidad, encargado, activa, fecha_creacion, fecha_actualizacion) 
VALUES 
('Bodega Principal', 'Calle 100 #15-20, Bogotá', 5000, 'Juan Pérez', 1, NOW(), NOW()),
('Bodega Norte', 'Av. Caracas #45-10, Bogotá', 3000, 'María García', 1, NOW(), NOW()),
('Bodega Sur', 'Cra. 30 #80-50, Bogotá', 2500, 'Carlos Ruiz', 1, NOW(), NOW()),
('Bodega Centro', 'Calle 50 #25-15, Bogotá', 1500, 'Ana López', 1, NOW(), NOW());

-- ============================================
-- PRODUCTOS
-- ============================================
INSERT INTO productos (nombre, categoria, descripcion, precio, stock, activo, fecha_creacion, fecha_actualizacion) 
VALUES 
('Laptop HP ProBook', 'Computadoras', 'Laptop HP ProBook 450 G9, Intel Core i5, 8GB RAM, 256GB SSD', 2500000.00, 25, 1, NOW(), NOW()),
('Mouse Logitech', 'Periféricos', 'Mouse inalámbrico Logitech M185', 45000.00, 150, 1, NOW(), NOW()),
('Teclado Genius', 'Periféricos', 'Teclado USB Genius KB-125', 35000.00, 80, 1, NOW(), NOW()),
('Monitor Samsung 24"', 'Monitores', 'Monitor Samsung 24 pulgadas Full HD', 650000.00, 40, 1, NOW(), NOW()),
('Cable HDMI 2m', 'Accesorios', 'Cable HDMI 2.0 de 2 metros', 25000.00, 200, 1, NOW(), NOW()),
('Disco Duro Externo 1TB', 'Almacenamiento', 'Disco duro externo Western Digital 1TB USB 3.0', 180000.00, 60, 1, NOW(), NOW()),
('Memoria USB 32GB', 'Almacenamiento', 'Memoria USB SanDisk 32GB USB 3.0', 28000.00, 300, 1, NOW(), NOW()),
('Impresora HP LaserJet', 'Impresoras', 'Impresora HP LaserJet Pro M404n', 850000.00, 15, 1, NOW(), NOW()),
('Webcam Logitech', 'Periféricos', 'Webcam Logitech C920 HD Pro', 280000.00, 35, 1, NOW(), NOW()),
('Audífonos Sony', 'Audio', 'Audífonos Sony WH-1000XM4 inalámbricos', 950000.00, 45, 1, NOW(), NOW());

-- ============================================
-- MOVIMIENTOS
-- ============================================
INSERT INTO movimientos (tipo_movimiento, producto_id, bodega_origen_id, bodega_destino_id, cantidad, observaciones, usuario_id, fecha, fecha_creacion) 
VALUES 
-- Entradas
('ENTRADA', 1, NULL, 1, 10, 'Compra inicial de laptops', 1, DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_SUB(NOW(), INTERVAL 5 DAY)),
('ENTRADA', 2, NULL, 1, 50, 'Reabastecimiento de mouse', 1, DATE_SUB(NOW(), INTERVAL 4 DAY), DATE_SUB(NOW(), INTERVAL 4 DAY)),
('ENTRADA', 3, NULL, 1, 30, 'Compra de teclados', 2, DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY)),
('ENTRADA', 10, NULL, 1, 50, 'Compra de audífonos Sony', 1, DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY)),

-- Salidas
('SALIDA', 1, 1, NULL, 5, 'Venta a cliente corporativo', 2, DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_SUB(NOW(), INTERVAL 2 DAY)),
('SALIDA', 2, 1, NULL, 20, 'Distribución a sucursales', 2, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY)),
('SALIDA', 10, 1, NULL, 5, 'Venta al por mayor', 3, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY)),

-- Transferencias
('TRANSFERENCIA', 3, 1, 2, 15, 'Transferencia a bodega norte', 1, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY)),
('TRANSFERENCIA', 4, 1, 3, 10, 'Transferencia a bodega sur', 2, NOW(), NOW()),
('TRANSFERENCIA', 7, 1, 4, 50, 'Distribución de memorias USB', 1, NOW(), NOW());

-- ============================================
-- FIN DE DATOS DE PRUEBA
-- ============================================