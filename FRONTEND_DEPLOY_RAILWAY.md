# Guía de Despliegue del Frontend en Railway

Esta guía te ayudará a desplegar el frontend Angular en Railway.

## Prerrequisitos

1. Cuenta en [Railway](https://railway.app)
2. Backend ya desplegado en Railway
3. Repositorio Git (GitHub, GitLab, etc.)

## Pasos para Desplegar

### 1. Configurar la URL del Backend

Antes de desplegar, asegúrate de que la URL del backend en `FRONTEND/src/app/config/api.config.ts` apunte a tu backend en Railway.

### 2. Crear un Nuevo Servicio en Railway

1. En tu proyecto de Railway, haz clic en "+ New"
2. Selecciona "GitHub Repo" (o tu proveedor Git)
3. Selecciona el mismo repositorio
4. Railway detectará automáticamente el Dockerfile en `FRONTEND/`

### 3. Configurar el Servicio

Railway debería detectar automáticamente el Dockerfile. Si no lo hace:

1. Ve a "Settings" → "Build & Deploy"
2. En "Root Directory", selecciona `FRONTEND`
3. En "Dockerfile Path", asegúrate de que esté en `Dockerfile`

### 4. Variables de Entorno (Opcional)

Si quieres usar variables de entorno para la URL del backend:

1. Ve a "Variables"
2. Agrega `VITE_API_URL` o `REACT_APP_API_URL` (según tu framework)
3. Para Angular, puedes modificar el build para usar variables de entorno

### 5. Desplegar

1. Railway detectará automáticamente los cambios en tu repositorio
2. El despliegue comenzará automáticamente cuando hagas push a la rama principal
3. Puedes ver el progreso en la pestaña "Deployments"

### 6. Obtener la URL de tu Frontend

1. Una vez desplegado, Railway te proporcionará una URL pública
2. Esta URL estará en la pestaña "Settings" → "Domains"
3. Puedes configurar un dominio personalizado si lo deseas

## Configuración Recomendada

- **Build Command**: `npm run build`
- **Output Directory**: `dist/angular-restaurant-frontend/browser`
- **Start Command**: Nginx se inicia automáticamente

## Notas Importantes

- El frontend se sirve como archivos estáticos usando Nginx
- Asegúrate de que la URL del backend en `api.config.ts` sea correcta
- El puerto 80 es el estándar para HTTP, Railway lo manejará automáticamente
- Los archivos estáticos se cachean para mejor rendimiento

## Solución de Problemas

### El build falla
- Verifica que todas las dependencias estén en `package.json`
- Revisa los logs en Railway para ver errores específicos

### La aplicación no carga
- Verifica que la URL del backend sea correcta
- Revisa la consola del navegador para errores de CORS
- Asegúrate de que el backend esté corriendo

### Error 404 en rutas
- Esto es normal en SPAs, Nginx está configurado para redirigir todas las rutas a `index.html`

