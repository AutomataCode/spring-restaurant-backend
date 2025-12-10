# 🍽️ Sistema de Restaurante - Gustitos

<div align="center">

![Pantalla Principal](imagenes%20para%20README/PANTALLA%20PRINCIPAL.png)

**Sistema completo de gestión de restaurante con backend Spring Boot y frontend moderno**

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Academic-blue.svg)](LICENSE)

</div>

---

## 📖 Sobre el Proyecto

Sistema web completo para la gestión de un restaurante que permite a los clientes explorar el menú, realizar pedidos y gestionar su experiencia gastronómica, mientras que los administradores pueden gestionar platos, categorías y pedidos de manera eficiente.

### ✨ Características Principales

- 🍕 **Catálogo de Platos**: Exploración intuitiva de menú con categorías (Comida/Bebida)
- 🛒 **Carrito de Compras**: Sistema de compra integrado con gestión de pedidos
- 👤 **Autenticación de Usuarios**: Sistema de registro e inicio de sesión seguro
- 📊 **Panel de Administración**: Gestión completa de platos, categorías y pedidos
- 💳 **Métodos de Pago**: Integración con diferentes formas de pago
- 📱 **Diseño Responsive**: Interfaz adaptada para todos los dispositivos

---

## 🎨 Vista Previa de la Aplicación

### 🏠 Pantalla Principal y Menú

<div align="center">

#### Menú Principal
![Menú 1](imagenes%20para%20README/SECCION%20MENU%20-%201.png)

#### Exploración de Platos
![Menú 2](imagenes%20para%20README/SECCION%20MENU%20-%202.png)

#### Platos Destacados
![Platos Destacados](imagenes%20para%20README/SECCION%20PLATOS%20DESTACADOS.png)

#### Catálogo de Platos
![Platos](imagenes%20para%20README/SECCION%20PLATOS%20.png)

#### Categorías
![Categorías](imagenes%20para%20README/SECCION%20CATEGORIAS.png)

</div>

---

### 🛒 Experiencia de Compra

<div align="center">

#### Carrito de Compras
![Carrito](imagenes%20para%20README/CARRITO%20DE%20COMPRAS.png)

#### Métodos de Pago e Información del Cliente
![Pago](imagenes%20para%20README/SECCION%20METODOS%20DE%20PAGO,%20INFORMACION%20DEL%20CLIENTE.png)

</div>

---

### 👤 Autenticación

<div align="center">

#### Iniciar Sesión
![Login](imagenes%20para%20README/SECCION%20INICIAR%20SESION.png)

#### Crear Usuario
![Registro](imagenes%20para%20README/SECCION%20CREAR%20USUARIO.png)

</div>

---

### ⚙️ Panel de Administración

<div align="center">

#### Dashboard de Administración
![Admin Dashboard](imagenes%20para%20README/SECCION%20ADMIN%20DASHBOARD.png)

#### Gestión de Pedidos (Parte 1)
![Gestión Pedidos 1](imagenes%20para%20README/SECCION%20GESITION%20PEDIDOS%201.png)

#### Gestión de Pedidos (Parte 2)
![Gestión Pedidos 2](imagenes%20para%20README/SECCION%20GESTION%20DE%20PEDIDOS%202.png)

#### Crear Plato
![Crear Plato](imagenes%20para%20README/SECCION%20CREAR%20PLATO.png)

#### Actualizar Plato
![Actualizar Plato](imagenes%20para%20README/SECCION%20ACTUALIZAR%20PLATO.png)

</div>

---

### 📄 Footer

<div align="center">

![Footer](imagenes%20para%20README/SECCION%20FOOTER%20.png)

</div>

---

## 📋 Características Técnicas

### Backend
- ✅ **API REST** separada en endpoints públicos y de administración
- ✅ **Gestión de Categorías** (COMIDA/BEBIDA) con activación/desactivación
- ✅ **Gestión de Platos** completa (CRUD) con validaciones
- ✅ **Validación de datos** robusta en todas las operaciones
- ✅ **Manejo global de excepciones** con respuestas estructuradas
- ✅ **Mapeo automático** con MapStruct para DTOs
- ✅ **Base de datos MySQL** con relaciones bien definidas
- ✅ **Soft Delete** para preservar integridad de datos históricos

## 🛠️ Stack Tecnológico

### Backend
- **Java 21** - Lenguaje de programación
- **Spring Boot 4.0.0-SNAPSHOT** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **MySQL 8** - Base de datos relacional
- **MapStruct 1.5.5** - Mapeo de objetos
- **Maven** - Gestión de dependencias

### Frontend
- **Angular** - Framework frontend
- **TypeScript** - Lenguaje de programación
- **Bootstrap/Angular Material** - Componentes UI
- **RxJS** - Programación reactiva

## 🚀 Instalación y Configuración

### Prerrequisitos

- JDK 21
- MySQL 8.0+
- Maven 3.6+

### Pasos para ejecutar

1. **Clonar el repositorio**
```bash
git clone <url-del-repositorio>
cd spring-restaurant-backend-pre
```

2. **Configurar la base de datos**

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurante_db
spring.datasource.username=root
spring.datasource.password=tu_password
```

3. **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 📡 Endpoints

### 🌍 Endpoints Públicos (Consulta)

#### Categorías
- `GET /api/categorias` - Listar todas las categorías activas
- `GET /api/categorias/{id}` - Obtener categoría por ID
- `GET /api/categorias/tipo/{tipo}` - Filtrar por tipo (COMIDA/BEBIDA)
- `GET /api/categorias/con-platos` - Categorías con platos activos

#### Platos
- `GET /api/platos` - Listar todos los platos activos
- `GET /api/platos/{id}` - Obtener plato por ID
- `GET /api/platos/categoria/{categoriaId}` - Platos por categoría
- `GET /api/platos/buscar?nombre={nombre}` - Buscar platos por nombre

### 🔐 Endpoints de Administración

#### Categorías
- `GET /api/admin/categorias` - Listar todas las categorías
- `POST /api/admin/categorias` - Crear categoría
- `PUT /api/admin/categorias/{id}` - Actualizar categoría
- `DELETE /api/admin/categorias/{id}` - Desactivar categoría
- `PATCH /api/admin/categorias/{id}/reactivar` - Reactivar categoría

#### Platos
- `GET /api/admin/platos` - Listar todos los platos
- `POST /api/admin/platos` - Crear plato
- `PUT /api/admin/platos/{id}` - Actualizar plato
- `DELETE /api/admin/platos/{id}` - Desactivar plato
- `PATCH /api/admin/platos/{id}/reactivar` - Reactivar plato

## 📝 Ejemplos de uso

### Crear una categoría
```bash
POST /api/admin/categorias
Content-Type: application/json

{
    "nombre": "Entradas",
    "descripcion": "Aperitivos y entradas",
    "tipo": "COMIDA",
    "activa": true
}
```

### Crear un plato
```bash
POST /api/admin/platos
Content-Type: application/json

{
    "nombre": "Ceviche",
    "descripcion": "Ceviche de pescado fresco",
    "precio": 35.50,
    "categoriaId": 1,
    "imagenUrl": "https://ejemplo.com/ceviche.jpg",
    "tiempoPreparacion": 15,
    "disponibleDomicilio": true
}
```

## 📊 Base de Datos

El proyecto incluye scripts SQL de inicialización:

- **schema.sql** - Crea las tablas
- **data.sql** - Inserta datos de prueba (5 categorías y 11 platos)

## 🏗️ Arquitectura

```
src/main/java/utp/edu/pe/restaurante/
├── controller/
│   ├── Admin/              # Endpoints de administración
│   └── Public/             # Endpoints públicos
├── service/
│   └── impl/               # Implementaciones de servicios
├── repository/             # Repositorios JPA
├── entity/                 # Entidades JPA
├── dto/                    # DTOs para transferencia de datos
│   └── request/            # DTOs para requests
├── mapper/                 # Mappers de MapStruct
└── exception/              # Manejo de excepciones
    └── error/              # Respuestas de error
```

## 🔧 Troubleshooting

### Error: "WebSocket connection failed"
**Solución:** Asegúrate de que el backend esté corriendo en `http://localhost:8080`. El frontend usa SockJS para conectarse al WebSocket.

### Error: "No se puede eliminar el plato"
**Causa:** El plato tiene pedidos asociados (restricción de base de datos)  
**Solución:** Usa "Desactivar" en lugar de "Eliminar permanentemente"

### Error al actualizar platos

#### "Ya existe un plato con el nombre: X" (cuando NO cambias el nombre)
**Solución:** ✅ **RESUELTO** - El backend ahora excluye correctamente el mismo plato de la validación de nombre único.

#### Otros errores comunes:
**Diagnóstico:**
1. Abre la consola del navegador (F12)
2. Verifica el mensaje de error específico
3. Causas comunes:
   - Intentando usar un nombre que otro plato ya tiene
   - Categoría inexistente o inactiva
   - Campos obligatorios vacíos

### Frontend no inicia (Error de Vite)

#### Error: "Failed to update Vite client error overlay text" o rutas con OneDrive
**Causa:** El proyecto fue movido de ubicación pero la caché de Angular/Vite aún tiene referencias a rutas antiguas.

**Solución (Windows PowerShell):**
```powershell
cd FRONTEND
npm cache clean --force
Remove-Item -Recurse -Force .angular -ErrorAction SilentlyContinue
Remove-Item -Recurse -Force node_modules\.vite -ErrorAction SilentlyContinue
Remove-Item -Recurse -Force dist -ErrorAction SilentlyContinue
npm start
```

**Solución (Linux/Mac):**
```bash
cd FRONTEND
npm cache clean --force
rm -rf .angular node_modules/.vite dist
npm start
```

**Nota:** Si moviste el proyecto desde OneDrive a otra ubicación, asegúrate de limpiar toda la caché antes de ejecutar nuevamente.

Ver `SOLUCIONES_APLICADAS.md` para más detalles.

## 👥 Equipo de Desarrollo

- Desarrollo: Grupo de Desarrollo Web Integrado - UTP

## 📄 Licencia

Este proyecto es parte de un proyecto académico de la Universidad Tecnológica del Perú.

