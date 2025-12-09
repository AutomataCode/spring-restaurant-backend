# ⚡ Solución Rápida: Ejecutar SQL en Railway

## 🎯 Problema
Railway NO tiene editor SQL integrado en la pestaña "Database" → "Data".

## ✅ Solución: Usar MySQL Workbench

### Paso 1: Obtén las Credenciales

1. En Railway, en la pestaña **"Database"**:
   - Click en **"Connect"** (botón morado)
   - O ve a la pestaña **"Credentials"**
   - Copia estos datos:
     - **Host** (ejemplo: `metro.proxy.rlwy.net`)
     - **Port** (ejemplo: `42697`)
     - **User** (ejemplo: `root`)
     - **Password** (la contraseña)
     - **Database** (nombre de tu base de datos)

### Paso 2: Descarga MySQL Workbench

1. Ve a: https://dev.mysql.com/downloads/workbench/
2. Descarga la versión para Windows
3. Instálalo (es gratis)

### Paso 3: Conéctate

1. Abre MySQL Workbench
2. Click en el botón **"+"** (crear nueva conexión)
3. Configura:
   - **Connection Name**: `Railway MySQL`
   - **Hostname**: [pega el Host de Railway]
   - **Port**: [pega el Port de Railway]
   - **Username**: [pega el User de Railway]
   - **Password**: Click "Store in Keychain" y pega la contraseña
4. Click **"Test Connection"**
5. Si funciona, click **"OK"**

### Paso 4: Ejecuta el Script

1. En MySQL Workbench, haz doble click en la conexión "Railway MySQL"
2. Click en **"File"** → **"Open SQL Script"**
3. Selecciona el archivo `INSERTAR_PLATOS.sql`
4. El script se abrirá en el editor
5. Click en el botón **⚡ Execute** (o presiona `Ctrl+Shift+Enter`)
6. ¡Listo! Los 40 platos se insertarán

---

## 🔄 Alternativa: Usar la Terminal

Si prefieres usar la terminal:

```bash
# Instala MySQL client (si no lo tienes)
# Windows: Descarga desde mysql.com o usa Chocolatey: choco install mysql

# Conéctate (reemplaza con tus datos de Railway)
mysql -h metro.proxy.rlwy.net -P 42697 -u root -p[NOMBRE_DE_LA_BASE_DE_DATOS] < INSERTAR_PLATOS.sql
```

---

## 📝 Verificación

Después de ejecutar, verifica en MySQL Workbench:

```sql
SELECT COUNT(*) as total_platos FROM platos;
-- Debería mostrar 40 o más
```

---

## 💡 Tip

Si no quieres instalar MySQL Workbench, puedes usar:
- **DBeaver** (gratis, multiplataforma): https://dbeaver.io/
- **HeidiSQL** (Windows, gratis): https://www.heidisql.com/
- **TablePlus** (pago, pero tiene versión trial)

Todos funcionan igual: te conectas con las credenciales de Railway y ejecutas el script SQL.

