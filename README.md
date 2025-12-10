# Sistema de Restaurante - Gustitos

<div align="center">

![Pantalla Principal](imagenes%20para%20README/PANTALLA%20PRINCIPAL.png)

**Sistema completo de gestión de restaurante con backend Spring Boot y frontend moderno**

🌐 **Sitio Web en Vivo:** [https://restaurantegustitosweb.vercel.app/](https://restaurantegustitosweb.vercel.app/)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Academic-blue.svg)](LICENSE)

</div>

---

## Tabla de Contenidos

- [Sobre el Proyecto](#sobre-el-proyecto)
- [Vista Previa de la Aplicación](#vista-previa-de-la-aplicación)
  - [Pantalla Principal y Menú](#pantalla-principal-y-menú)
  - [Experiencia de Compra](#experiencia-de-compra)
  - [Autenticación](#autenticación)
  - [Panel de Administración](#panel-de-administración)
- [Características Técnicas](#características-técnicas)
- [Stack Tecnológico](#stack-tecnológico)
- [Instalación y Configuración](#instalación-y-configuración)
- [API Endpoints](#api-endpoints)
- [Ejemplos de Uso de la API](#ejemplos-de-uso-de-la-api)
- [Base de Datos](#base-de-datos)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Troubleshooting](#troubleshooting)
- [Contribuir](#contribuir)
- [Equipo de Desarrollo](#equipo-de-desarrollo)
- [Licencia](#licencia)

---

## Sobre el Proyecto

Este es un sistema web completo para la gestión de un restaurante. Permite a los clientes explorar el menú, realizar pedidos y gestionar su experiencia, mientras que los administradores pueden gestionar platos, categorías y pedidos de manera eficiente.

### Características Principales

- **Catálogo de Platos**: Los usuarios pueden explorar el menú de forma intuitiva, con categorías separadas para Comida y Bebida
- **Carrito de Compras**: Sistema de compra integrado con gestión completa de pedidos
- **Autenticación de Usuarios**: Sistema de registro e inicio de sesión para clientes
- **Panel de Administración**: Dashboard completo para que los administradores gestionen platos, categorías y pedidos
- **Métodos de Pago**: Integración con diferentes formas de pago
- **Diseño Responsive**: La interfaz se adapta a diferentes dispositivos

---

## Vista Previa de la Aplicación

### Pantalla Principal y Menú

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

### Experiencia de Compra

<div align="center">

#### Carrito de Compras
![Carrito](imagenes%20para%20README/CARRITO%20DE%20COMPRAS.png)

#### Métodos de Pago e Información del Cliente
![Pago](imagenes%20para%20README/SECCION%20METODOS%20DE%20PAGO,%20INFORMACION%20DEL%20CLIENTE.png)

</div>

---

### Autenticación

<div align="center">

#### Iniciar Sesión
![Login](imagenes%20para%20README/SECCION%20INICIAR%20SESION.png)

#### Crear Usuario
![Registro](imagenes%20para%20README/SECCION%20CREAR%20USUARIO.png)

</div>

---

### Panel de Administración

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

### Footer

<div align="center">

![Footer](imagenes%20para%20README/SECCION%20FOOTER%20.png)

</div>

---

## Características Técnicas

### Backend

| Característica | Descripción |
|----------------|-------------|
| **API REST** | Separación clara entre endpoints públicos y de administración |
| **Gestión de Categorías** | CRUD completo con soporte para tipos COMIDA/BEBIDA y activación/desactivación |
| **Gestión de Platos** | CRUD completo con validaciones robustas y búsqueda avanzada |
| **Validación de Datos** | Validaciones en todas las capas (DTO, Service, Entity) |
| **Manejo de Excepciones** | Manejo global centralizado con respuestas estructuradas |
| **Mapeo Automático** | MapStruct para conversión eficiente entre entidades y DTOs |
| **Base de Datos** | MySQL 8 con relaciones bien definidas y constraints |
| **Soft Delete** | Preservación de integridad de datos históricos |
| **CORS Configurado** | Soporte para comunicación con frontend en diferentes dominios |

### Frontend

| Característica | Descripción |
|----------------|-------------|
| **Interfaz Moderna** | Diseño responsive y atractivo con Angular Material |
| **Gestión de Estado** | Manejo eficiente del estado de la aplicación |
| **Autenticación** | Sistema de login y registro de usuarios |
| **Carrito de Compras** | Gestión completa del carrito con persistencia |
| **Panel de Administración** | Dashboard completo para gestión del restaurante |
| **Búsqueda y Filtros** | Búsqueda avanzada de platos y filtrado por categorías |

## Stack Tecnológico

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

## Instalación y Configuración

### Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- JDK 21 o superior
- MySQL 8.0+
- Maven 3.6+
- Node.js 18+ (para el frontend)
- npm o yarn (gestor de paquetes)

### Pasos para ejecutar

#### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd spring-restaurant-backend
```

#### 2. Configurar la base de datos

Crea la base de datos MySQL:

```sql
CREATE DATABASE restaurante_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

O ejecuta el script incluido:

```bash
mysql -u root -p < create_database.sql
```

#### 3. Configurar las propiedades de la aplicación

Edita `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/restaurante_db
spring.datasource.username=root
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

#### 4. Ejecutar el backend

**Opción A: Usando Maven Wrapper (Recomendado)**
```bash
./mvnw spring-boot:run
```

**Opción B: Usando Maven instalado**
```bash
mvn spring-boot:run
```

**Opción C: Compilar y ejecutar JAR**
```bash
mvn clean package
java -jar target/restaurante-backend-*.jar
```

#### 5. Ejecutar el frontend (Opcional)

```bash
cd FRONTEND
npm install
npm start
```

### Verificar la instalación

Una vez iniciado, la aplicación estará disponible en:

- **Backend API**: `http://localhost:8080`
- **Frontend**: `http://localhost:4200` (si ejecutaste el frontend)
- **API Docs**: `http://localhost:8080/api-docs` (si está configurado)

Puedes probar un endpoint público:

```bash
curl http://localhost:8080/api/categorias
```

## API Endpoints

### Endpoints Públicos (Consulta)

Estos endpoints están disponibles para todos los usuarios y no requieren autenticación.

#### Categorías

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/categorias` | Listar todas las categorías activas |
| `GET` | `/api/categorias/{id}` | Obtener categoría por ID |
| `GET` | `/api/categorias/tipo/{tipo}` | Filtrar por tipo (COMIDA/BEBIDA) |
| `GET` | `/api/categorias/con-platos` | Categorías con platos activos |

#### Platos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/platos` | Listar todos los platos activos |
| `GET` | `/api/platos/{id}` | Obtener plato por ID |
| `GET` | `/api/platos/categoria/{categoriaId}` | Platos por categoría |
| `GET` | `/api/platos/buscar?nombre={nombre}` | Buscar platos por nombre |

### Endpoints de Administración

Estos endpoints requieren permisos de administrador para gestionar el catálogo.

#### Categorías

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/admin/categorias` | Listar todas las categorías (incluye inactivas) |
| `POST` | `/api/admin/categorias` | Crear nueva categoría |
| `PUT` | `/api/admin/categorias/{id}` | Actualizar categoría existente |
| `DELETE` | `/api/admin/categorias/{id}` | Desactivar categoría (soft delete) |
| `PATCH` | `/api/admin/categorias/{id}/reactivar` | Reactivar categoría desactivada |

#### Platos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/admin/platos` | Listar todos los platos (incluye inactivos) |
| `POST` | `/api/admin/platos` | Crear nuevo plato |
| `PUT` | `/api/admin/platos/{id}` | Actualizar plato existente |
| `DELETE` | `/api/admin/platos/{id}` | Desactivar plato (soft delete) |
| `PATCH` | `/api/admin/platos/{id}/reactivar` | Reactivar plato desactivado |

## Ejemplos de Uso de la API

### Crear una Categoría

**Request:**
```http
POST /api/admin/categorias
Content-Type: application/json

{
    "nombre": "Entradas",
    "descripcion": "Aperitivos y entradas deliciosas",
    "tipo": "COMIDA",
    "activa": true
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "nombre": "Entradas",
    "descripcion": "Aperitivos y entradas deliciosas",
    "tipo": "COMIDA",
    "activa": true
}
```

### Crear un Plato

**Request:**
```http
POST /api/admin/platos
Content-Type: application/json

{
    "nombre": "Ceviche de Pescado",
    "descripcion": "Ceviche de pescado fresco con limón, cebolla y ají",
    "precio": 35.50,
    "categoriaId": 1,
    "imagenUrl": "https://ejemplo.com/ceviche.jpg",
    "tiempoPreparacion": 15,
    "disponibleDomicilio": true
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "nombre": "Ceviche de Pescado",
    "descripcion": "Ceviche de pescado fresco con limón, cebolla y ají",
    "precio": 35.50,
    "categoriaId": 1,
    "imagenUrl": "https://ejemplo.com/ceviche.jpg",
    "tiempoPreparacion": 15,
    "disponibleDomicilio": true,
    "activo": true
}
```

### Obtener Platos por Categoría

**Request:**
```http
GET /api/platos/categoria/1
```

**Response (200 OK):**
```json
[
    {
        "id": 1,
        "nombre": "Ceviche de Pescado",
        "descripcion": "Ceviche de pescado fresco...",
        "precio": 35.50,
        "categoriaId": 1,
        "imagenUrl": "https://ejemplo.com/ceviche.jpg",
        "tiempoPreparacion": 15,
        "disponibleDomicilio": true
    }
]
```

### Buscar Platos por Nombre

**Request:**
```http
GET /api/platos/buscar?nombre=ceviche
```

### Actualizar un Plato

**Request:**
```http
PUT /api/admin/platos/1
Content-Type: application/json

{
    "nombre": "Ceviche de Pescado Premium",
    "descripcion": "Ceviche de pescado fresco con ingredientes premium",
    "precio": 42.00,
    "categoriaId": 1,
    "imagenUrl": "https://ejemplo.com/ceviche-premium.jpg",
    "tiempoPreparacion": 20,
    "disponibleDomicilio": true
}
```

### Desactivar un Plato (Soft Delete)

**Request:**
```http
DELETE /api/admin/platos/1
```

**Response (200 OK):**
```json
{
    "mensaje": "Plato desactivado correctamente"
}
```

### Reactivar un Plato

**Request:**
```http
PATCH /api/admin/platos/1/reactivar
```

**Response (200 OK):**
```json
{
    "mensaje": "Plato reactivado correctamente"
}
```

## Base de Datos

### Estructura de la Base de Datos

El proyecto utiliza MySQL 8.0+ con las siguientes entidades principales:

- **Categorías**: Almacena las categorías de platos (COMIDA/BEBIDA)
- **Platos**: Catálogo completo de platos del restaurante
- **Usuarios**: Gestión de usuarios del sistema
- **Pedidos**: Registro de pedidos realizados
- **Detalles de Pedido**: Items individuales de cada pedido

### Scripts SQL Incluidos

El proyecto incluye varios scripts SQL para facilitar la configuración:

| Script | Descripción |
|--------|-------------|
| `create_database.sql` | Crea la base de datos |
| `schema.sql` | Crea todas las tablas necesarias |
| `data.sql` | Inserta datos de prueba (5 categorías y 11 platos) |
| `INSERTAR_PLATOS.sql` | Script adicional para insertar más platos |
| `limpiar_base_datos.sql` | Limpia los datos de prueba |
| `borrar_base_datos.sql` | Elimina la base de datos |

### Inicializar con Datos de Prueba

```bash
# Crear base de datos
mysql -u root -p < create_database.sql

# Crear tablas e insertar datos
mysql -u root -p restaurante_db < schema.sql
mysql -u root -p restaurante_db < data.sql
```

## Arquitectura del Proyecto

### Estructura del Backend

El proyecto sigue una arquitectura en capas (Layered Architecture) con separación clara de responsabilidades:

```
spring-restaurant-backend/
│
├── BACKEND/                                    # Código fuente del backend
│   └── src/main/java/utp/edu/pe/restaurante/
│       ├── controller/                         # Controladores REST
│       │   ├── Admin/                          # Endpoints de administración
│       │   │   ├── AdminCategoriaController.java
│       │   │   └── AdminPlatoController.java
│       │   └── Public/                         # Endpoints públicos
│       │       ├── PublicCategoriaController.java
│       │       └── PublicPlatoController.java
│       │
│       ├── service/                            # Lógica de negocio
│       │   ├── CategoriaService.java
│       │   ├── PlatoService.java
│       │   └── impl/                           # Implementaciones
│       │       ├── CategoriaServiceImpl.java
│       │       └── PlatoServiceImpl.java
│       │
│       ├── repository/                         # Acceso a datos (JPA)
│       │   ├── CategoriaRepository.java
│       │   └── PlatoRepository.java
│       │
│       ├── entity/                             # Entidades JPA
│       │   ├── Categoria.java
│       │   └── Plato.java
│       │
│       ├── dto/                                # Data Transfer Objects
│       │   ├── CategoriaDTO.java
│       │   ├── PlatoDTO.java
│       │   └── request/                        # DTOs para requests
│       │       ├── CategoriaRequestDTO.java
│       │       └── PlatoRequestDTO.java
│       │
│       ├── mapper/                             # Mappers MapStruct
│       │   ├── CategoriaMapper.java
│       │   └── PlatoMapper.java
│       │
│       └── exception/                          # Manejo de excepciones
│           ├── GlobalExceptionHandler.java
│           └── error/                          # Respuestas de error
│               └── ErrorResponse.java
│
├── FRONTEND/                                   # Código fuente del frontend
│
├── imagenes para README/                       # Imágenes para documentación
│
└── Scripts SQL/                                # Scripts de base de datos
    ├── create_database.sql
    ├── schema.sql
    ├── data.sql
    └── ...
```

### Patrones de Diseño Utilizados

- **DTO Pattern**: Separación entre entidades de dominio y objetos de transferencia
- **Repository Pattern**: Abstracción del acceso a datos
- **Service Layer Pattern**: Lógica de negocio encapsulada en servicios
- **Controller-Service-Repository**: Separación clara de responsabilidades
- **Exception Handling**: Manejo centralizado de excepciones

## Troubleshooting

### Problemas Comunes y Soluciones

#### Error: "WebSocket connection failed"

**Síntoma:** El frontend no puede conectarse al backend.

**Solución:**
- Verifica que el backend esté corriendo en `http://localhost:8080`
- Revisa la configuración de CORS en el backend
- El frontend usa SockJS para conectarse al WebSocket

---

#### Error: "No se puede eliminar el plato"

**Causa:** El plato tiene pedidos asociados (restricción de integridad referencial en la base de datos).

**Solución:** 
- Usa la opción "Desactivar" en lugar de "Eliminar permanentemente"
- El sistema implementa Soft Delete para preservar la integridad de los datos históricos

---

#### Error: "Ya existe un plato con el nombre: X" (al actualizar)

**Causa:** El sistema valida nombres únicos, pero puede fallar si no se excluye el mismo plato.

**Solución:** RESUELTO - El backend ahora excluye correctamente el mismo plato de la validación de nombre único.

**Si persiste el error:**
1. Abre la consola del navegador (F12)
2. Verifica el mensaje de error específico
3. Causas comunes:
   - Intentando usar un nombre que otro plato ya tiene
   - Categoría inexistente o inactiva
   - Campos obligatorios vacíos

---

#### Frontend no inicia (Error de Vite/Angular)

**Síntoma:** Error: "Failed to update Vite client error overlay text" o problemas con rutas.

**Causa:** El proyecto fue movido de ubicación pero la caché de Angular/Vite aún tiene referencias a rutas antiguas.

**Solución (Windows PowerShell):**
```powershell
cd FRONTEND
npm cache clean --force
Remove-Item -Recurse -Force .angular -ErrorAction SilentlyContinue
Remove-Item -Recurse -Force node_modules\.vite -ErrorAction SilentlyContinue
Remove-Item -Recurse -Force dist -ErrorAction SilentlyContinue
npm install
npm start
```

**Solución (Linux/Mac):**
```bash
cd FRONTEND
npm cache clean --force
rm -rf .angular node_modules/.vite dist
npm install
npm start
```

**Nota:** Si moviste el proyecto desde OneDrive a otra ubicación, asegúrate de limpiar toda la caché antes de ejecutar nuevamente.

---

#### Error de conexión a la base de datos

**Síntoma:** `java.sql.SQLException: Access denied` o `Connection refused`

**Solución:**
1. Verifica que MySQL esté corriendo:
   ```bash
   # Windows
   net start MySQL80
   
   # Linux/Mac
   sudo systemctl start mysql
   ```

2. Verifica las credenciales en `application.properties`
3. Asegúrate de que la base de datos exista:
   ```sql
   CREATE DATABASE restaurante_db;
   ```

---

#### Puerto 8080 ya está en uso

**Solución:**
- Cambia el puerto en `application.properties`:
  ```properties
  server.port=8081
  ```
- O termina el proceso que está usando el puerto:
  ```bash
  # Windows
  netstat -ano | findstr :8080
  taskkill /PID <PID> /F
  ```

---

Para más detalles, consulta `SOLUCIONES_APLICADAS.md` si está disponible en el repositorio.

## Contribuir

Este es un proyecto académico, pero las sugerencias y mejoras son bienvenidas. Si deseas contribuir:

1. Haz un Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Guía de Estilo de Código

- Sigue las convenciones de Java (camelCase para métodos y variables)
- Usa nombres descriptivos para clases, métodos y variables
- Documenta métodos complejos con JavaDoc
- Mantén las clases pequeñas y con responsabilidades únicas

## Recursos Adicionales

- [Documentación de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Angular](https://angular.io/docs)
- [Documentación de MySQL](https://dev.mysql.com/doc/)
- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)

## Enlaces Útiles

- [Configuración de Vercel](CONFIGURACION_VERCEL_EXACTA.md)
- [Conectar Servicios Railway](CONECTAR_SERVICIOS_RAILWAY.md)

## Equipo de Desarrollo

**Grupo de Desarrollo Web Integrado - UTP**

Desarrollado como parte del proyecto académico de la Universidad Tecnológica del Perú.

## Licencia

Este proyecto es parte de un proyecto académico de la Universidad Tecnológica del Perú (UTP).

---

<div align="center">

Si este proyecto te fue útil, considera darle una estrella.

Hecho por el Grupo de Desarrollo Web Integrado - UTP

</div>
