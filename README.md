# 🚀 LogiTrack - Sistema de Gestión de Bodegas

Sistema backend desarrollado en **Spring Boot** para la gestión y auditoría de bodegas, productos y movimientos de inventario.

## 📋 Descripción del Proyecto

LogiTrack S.A. administra varias bodegas distribuidas en distintas ciudades, encargadas de almacenar productos y gestionar movimientos de inventario (entradas, salidas y transferencias).

Este sistema permite:
- ✅ Controlar todos los movimientos entre bodegas
- ✅ Registrar automáticamente los cambios (auditorías)
- ✅ Proteger la información con autenticación JWT
- ✅ Ofrecer endpoints REST documentados y seguros

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Security** (JWT)
- **Spring Data JPA** (Hibernate)
- **MySQL 8.0**
- **Lombok**
- **Jackson** (JSON processing)
- **BCrypt** (Password encryption)

### Frontend
- **HTML5 / CSS3**
- **JavaScript (Vanilla)**
- **Bootstrap 5.3**
- **Bootstrap Icons**

---

## 📁 Estructura del Proyecto
```
LogiTrack/
├── logitrack-backend/          # Backend Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/logitrack/
│   │   │   │   ├── audit/      # Sistema de auditoría
│   │   │   │   ├── config/     # Configuraciones
│   │   │   │   ├── controller/ # Controladores REST
│   │   │   │   ├── dto/        # Data Transfer Objects
│   │   │   │   ├── entity/     # Entidades JPA
│   │   │   │   ├── exception/  # Manejo de excepciones
│   │   │   │   ├── model/      # Modelos de dominio
│   │   │   │   ├── repository/ # Repositorios JPA
│   │   │   │   ├── security/   # Seguridad JWT
│   │   │   │   └── service/    # Lógica de negocio
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── schema.sql
│   │   │       └── data.sql
│   │   └── test/
│   └── pom.xml
│
└── logitrack-frontend/         # Frontend Web
    ├── css/
    │   └── style.css
    ├── js/
    │   ├── api.js
    │   ├── auth.js
    │   └── main.js
    ├── index.html              # Login
    ├── dashboard.html          # Panel principal
    ├── bodegas.html            # Gestión de bodegas
    ├── productos.html          # Gestión de productos
    └── movimientos.html        # Registro de movimientos
```

---

## ⚙️ Instalación y Configuración

### Requisitos Previos
- Java JDK 17 o superior
- MySQL 8.0 o superior
- Maven 3.9+
- Navegador web moderno

### 1. Configurar Base de Datos
```sql
CREATE DATABASE logitrack_db;
USE logitrack_db;

-- Ejecutar schema.sql y data.sql
SOURCE schema.sql;
SOURCE data.sql;
```

### 2. Configurar Backend

Editar `logitrack-backend/src/main/resources/application.properties`:
```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/logitrack_db
spring.datasource.username=root
spring.datasource.password=tu_password

# JWT
jwt.secret=tu_secret_key_aqui
jwt.expiration=86400000
```

### 3. Ejecutar Backend
```bash
cd logitrack-backend
./mvnw spring-boot:run

# Windows:
.\mvnw.cmd spring-boot:run
```

El backend se ejecutará en: `http://localhost:8080/api`

### 4. Ejecutar Frontend

Abrir `logitrack-frontend/index.html` con **Live Server** en VS Code o cualquier servidor HTTP.

URL: `http://localhost:5500/logitrack-frontend/index.html`

---

## 🔐 Credenciales de Acceso

### Usuario Administrador
- **Email:** admin@logitrack.com
- **Password:** admin123
- **Rol:** ADMIN

### Usuario Empleado
- **Email:** juan@logitrack.com
- **Password:** admin123
- **Rol:** EMPLEADO

---

## 📡 Endpoints Principales

### Autenticación
```
POST   /api/auth/login       - Iniciar sesión
POST   /api/auth/register    - Registrar usuario
```

### Bodegas
```
GET    /api/bodegas          - Listar todas las bodegas
GET    /api/bodegas/{id}     - Obtener bodega por ID
POST   /api/bodegas          - Crear bodega
PUT    /api/bodegas/{id}     - Actualizar bodega
DELETE /api/bodegas/{id}     - Eliminar bodega
GET    /api/bodegas/activas  - Listar bodegas activas
```

### Productos
```
GET    /api/productos        - Listar todos los productos
GET    /api/productos/{id}   - Obtener producto por ID
POST   /api/productos        - Crear producto
PUT    /api/productos/{id}   - Actualizar producto
DELETE /api/productos/{id}   - Eliminar producto
GET    /api/productos/activos      - Listar productos activos
GET    /api/productos/stock-bajo   - Productos con stock bajo
```

### Movimientos
```
GET    /api/movimientos      - Listar todos los movimientos
GET    /api/movimientos/{id} - Obtener movimiento por ID
POST   /api/movimientos      - Crear movimiento
```

### Auditorías
```
GET    /api/auditorias       - Listar todas las auditorías
GET    /api/auditorias/usuario/{id}     - Auditorías por usuario
GET    /api/auditorias/tipo/{tipo}      - Auditorías por tipo
GET    /api/auditorias/fechas           - Auditorías por rango de fechas
```

---

## 🧪 Pruebas con cURL

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@logitrack.com","password":"admin123"}'
```

### Crear Producto (con JWT)
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "nombre": "Producto Test",
    "categoria": "Prueba",
    "precio": 1000,
    "stock": 10,
    "activo": true
  }'
```

---

## 🔒 Seguridad

- **Autenticación:** JWT (JSON Web Tokens)
- **Encriptación:** BCrypt para contraseñas
- **Protección CSRF:** Deshabilitado para API REST
- **CORS:** Configurado para permitir frontend
- **Validaciones:** Bean Validation (JSR-380)

---

## 📊 Características Principales

### 1. Gestión de Bodegas
- CRUD completo de bodegas
- Validación de capacidad
- Estado activo/inactivo

### 2. Gestión de Productos
- CRUD completo de productos
- Control de stock
- Categorización
- Alertas de stock bajo

### 3. Movimientos de Inventario
- **Entradas:** Ingreso de productos a bodega
- **Salidas:** Egreso de productos
- **Transferencias:** Movimiento entre bodegas
- Validación de stock disponible

### 4. Sistema de Auditoría
- Registro automático de cambios
- Trazabilidad completa
- Filtros por fecha, tipo y usuario

---

## 👥 Autor

**Brayan Mantilla**
**Sofia Novoa**

---

## 📄 Licencia

Este proyecto fue desarrollado como parte de un proyecto académico para CampusLands.

---

## 🚀 Roadmap Futuro

- [ ] Documentación Swagger/OpenAPI
- [ ] Reportes en PDF/Excel
- [ ] Dashboard con gráficas
- [ ] Notificaciones por email
- [ ] Sistema de roles avanzado
- [ ] Backup automático
- [ ] Despliegue en la nube


