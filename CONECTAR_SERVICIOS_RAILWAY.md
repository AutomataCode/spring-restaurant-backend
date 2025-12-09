# 🔗 Cómo Conectar MySQL con tu Aplicación en Railway

## Problema
Tienes dos servicios separados:
- ✅ **MySQL** - Online (tiene las variables de base de datos)
- ❌ **spring-restaurant-backend** - Build failed (no tiene acceso a las variables de MySQL)

## Solución: Conectar los Servicios

### Opción 1: Usar Shared Variables (Recomendado)

1. **En el servicio MySQL:**
   - Haz clic en el servicio **MySQL**
   - Ve a la pestaña **"Variables"**
   - Verás las variables: `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DATABASE`, `MYSQL_USER`, `MYSQL_PASSWORD`
   - Haz clic en el botón **"Share"** o **"🔗"** al lado de cada variable
   - O usa el botón **"Share All"** si está disponible

2. **En el servicio de tu aplicación:**
   - Haz clic en el servicio **spring-restaurant-backend**
   - Ve a la pestaña **"Variables"**
   - Haz clic en **"Shared Variable"** (botón con icono de dos flechas en círculo)
   - Selecciona el servicio **MySQL**
   - Selecciona las variables que quieres compartir:
     - `MYSQL_HOST`
     - `MYSQL_PORT`
     - `MYSQL_DATABASE`
     - `MYSQL_USER`
     - `MYSQL_PASSWORD`
   - Haz clic en **"Add"**

### Opción 2: Agregar Variables Manualmente (Referencia)

Si la opción 1 no funciona, agrega las variables manualmente usando referencias:

1. **Obtén los valores de MySQL:**
   - Ve al servicio **MySQL** → **Variables**
   - Anota los valores de cada variable

2. **En el servicio de tu aplicación:**
   - Ve a **Variables** → **"+ New Variable"**
   - Agrega cada variable con su valor:

```
MYSQL_HOST = [valor del servicio MySQL]
MYSQL_PORT = [valor del servicio MySQL]
MYSQL_DATABASE = [valor del servicio MySQL]
MYSQL_USER = [valor del servicio MySQL]
MYSQL_PASSWORD = [valor del servicio MySQL]
```

### Opción 3: Usar DATABASE_URL (Alternativa)

Railway también puede proporcionar una variable `DATABASE_URL` en formato:
```
mysql://user:password@host:port/database
```

Si MySQL expone `DATABASE_URL`, puedes usarla directamente. Verifica en las variables del servicio MySQL.

## ✅ Verificación

Después de conectar los servicios:

1. Ve al servicio **spring-restaurant-backend** → **Variables**
2. Deberías ver las 5 variables de MySQL listadas
3. Haz un nuevo despliegue (Railway lo hará automáticamente cuando detecte cambios)

## 🔍 Si el Build sigue fallando

Revisa los logs del build:
1. Ve a **Deployments**
2. Haz clic en el deployment fallido
3. Revisa los logs para ver el error específico

Posibles problemas:
- Variables no conectadas correctamente
- Error en el Dockerfile
- Error de compilación de Maven

