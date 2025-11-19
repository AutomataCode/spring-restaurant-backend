# 📋 Instrucciones de Configuración y Ejecución

## ✅ Problemas Corregidos

### 1. **Frontend Angular - Archivos Faltantes**
- ✅ Creado `package.json` con todas las dependencias necesarias
- ✅ Creado `angular.json` para configuración del proyecto
- ✅ Creado `tsconfig.json` y `tsconfig.app.json` para TypeScript
- ✅ Creado `src/main.ts` como punto de entrada de la aplicación

### 2. **Backend - Controlador de Autenticación**
- ✅ Creado `AuthController` con endpoint `POST /api/auth/login`
- ✅ Creado `AuthService` e `AuthServiceImpl` para manejar la autenticación
- ✅ Creados DTOs `LoginRequest` y `LoginResponse`

### 3. **Backend - Endpoint de Pedidos para Cliente**
- ✅ Agregado endpoint `POST /api/cliente/pedidos` en `PedidoController`
- ✅ Actualizado el controlador para usar `/api/cliente` como base
- ✅ Actualizado `PedidoService` en el frontend para usar las rutas correctas

### 4. **Configuración Docker**
- ⚠️ Nota: El archivo `.env` debe crearse manualmente (está en .gitignore)
- Crea un archivo `.env` en la raíz del proyecto con:
  ```
  MYSQL_ROOT_PASSWORD=1234
  MYSQL_DATABASE=restaurante_db
  MYSQL_USER=root
  MYSQL_PASSWORD=1234
  ```

## 🚀 Cómo Ejecutar el Proyecto

### Backend (Spring Boot)

1. **Configurar la base de datos MySQL:**
   - Opción 1: Usar Docker Compose
     ```bash
     docker-compose up -d
     ```
   - Opción 2: Instalar MySQL localmente y configurar en `application.properties`

2. **Ejecutar el backend:**
   ```bash
   cd spring-restaurant-backend
   ./mvnw spring-boot:run
   ```
   O en Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

   El backend estará disponible en: `http://localhost:8080`

### Frontend (Angular)

1. **Instalar dependencias:**
   ```bash
   cd Angular-restaurant-frontend
   npm install
   ```

2. **Ejecutar el servidor de desarrollo:**
   ```bash
   npm start
   ```
   O directamente:
   ```bash
   ng serve
   ```

   El frontend estará disponible en: `http://localhost:4200`

## 🔗 Endpoints Principales

### Autenticación
- `POST /api/auth/login` - Iniciar sesión

### Platos (Público)
- `GET /api/platos` - Listar todos los platos activos
- `GET /api/platos/{id}` - Obtener plato por ID
- `GET /api/platos/categoria/{categoriaId}` - Platos por categoría

### Categorías (Público)
- `GET /api/categorias` - Listar todas las categorías activas
- `GET /api/categorias/con-platos` - Categorías con platos

### Pedidos (Cliente)
- `POST /api/cliente/pedidos` - Crear un nuevo pedido
- `GET /api/cliente/pedidos/{id}` - Obtener pedido por ID
- `GET /api/cliente/pedidos/usuario/{usuarioId}` - Pedidos de un usuario

### Administración
- `GET /api/admin/platos` - Gestión de platos
- `GET /api/admin/categorias` - Gestión de categorías
- `GET /api/admin/pedidos` - Gestión de pedidos

## ⚠️ Notas Importantes

1. **CORS**: Todos los controladores tienen `@CrossOrigin(origins = "*")` configurado para desarrollo. En producción, deberías limitar esto a dominios específicos.

2. **Autenticación**: La autenticación actual es básica (comparación de contraseñas en texto plano). Para producción, se recomienda usar BCrypt para hash de contraseñas y JWT para tokens de autenticación.

3. **Base de Datos**: Asegúrate de que MySQL esté corriendo y que las credenciales en `application.properties` coincidan con tu configuración.

4. **Puertos**: 
   - Backend: `8080`
   - Frontend: `4200`
   - MySQL: `3306`

## 🐛 Solución de Problemas

### Error: "No se puede conectar al backend"
- Verifica que el backend esté corriendo en el puerto 8080
- Verifica que no haya errores en la consola del backend
- Revisa la configuración de CORS

### Error: "No se puede conectar a la base de datos"
- Verifica que MySQL esté corriendo
- Verifica las credenciales en `application.properties`
- Asegúrate de que la base de datos `restaurante_db` exista o que `createDatabaseIfNotExist=true` esté configurado

### Error al instalar dependencias de Angular
- Asegúrate de tener Node.js instalado (versión 18 o superior)
- Intenta eliminar `node_modules` y `package-lock.json` y ejecutar `npm install` nuevamente

