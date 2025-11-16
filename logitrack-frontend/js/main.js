// ============================================
// LOGITRACK - FUNCIONES PRINCIPALES
// ============================================

// ============================================
// PROTECCIÓN DE RUTAS
// ============================================

// Verificar autenticación en páginas protegidas
function checkAuth() {
    if (!isAuthenticated()) {
        window.location.href = 'index.html';
        return false;
    }
    return true;
}

// Cerrar sesión
function logout() {
    if (confirm('¿Estás seguro que deseas cerrar sesión?')) {
        clearAuth();
        window.location.href = 'index.html';
    }
}

// ============================================
// FUNCIONES DE UI
// ============================================

// Mostrar alerta de éxito
function showSuccessAlert(message, containerId = 'alertContainer') {
    const container = document.getElementById(containerId);
    if (!container) return;

    const alert = document.createElement('div');
    alert.className = 'alert alert-success alert-dismissible fade show';
    alert.innerHTML = `
        <i class="bi bi-check-circle-fill me-2"></i>
        <strong>¡Éxito!</strong> ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    container.innerHTML = '';
    container.appendChild(alert);

    // Auto-cerrar después de 5 segundos
    setTimeout(() => {
        alert.remove();
    }, 5000);
}

// Mostrar alerta de error
function showErrorAlert(message, containerId = 'alertContainer') {
    const container = document.getElementById(containerId);
    if (!container) return;

    const alert = document.createElement('div');
    alert.className = 'alert alert-danger alert-dismissible fade show';
    alert.innerHTML = `
        <i class="bi bi-exclamation-triangle-fill me-2"></i>
        <strong>Error:</strong> ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    container.innerHTML = '';
    container.appendChild(alert);

    // Auto-cerrar después de 5 segundos
    setTimeout(() => {
        alert.remove();
    }, 5000);
}

// Mostrar alerta de advertencia
function showWarningAlert(message, containerId = 'alertContainer') {
    const container = document.getElementById(containerId);
    if (!container) return;

    const alert = document.createElement('div');
    alert.className = 'alert alert-warning alert-dismissible fade show';
    alert.innerHTML = `
        <i class="bi bi-exclamation-circle-fill me-2"></i>
        <strong>Advertencia:</strong> ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    container.innerHTML = '';
    container.appendChild(alert);

    // Auto-cerrar después de 5 segundos
    setTimeout(() => {
        alert.remove();
    }, 5000);
}

// ============================================
// FUNCIONES DE FORMATEO
// ============================================

// Formatear fecha
function formatDate(dateString) {
    if (!dateString) return '-';
    
    const date = new Date(dateString);
    const options = { 
        year: 'numeric', 
        month: '2-digit', 
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    };
    
    return date.toLocaleDateString('es-CO', options);
}

// Formatear fecha corta (solo fecha)
function formatDateShort(dateString) {
    if (!dateString) return '-';
    
    const date = new Date(dateString);
    const options = { 
        year: 'numeric', 
        month: '2-digit', 
        day: '2-digit'
    };
    
    return date.toLocaleDateString('es-CO', options);
}

// Formatear moneda (pesos colombianos)
function formatCurrency(amount) {
    if (amount === null || amount === undefined) return '$0';
    
    return new Intl.NumberFormat('es-CO', {
        style: 'currency',
        currency: 'COP',
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
    }).format(amount);
}

// Formatear número con separadores de miles
function formatNumber(number) {
    if (number === null || number === undefined) return '0';
    
    return new Intl.NumberFormat('es-CO').format(number);
}

// ============================================
// FUNCIONES DE USUARIO
// ============================================

// Obtener nombre del usuario actual
function getCurrentUserName() {
    const user = getUser();
    return user ? user.nombre : 'Usuario';
}

// Obtener email del usuario actual
function getCurrentUserEmail() {
    const user = getUser();
    return user ? user.email : '';
}

// Obtener rol del usuario actual
function getCurrentUserRole() {
    const user = getUser();
    return user ? user.rol : '';
}

// Verificar si el usuario es admin
function isAdmin() {
    const user = getUser();
    return user && user.rol === 'ADMIN';
}

// ============================================
// FUNCIONES DE NAVEGACIÓN
// ============================================

// Actualizar navbar con información del usuario
function updateNavbar() {
    const userNameElement = document.getElementById('userName');
    const userEmailElement = document.getElementById('userEmail');
    
    if (userNameElement) {
        userNameElement.textContent = getCurrentUserName();
    }
    
    if (userEmailElement) {
        userEmailElement.textContent = getCurrentUserEmail();
    }
}

// Marcar página activa en el navbar
function setActiveNavLink() {
    const currentPage = window.location.pathname.split('/').pop();
    const navLinks = document.querySelectorAll('.nav-link');
    
    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href === currentPage) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
}

// ============================================
// FUNCIONES DE VALIDACIÓN
// ============================================

// Validar email
function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Validar número positivo
function validatePositiveNumber(value) {
    const num = parseFloat(value);
    return !isNaN(num) && num > 0;
}

// Validar campo requerido
function validateRequired(value) {
    return value !== null && value !== undefined && value.trim() !== '';
}

// ============================================
// FUNCIONES DE LOADING
// ============================================

// Mostrar loading en un botón
function showButtonLoading(buttonId, loadingText = 'Cargando...') {
    const button = document.getElementById(buttonId);
    if (!button) return;
    
    button.disabled = true;
    button.dataset.originalText = button.innerHTML;
    button.innerHTML = `<span class="spinner-border spinner-border-sm me-2"></span>${loadingText}`;
}

// Ocultar loading de un botón
function hideButtonLoading(buttonId) {
    const button = document.getElementById(buttonId);
    if (!button) return;
    
    button.disabled = false;
    if (button.dataset.originalText) {
        button.innerHTML = button.dataset.originalText;
    }
}

// Mostrar loading en un contenedor
function showLoading(containerId) {
    const container = document.getElementById(containerId);
    if (!container) return;
    
    container.innerHTML = `
        <div class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-3 text-muted">Cargando datos...</p>
        </div>
    `;
}

// Mostrar mensaje de "sin datos"
function showNoData(containerId, message = 'No hay datos para mostrar') {
    const container = document.getElementById(containerId);
    if (!container) return;
    
    container.innerHTML = `
        <div class="text-center py-5">
            <i class="bi bi-inbox display-1 text-muted"></i>
            <p class="mt-3 text-muted">${message}</p>
        </div>
    `;
}

// ============================================
// FUNCIONES DE CONFIRMACIÓN
// ============================================

// Confirmar eliminación
function confirmDelete(itemName) {
    return confirm(`¿Estás seguro que deseas eliminar "${itemName}"?\n\nEsta acción no se puede deshacer.`);
}

// Confirmar acción
function confirmAction(message) {
    return confirm(message);
}

// ============================================
// UTILIDADES
// ============================================

// Capitalizar primera letra
function capitalize(string) {
    if (!string) return '';
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}

// Truncar texto
function truncateText(text, maxLength) {
    if (!text || text.length <= maxLength) return text;
    return text.substring(0, maxLength) + '...';
}

// Limpiar formulario
function clearForm(formId) {
    const form = document.getElementById(formId);
    if (form) {
        form.reset();
    }
}

// ============================================
// INICIALIZACIÓN
// ============================================

// Ejecutar al cargar cualquier página protegida
document.addEventListener('DOMContentLoaded', () => {
    // Solo en páginas que NO son index.html
    if (!window.location.pathname.includes('index.html')) {
        // Verificar autenticación
        checkAuth();
        
        // Actualizar navbar si existe
        updateNavbar();
        
        // Marcar página activa
        setActiveNavLink();
    }
});