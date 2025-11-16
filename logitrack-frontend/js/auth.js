// ============================================
// LOGITRACK - AUTENTICACIÓN
// ============================================

document.addEventListener('DOMContentLoaded', () => {
    
    // Si ya está autenticado, redirigir al dashboard
    if (isAuthenticated()) {
        window.location.href = 'dashboard.html';
        return;
    }

    // Elementos del DOM
    const loginForm = document.getElementById('loginForm');
    const btnLogin = document.getElementById('btnLogin');
    const alertError = document.getElementById('alertError');
    const errorMessage = document.getElementById('errorMessage');

    // Manejar el submit del formulario de login
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Obtener valores del formulario
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;

        // Validaciones básicas
        if (!email || !password) {
            showError('Por favor complete todos los campos');
            return;
        }

        // Validar formato de email
        if (!isValidEmail(email)) {
            showError('Por favor ingrese un email válido');
            return;
        }

        // Deshabilitar botón y mostrar loading
        btnLogin.disabled = true;
        btnLogin.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Ingresando...';

        try {
            // Llamar a la API de login
            const response = await authAPI.login(email, password);

            console.log('Respuesta del servidor:', response);

            // Guardar token y usuario en localStorage
            saveAuth(response.token, {
                id: response.id,
                nombre: response.nombre,
                email: response.email,
                rol: response.rol
            });

            // Mostrar mensaje de éxito
            showSuccess('¡Bienvenido! Redirigiendo al dashboard...');

            // Redirigir al dashboard después de 1 segundo
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 1000);

        } catch (error) {
            console.error('Error en login:', error);
            showError(error.message || 'Email o contraseña incorrectos');
            
            // Re-habilitar botón
            btnLogin.disabled = false;
            btnLogin.innerHTML = '<i class="bi bi-box-arrow-in-right me-2"></i>Ingresar';
        }
    });

    // ============================================
    // FUNCIONES AUXILIARES
    // ============================================

    // Mostrar mensaje de error
    function showError(message) {
        errorMessage.textContent = message;
        alertError.classList.remove('d-none', 'alert-success');
        alertError.classList.add('alert-danger');
        alertError.style.display = 'block';
    }

    // Mostrar mensaje de éxito
    function showSuccess(message) {
        errorMessage.textContent = message;
        alertError.classList.remove('d-none', 'alert-danger');
        alertError.classList.add('alert-success');
        alertError.style.display = 'block';
    }

    // Validar formato de email
    function isValidEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

});