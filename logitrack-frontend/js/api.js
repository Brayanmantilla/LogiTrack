// ============================================
// LOGITRACK - CONFIGURACIÓN DE API
// ============================================

// URL base del backend
const API_URL = 'http://localhost:8080/api';

// ============================================
// GESTIÓN DE AUTENTICACIÓN
// ============================================

// Obtener el token JWT almacenado
const getToken = () => {
    return localStorage.getItem('token');
};

// Obtener información del usuario
const getUser = () => {
    const userJson = localStorage.getItem('user');
    return userJson ? JSON.parse(userJson) : null;
};

// Guardar token y usuario en localStorage
const saveAuth = (token, user) => {
    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(user));
};

// Limpiar autenticación (cerrar sesión)
const clearAuth = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
};

// Verificar si el usuario está autenticado
const isAuthenticated = () => {
    return getToken() !== null;
};

// ============================================
// FUNCIÓN GENÉRICA PARA PETICIONES HTTP
// ============================================

const request = async (endpoint, options = {}) => {
    const url = `${API_URL}${endpoint}`;
    
    // Headers por defecto
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };

    // Agregar token si existe
    const token = getToken();
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    try {
        const response = await fetch(url, {
            ...options,
            headers
        });

        // Si es 401 (no autorizado), redirigir al login
        if (response.status === 401) {
            clearAuth();
            window.location.href = 'index.html';
            throw new Error('Sesión expirada');
        }

        // Si es 403 (prohibido)
        if (response.status === 403) {
            throw new Error('No tienes permisos para realizar esta acción');
        }

        // Si es 204 (sin contenido)
        if (response.status === 204) {
            return null;
        }

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || 'Error en la petición');
        }

        return data;
    } catch (error) {
        console.error('Error en la petición:', error);
        throw error;
    }
};

// ============================================
// API DE AUTENTICACIÓN
// ============================================

const authAPI = {
    // Login
    login: (email, password) => {
        return request('/auth/login', {
            method: 'POST',
            body: JSON.stringify({ email, password })
        });
    },

    // Registro
    register: (nombre, email, password, rol) => {
        return request('/auth/register', {
            method: 'POST',
            body: JSON.stringify({ nombre, email, password, rol })
        });
    }
};

// ============================================
// API DE BODEGAS
// ============================================

const bodegasAPI = {
    // Obtener todas las bodegas
    getAll: () => request('/bodegas'),
    
    // Obtener bodega por ID
    getById: (id) => request(`/bodegas/${id}`),
    
    // Obtener solo bodegas activas
    getActivas: () => request('/bodegas/activas'),
    
    // Crear nueva bodega
    create: (bodega) => request('/bodegas', {
        method: 'POST',
        body: JSON.stringify(bodega)
    }),
    
    // Actualizar bodega
    update: (id, bodega) => request(`/bodegas/${id}`, {
        method: 'PUT',
        body: JSON.stringify(bodega)
    }),
    
    // Eliminar bodega
    delete: (id) => request(`/bodegas/${id}`, {
        method: 'DELETE'
    })
};

// ============================================
// API DE PRODUCTOS
// ============================================

const productosAPI = {
    // Obtener todos los productos
    getAll: () => request('/productos'),
    
    // Obtener producto por ID
    getById: (id) => request(`/productos/${id}`),
    
    // Obtener solo productos activos
    getActivos: () => request('/productos/activos'),
    
    // Obtener productos con stock bajo
    getStockBajo: () => request('/productos/stock-bajo'),
    
    // Crear nuevo producto
    create: (producto) => request('/productos', {
        method: 'POST',
        body: JSON.stringify(producto)
    }),
    
    // Actualizar producto
    update: (id, producto) => request(`/productos/${id}`, {
        method: 'PUT',
        body: JSON.stringify(producto)
    }),
    
    // Eliminar producto
    delete: (id) => request(`/productos/${id}`, {
        method: 'DELETE'
    })
};

// ============================================
// API DE MOVIMIENTOS
// ============================================

const movimientosAPI = {
    // Obtener todos los movimientos
    getAll: () => request('/movimientos'),
    
    // Obtener movimiento por ID
    getById: (id) => request(`/movimientos/${id}`),
    
    // Obtener movimientos por tipo
    getByTipo: (tipo) => request(`/movimientos/tipo/${tipo}`),
    
    // Crear nuevo movimiento
    create: (movimiento) => request('/movimientos', {
        method: 'POST',
        body: JSON.stringify(movimiento)
    })
};

// ============================================
// API DE AUDITORÍAS
// ============================================

const auditoriasAPI = {
    // Obtener todas las auditorías
    getAll: () => request('/auditorias'),
    
    // Obtener auditorías por usuario
    getByUsuario: (usuarioId) => request(`/auditorias/usuario/${usuarioId}`),
    
    // Obtener auditorías por tipo de operación
    getByTipo: (tipo) => request(`/auditorias/tipo/${tipo}`)
};