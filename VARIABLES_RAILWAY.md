# Variables de Entorno para Railway

## ✅ Variables que Railway ya configuró automáticamente (NO necesitas agregarlas)

Estas 6 variables ya están configuradas por Railway:
- `MYSQL_HOST` - Host de la base de datos
- `MYSQL_PORT` - Puerto de MySQL
- `MYSQL_DATABASE` - Nombre de la base de datos
- `MYSQL_USER` - Usuario de MySQL
- `MYSQL_PASSWORD` - Contraseña de MySQL
- `PORT` - Puerto del servidor (asignado automáticamente)

## 🔧 Variables que SÍ debes agregar manualmente

Haz clic en **"+ New Variable"** y agrega estas variables:

### 1. BASE_URL (IMPORTANTE - Requerida)
```
Nombre: BASE_URL
Valor: https://tu-app.railway.app
```
**Nota:** Reemplaza `tu-app.railway.app` con la URL real que Railway te asignó. La encontrarás en Settings → Domains después del primer despliegue.

### 2. Variables de Producción (Recomendadas)

```
Nombre: LOG_LEVEL
Valor: INFO
```

```
Nombre: JPA_DDL_AUTO
Valor: update
```
**Nota:** Cambia a `validate` después de la primera ejecución exitosa.

```
Nombre: JPA_SHOW_SQL
Valor: false
```

```
Nombre: SQL_INIT_MODE
Valor: always
```
**Nota:** Cambia a `never` después de la primera ejecución exitosa.

```
Nombre: THYMELEAF_CACHE
Valor: true
```

### 3. Variables de Yape (Opcionales - solo si quieres personalizarlas)

Si quieres cambiar los valores por defecto de Yape:

```
Nombre: YAPE_NUMERO
Valor: 908556931
```

```
Nombre: YAPE_CODIGO
Valor: 908556931
```

```
Nombre: YAPE_WHATSAPP
Valor: 908556931
```

```
Nombre: YAPE_QR_URL
Valor: https://tu-app.railway.app/uploads/yape/yape.jpeg
```

## 📝 Resumen Rápido

**Mínimo necesario para funcionar:**
- ✅ Las 6 variables de MySQL ya están (Railway las agregó)
- ✅ `PORT` ya está (Railway lo asigna)
- ⚠️ **Solo necesitas agregar:** `BASE_URL` (después del primer despliegue)

**Recomendado para producción:**
- `BASE_URL`
- `LOG_LEVEL=INFO`
- `JPA_SHOW_SQL=false`
- `THYMELEAF_CACHE=true`

## 🚀 Pasos para agregar variables

1. Haz clic en **"+ New Variable"**
2. Ingresa el **Nombre** de la variable
3. Ingresa el **Valor** de la variable
4. Haz clic en **"Add"**
5. La aplicación se reiniciará automáticamente con las nuevas variables

