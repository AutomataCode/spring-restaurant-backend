# 🍽️ API REST - Sistema de Restaurante

Backend desarrollado con Spring Boot para la gestión de un sistema de restaurante.

## 📋 Características

- ✅ Gestión de Categorías (COMIDA/BEBIDA)
- ✅ Gestión de Platos
- ✅ API REST separada en endpoints públicos y de administración
- ✅ Validación de datos
- ✅ Manejo global de excepciones
- ✅ Mapeo automático con MapStruct
- ✅ Base de datos MySQL

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 4.0.0-SNAPSHOT**
- **Spring Data JPA**
- **MySQL 8**
- **MapStruct 1.5.5**
- **Maven**

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
**Solución:**
```bash
cd FRONTEND
npm cache clean --force
rm -rf .angular node_modules/.vite
npm start
```

Ver `SOLUCIONES_APLICADAS.md` para más detalles.

## 👥 Equipo de Desarrollo

- Desarrollo: Grupo de Desarrollo Web Integrado - UTP

## 📄 Licencia

Este proyecto es parte de un proyecto académico de la Universidad Tecnológica del Perú.

