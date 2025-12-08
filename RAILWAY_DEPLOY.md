# Guía de Despliegue en Railway

Esta guía te ayudará a desplegar el backend de Spring Boot en Railway.

## Prerrequisitos

1. Cuenta en [Railway](https://railway.app)
2. Repositorio Git (GitHub, GitLab, etc.)

## Pasos para Desplegar

### 1. Conectar el Repositorio

1. Inicia sesión en Railway
2. Crea un nuevo proyecto
3. Selecciona "Deploy from GitHub repo" (o tu proveedor Git)
4. Conecta tu repositorio

### 2. Configurar el Servicio

Railway detectará automáticamente el `Dockerfile` en la carpeta `BACKEND/` gracias al archivo `railway.toml`.

### 3. Configurar Base de Datos MySQL

1. En tu proyecto de Railway, haz clic en "+ New"
2. Selecciona "Database" → "MySQL"
3. Railway creará automáticamente las variables de entorno:
   - `MYSQL_HOST`
   - `MYSQL_PORT`
   - `MYSQL_DATABASE`
   - `MYSQL_USER`
   - `MYSQL_PASSWORD`

### 4. Configurar Variables de Entorno

En la configuración de tu servicio de aplicación, agrega las siguientes variables de entorno:

#### Variables Requeridas (se configuran automáticamente con MySQL):
- `MYSQL_HOST` - Host de la base de datos (se configura automáticamente)
- `MYSQL_PORT` - Puerto de MySQL (se configura automáticamente)
- `MYSQL_DATABASE` - Nombre de la base de datos (se configura automáticamente)
- `MYSQL_USER` - Usuario de MySQL (se configura automáticamente)
- `MYSQL_PASSWORD` - Contraseña de MySQL (se configura automáticamente)

#### Variables Opcionales (con valores por defecto):
- `PORT` - Puerto del servidor (Railway lo asigna automáticamente, no es necesario configurarlo)
- `BASE_URL` - URL base de la aplicación (ej: `https://tu-app.railway.app`)
- `UPLOAD_DIR` - Directorio para subir archivos (por defecto: `uploads`)
- `LOG_LEVEL` - Nivel de logging (por defecto: `INFO`, opciones: `DEBUG`, `INFO`, `WARN`, `ERROR`)
- `JPA_DDL_AUTO` - Modo de DDL de Hibernate (por defecto: `update`, cambiar a `validate` en producción)
- `JPA_SHOW_SQL` - Mostrar SQL en logs (por defecto: `true`, cambiar a `false` en producción)
- `SQL_INIT_MODE` - Modo de inicialización SQL (por defecto: `always`, cambiar a `never` después de la primera ejecución)
- `THYMELEAF_CACHE` - Cache de Thymeleaf (por defecto: `false`, cambiar a `true` en producción)

#### Variables de Configuración de Yape:
- `YAPE_NUMERO` - Número de Yape (por defecto: `908556931`)
- `YAPE_CODIGO` - Código de Yape (por defecto: `908556931`)
- `YAPE_WHATSAPP` - WhatsApp de Yape (por defecto: `908556931`)
- `YAPE_QR_URL` - URL del QR de Yape

### 5. Configurar Variables de Entorno en Railway

1. Ve a tu servicio en Railway
2. Haz clic en la pestaña "Variables"
3. Agrega las variables necesarias
4. Si agregaste un servicio MySQL, Railway ya habrá configurado las variables de base de datos automáticamente

### 6. Desplegar

1. Railway detectará automáticamente los cambios en tu repositorio
2. El despliegue comenzará automáticamente cuando hagas push a la rama principal
3. Puedes ver el progreso en la pestaña "Deployments"

### 7. Obtener la URL de tu Aplicación

1. Una vez desplegado, Railway te proporcionará una URL pública
2. Esta URL estará en la pestaña "Settings" → "Domains"
3. Puedes configurar un dominio personalizado si lo deseas

## Configuración Recomendada para Producción

Para producción, se recomienda configurar:

```env
LOG_LEVEL=INFO
JPA_DDL_AUTO=validate
JPA_SHOW_SQL=false
SQL_INIT_MODE=never
THYMELEAF_CACHE=true
BASE_URL=https://tu-dominio.railway.app
```

## Solución de Problemas

### La aplicación no inicia
- Verifica que todas las variables de entorno estén configuradas correctamente
- Revisa los logs en Railway para ver errores específicos

### Error de conexión a la base de datos
- Asegúrate de que el servicio MySQL esté en el mismo proyecto de Railway
- Verifica que las variables de entorno de MySQL estén configuradas

### Puerto no disponible
- Railway asigna el puerto automáticamente a través de la variable `PORT`
- No necesitas configurar manualmente el puerto

## Notas Importantes

- Railway asigna el puerto dinámicamente, la aplicación está configurada para usar la variable `PORT`
- Los archivos subidos se almacenarán en el contenedor, considera usar un servicio de almacenamiento externo (S3, etc.) para producción
- El Dockerfile está optimizado para builds rápidos usando caché de capas de Maven

