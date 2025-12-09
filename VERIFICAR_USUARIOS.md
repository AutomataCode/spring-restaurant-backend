# Verificar y Crear Usuarios en Railway

## Problema
Los usuarios de prueba pueden no haberse insertado automáticamente porque la base de datos ya existía.

## Solución: Insertar Usuarios Manualmente

### Opción 1: Usar Railway MySQL Query (Recomendado)

1. **Ve a tu servicio MySQL en Railway**
2. **Haz clic en "Query" o "MySQL Console"**
3. **Ejecuta este SQL:**

```sql
-- Verificar si ya existen usuarios
SELECT * FROM usuarios;

-- Si no hay usuarios, insertar usuarios de prueba
INSERT IGNORE INTO usuarios (nombre, email, password, telefono, direccion, fecha_registro, activo, rol) VALUES
('Administrador', 'admin@restaurante.com', 'admin123', '999888777', 'Dirección del administrador', NOW(), TRUE, 'ADMIN'),
('Cliente Prueba', 'cliente@restaurante.com', 'cliente123', '999888666', 'Dirección del cliente', NOW(), TRUE, 'CLIENTE'),
('Juan Pérez', 'juan@example.com', 'juan123', '987654321', 'Av. Principal 123', NOW(), TRUE, 'CLIENTE');
```

### Opción 2: Cambiar SQL_INIT_MODE temporalmente

1. **Ve a tu servicio spring-restaurant-backend → Variables**
2. **Cambia `SQL_INIT_MODE` a `always`** (temporalmente)
3. **Reinicia el servicio**
4. **Después de que se ejecute, cambia de vuelta a `never`**

### Opción 3: Usar un cliente MySQL externo

Si tienes un cliente MySQL (como MySQL Workbench, DBeaver, etc.), puedes conectarte usando las credenciales de Railway y ejecutar el SQL.

## Credenciales de Prueba

Después de insertar los usuarios, puedes usar:

**Admin:**
- Email: `admin@restaurante.com`
- Password: `admin123`

**Cliente:**
- Email: `cliente@restaurante.com`
- Password: `cliente123`

## Verificar que Funcionó

1. Intenta hacer login con las credenciales
2. O ejecuta en Railway MySQL Query:
   ```sql
   SELECT email, nombre, rol FROM usuarios;
   ```

