# 🔗 Variables de MySQL en Railway - Guía Actualizada

## ✅ Variables que Railway Proporciona Automáticamente

Railway proporciona estas variables en el servicio MySQL (sin guiones bajos):

- `MYSQLHOST` = `mysql.railway.internal`
- `MYSQLPORT` = `3306`
- `MYSQLDATABASE` = `railway`
- `MYSQLUSER` = `root`
- `MYSQLPASSWORD` = (contraseña generada)
- `MYSQL_URL` = URL completa de conexión
- `MYSQL_PUBLIC_URL` = URL pública (para conexiones externas)

## 🔧 Cómo Conectar las Variables a tu Aplicación

### Opción 1: Usar Variable Reference (Recomendado)

1. **Ve al servicio de tu aplicación** (spring-restaurant-backend)
2. **Ve a la pestaña "Variables"**
3. **Haz clic en "+ New Variable"**
4. **Para cada variable, usa "Add Reference":**
   - Haz clic en el icono de referencia (🔗) o selecciona "Reference"
   - Selecciona el servicio **MySQL**
   - Selecciona la variable que quieres referenciar

**Variables a referenciar:**
- `MYSQLHOST` → Referencia desde MySQL
- `MYSQLPORT` → Referencia desde MySQL
- `MYSQLDATABASE` → Referencia desde MySQL
- `MYSQLUSER` → Referencia desde MySQL
- `MYSQLPASSWORD` → Referencia desde MySQL

**O más fácil:**
- `MYSQL_URL` → Referencia desde MySQL (contiene toda la información)

### Opción 2: Usar MYSQL_URL Directamente

Si Railway proporciona `MYSQL_URL`, puedes usarla directamente. El `application.properties` ya está configurado para usarla.

Solo necesitas referenciar:
- `MYSQL_URL` desde el servicio MySQL

## 📝 Pasos Detallados

### Paso 1: En el servicio MySQL
1. Ve a **Variables**
2. Encuentra `MYSQL_URL` o las variables individuales
3. Anota los nombres exactos

### Paso 2: En el servicio de tu aplicación
1. Ve a **Variables**
2. Haz clic en **"+ New Variable"**
3. Para cada variable:
   - **Nombre:** `MYSQLHOST` (o el nombre que quieras usar)
   - **Valor:** Haz clic en "Reference" o el icono 🔗
   - Selecciona el servicio **MySQL**
   - Selecciona la variable correspondiente del servicio MySQL
   - Guarda

### Paso 3: Verificar
Después de agregar las referencias, deberías ver las variables en tu servicio de aplicación con un icono que indica que son referencias.

## ⚠️ Nota Importante

El `application.properties` ya está actualizado para usar las variables de Railway (con y sin guiones bajos para compatibilidad). Solo necesitas conectar las variables usando referencias.

## 🚀 Después de Conectar

1. Railway detectará los cambios automáticamente
2. Se iniciará un nuevo despliegue
3. La aplicación debería conectarse a MySQL correctamente

