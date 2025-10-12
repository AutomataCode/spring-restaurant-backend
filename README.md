# 🍽️ API REST - Sistema de Restaurante

Backend desarrollado con Spring Boot para la gestión de un sistema de restaurante.

## 📋 Características

- ✅ Gestión de Categorías (COMIDA/BEBIDA)
- ✅ Gestión de Platos
- ✅ **Sistema completo de Pedidos con Detalles**
- ✅ **Gestión de Usuarios (ADMIN/CLIENTE)**
- ✅ **Cálculo automático de totales**
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

### Configuración de IDE (Opcional pero recomendado)

Si usas **Cursor** o **VS Code**:
- Al abrir el proyecto, te sugerirá instalar las extensiones recomendadas (definidas en `.vscode/extensions.json`)
- Acepta la sugerencia o instala manualmente: [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- El proyecto incluye configuración automática en `.vscode/settings.json` para compilación automática de Maven

Si usas **IntelliJ IDEA** o **Eclipse**: No necesitas instalar nada adicional, ya tienen soporte Maven integrado.

### Pasos para ejecutar

1. **Clonar el repositorio**
```bash
git clone <url-del-repositorio>
cd spring-restaurant-backend
```

2. **Compilar el proyecto** ⚠️ **IMPORTANTE**
```bash
mvn clean install
```
> **Nota**: Este paso es necesario para que MapStruct genere las implementaciones de los mappers. Si usas un IDE como IntelliJ IDEA, Eclipse, VS Code o Cursor con las extensiones de Java, esto se hará automáticamente al importar el proyecto Maven.

3. **Configurar la base de datos**

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurante_db
spring.datasource.username=root
spring.datasource.password=tu_password
```

4. **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```
O desde tu IDE ejecuta la clase `AppRestauranteApiApplication`

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

#### Pedidos (Cliente)
- `POST /api/pedidos` - Crear nuevo pedido
- `GET /api/pedidos/usuario/{usuarioId}` - Pedidos de un usuario
- `GET /api/pedidos/{id}` - Obtener pedido por ID

#### Detalles de Pedido
- `GET /api/detalle-pedidos/pedido/{pedidoId}` - Detalles de un pedido

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

#### Pedidos (Administración)
- `GET /api/admin/pedidos` - Listar todos los pedidos
- `POST /api/admin/pedidos` - Crear pedido (admin)
- `GET /api/admin/pedidos/{id}` - Obtener pedido por ID
- `PUT /api/admin/pedidos/{id}` - Actualizar pedido
- `DELETE /api/admin/pedidos/{id}` - Cancelar pedido

#### Usuarios
- `GET /api/admin/usuarios` - Listar todos los usuarios
- `POST /api/admin/usuarios` - Crear usuario
- `GET /api/admin/usuarios/{id}` - Obtener usuario por ID
- `PUT /api/admin/usuarios/{id}` - Actualizar usuario
- `DELETE /api/admin/usuarios/{id}` - Desactivar usuario

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

### Crear un pedido completo
```bash
POST /api/admin/pedidos
Content-Type: application/json

{
    "usuarioId": 1,
    "tipoPedido": "DOMICILIO",
    "metodoPago": "EFECTIVO",
    "direccionEntrega": "Av. Arequipa 1234, Miraflores",
    "telefonoContacto": "987654321",
    "instruccionesEntrega": "Casa azul, timbre 3",
    "cambioPara": 100.00,
    "detalles": [
        {
            "platoId": 1,
            "cantidad": 2,
            "notas": "Sin cebolla"
        },
        {
            "platoId": 2,
            "cantidad": 1,
            "notas": "Término medio"
        },
        {
            "platoId": 3,
            "cantidad": 3,
            "notas": "Extra queso"
        }
    ]
}
```

**Respuesta esperada:**
```json
{
    "id": 22,
    "total": 125.00,
    "estado": "PENDIENTE",
    "detalles": [
        {
            "id": 58,
            "cantidad": 2,
            "precioUnitario": 18.50,
            "subtotal": 37.00,
            "platoNombre": "Ceviche de Pescado",
            "notas": "Sin cebolla"
        },
        {
            "id": 59,
            "cantidad": 1,
            "precioUnitario": 16.00,
            "subtotal": 16.00,
            "platoNombre": "Anticuchos",
            "notas": "Término medio"
        },
        {
            "id": 60,
            "cantidad": 3,
            "precioUnitario": 24.00,
            "subtotal": 72.00,
            "platoNombre": "Lomo Saltado",
            "notas": "Extra queso"
        }
    ],
    "fechaPedido": "2025-10-11T22:23:19.5836856",
    "direccionEntrega": "Av. Arequipa 1234, Miraflores",
    "telefonoContacto": "987654321",
    "instruccionesEntrega": "Casa azul, timbre 3",
    "metodoPago": "EFECTIVO",
    "tipoPedido": "DOMICILIO",
    "cambioPara": 100.00,
    "usuarioId": 1
}
```

### Crear un usuario
```bash
POST /api/admin/usuarios
Content-Type: application/json

{
    "nombre": "Juan Pérez",
    "email": "juan@email.com",
    "telefono": "987654321",
    "direccion": "Av. Principal 123",
    "password": "password123",
    "rol": "CLIENTE"
}
```

## 📊 Base de Datos

El proyecto incluye scripts SQL de inicialización:

- **schema.sql** - Crea las tablas (categorías, platos, usuarios, pedidos, detalle_pedidos)
- **data.sql** - Inserta datos de prueba:
  - 5 categorías (COMIDA/BEBIDA)
  - 11 platos con precios
  - 1 usuario administrador
  - 1 usuario cliente

### Estructura de Tablas

- **categorias**: ID, nombre, descripción, tipo, activa
- **platos**: ID, nombre, descripción, precio, categoría, imagen, tiempo preparación
- **usuarios**: ID, nombre, email, teléfono, dirección, password, rol, activo
- **pedidos**: ID, usuario, tipo, método pago, dirección, teléfono, total, estado, fechas
- **detalle_pedidos**: ID, pedido, plato, cantidad, precio unitario, subtotal, notas

## 🏗️ Arquitectura

```
src/main/java/utp/edu/pe/restaurante/
├── controller/
│   ├── admin/              # Endpoints de administración
│   └── Cliente/            # Endpoints para clientes
├── service/
│   └── impl/               # Implementaciones de servicios
├── repository/             # Repositorios JPA
├── entity/                 # Entidades JPA (Categoria, Plato, Usuario, Pedido, DetallePedido)
├── dto/                    # DTOs para transferencia de datos
│   └── request/            # DTOs para requests (CreatePedidoRequest, etc.)
├── mapper/                 # Mappers de MapStruct
└── exception/              # Manejo de excepciones
    └── error/              # Respuestas de error
```

### Flujo de Pedidos

1. **Cliente** envía request con `usuarioId`, `detalles[]` y datos del pedido
2. **Sistema** valida usuario y platos
3. **Sistema** crea pedido con estado `PENDIENTE`
4. **Sistema** crea detalles con precios unitarios y subtotales
5. **Sistema** calcula total automáticamente
6. **Sistema** retorna pedido completo con detalles y total

## 🔧 Solución de Problemas

### Error: "No qualifying bean of type 'CategoriaMapper' available"

**Causa**: MapStruct no ha generado las implementaciones de los mappers.

**Solución**: Ejecuta `mvn clean compile` o `mvn clean install`. Esto generará automáticamente las clases `CategoriaMapperImpl` y `PlatoMapperImpl` en `target/generated-sources/annotations/`.

> **Por qué sucede**: Los mappers generados por MapStruct se crean en tiempo de compilación y se ubican en la carpeta `target/`, que NO está versionada en Git (y no debería estarlo). Por eso, después de clonar el repositorio, es necesario compilar el proyecto.

### Error: "Conflicting bean definition" o problemas con controladores

**Causa**: Clases compiladas antiguas en `target/` causando conflictos.

**Solución**: Ejecuta `mvn clean compile` para eliminar archivos antiguos y recompilar.

> **Nota**: Usamos `Cliente` en lugar de `public` para los controladores públicos porque `public` es una palabra reservada en Java y causa errores de compilación.

### Error de conexión a MySQL

Verifica que:
- MySQL esté corriendo en `localhost:3306`
- Las credenciales en `application.properties` sean correctas
- La base de datos `restaurante_db` exista (o se creará automáticamente si `createDatabaseIfNotExist=true`)

### Error: "A collection with orphan deletion was no longer referenced"

**Causa**: Problema con colecciones JPA al modificar relaciones.

**Solución**: Usar `clear()` y `addAll()` en lugar de `setDetalles()`:
```java
// ❌ INCORRECTO
pedido.setDetalles(detalles);

// ✅ CORRECTO
pedido.getDetalles().clear();
pedido.getDetalles().addAll(detalles);
```

### Error: "Column 'precio_unitario' cannot be null"

**Causa**: El cliente no envía el precio unitario, debe calcularse automáticamente.

**Solución**: El sistema debe obtener el precio del plato y calcularlo:
```java
detalle.setPrecioUnitario(plato.getPrecio());
detalle.setSubtotal(plato.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
```

### Error: "detalles" vacío y "total" = 0 en respuesta

**Causa**: Problemas de carga lazy o contexto de persistencia.

**Solución**: 
1. Usar `flush()` antes de recargar
2. Cargar detalles manualmente con `findByPedidoId()`
3. Calcular total manualmente después de cargar detalles

## 🧪 Testing con Postman

### Colección de Postman

Puedes importar esta colección en Postman para probar todos los endpoints:

```json
{
  "info": {
    "name": "Restaurante API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Crear Pedido",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"usuarioId\": 1,\n  \"tipoPedido\": \"DOMICILIO\",\n  \"metodoPago\": \"EFECTIVO\",\n  \"direccionEntrega\": \"Av. Arequipa 1234, Miraflores\",\n  \"telefonoContacto\": \"987654321\",\n  \"instruccionesEntrega\": \"Casa azul, timbre 3\",\n  \"cambioPara\": 100.00,\n  \"detalles\": [\n    {\n      \"platoId\": 1,\n      \"cantidad\": 2,\n      \"notas\": \"Sin cebolla\"\n    },\n    {\n      \"platoId\": 2,\n      \"cantidad\": 1,\n      \"notas\": \"Término medio\"\n    }\n  ]\n}"
        },
        "url": {
          "raw": "http://localhost:8080/api/admin/pedidos",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "admin", "pedidos"]
        }
      }
    }
  ]
}
```

### Scripts de Testing

**Crear pedido de prueba:**
```bash
curl -X POST http://localhost:8080/api/admin/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "tipoPedido": "DOMICILIO",
    "metodoPago": "EFECTIVO",
    "direccionEntrega": "Av. Arequipa 1234, Miraflores",
    "telefonoContacto": "987654321",
    "instruccionesEntrega": "Casa azul, timbre 3",
    "cambioPara": 100.00,
    "detalles": [
      {
        "platoId": 1,
        "cantidad": 2,
        "notas": "Sin cebolla"
      }
    ]
  }'
```

## 🎯 Funcionalidades Implementadas

### ✅ Sistema de Pedidos Completo
- **Creación de pedidos** con múltiples detalles
- **Cálculo automático** de totales y subtotales
- **Validación** de usuarios y platos
- **Estados de pedido** (PENDIENTE, CONFIRMADO, etc.)
- **Tipos de pedido** (DOMICILIO, RECOGIDA)
- **Métodos de pago** (EFECTIVO, TARJETA)

### ✅ Gestión de Usuarios
- **Roles** (ADMIN, CLIENTE)
- **Datos completos** (nombre, email, teléfono, dirección)
- **Autenticación** básica

### ✅ Sistema de Productos
- **Categorías** (COMIDA, BEBIDA)
- **Platos** con precios y descripciones
- **Gestión de disponibilidad**

## 🚀 Próximas Mejoras

- [ ] Autenticación JWT
- [ ] Paginación en listados
- [ ] Filtros avanzados
- [ ] Notificaciones por email
- [ ] Dashboard de administración
- [ ] Reportes de ventas

## 👥 Equipo de Desarrollo

- Desarrollo: Grupo de Desarrollo Web Integrado - UTP

## 📄 Licencia

Este proyecto es parte de un proyecto académico de la Universidad Tecnológica del Perú.

