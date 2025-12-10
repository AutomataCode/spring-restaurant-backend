# 🚀 Cómo Ejecutar el Script SQL en Railway

## 📍 Opción 1: Desde la Pestaña "Database" en Railway

1. **En Railway, en tu servicio MySQL:**
   - Click en la pestaña **"Database"** (no "Settings")
   - Busca un editor de SQL o botón "Query" / "Console"

2. **Si hay un editor:**
   - Abre el archivo `INSERTAR_PLATOS.sql`
   - Copia TODO el contenido
   - Pégalo en el editor
   - Click en "Execute" o "Run"

---

## 📍 Opción 2: Usar MySQL Workbench (Recomendado)

1. **Obtén las credenciales:**
   - En Railway, ve a la pestaña **"Variables"** de MySQL
   - Copia estos valores:
     - `MYSQLHOST` (o `MYSQL_HOST`)
     - `MYSQLPORT` (o `MYSQL_PORT`)
     - `MYSQLUSER` (o `MYSQL_USER`)
     - `MYSQLPASSWORD` (o `MYSQL_PASSWORD`)
     - `MYSQLDATABASE` (o `MYSQL_DATABASE`)

2. **Conéctate con MySQL Workbench:**
   - Abre MySQL Workbench
   - Click en "+" para crear nueva conexión
   - Configura:
     - **Connection Name**: Railway MySQL
     - **Hostname**: [valor de MYSQLHOST]
     - **Port**: [valor de MYSQLPORT]
     - **Username**: [valor de MYSQLUSER]
     - **Password**: Click "Store in Keychain" y pega [valor de MYSQLPASSWORD]
   - Click "Test Connection"
   - Si funciona, click "OK"

3. **Ejecuta el script:**
   - Abre la conexión
   - Click en "File" → "Open SQL Script"
   - Selecciona `INSERTAR_PLATOS.sql`
   - Click en el botón de ejecutar (⚡) o presiona `Ctrl+Shift+Enter`

---

## 📍 Opción 3: Desde la Terminal (si tienes MySQL instalado)

```bash
# Instala MySQL client si no lo tienes
# Windows: Descarga desde mysql.com
# Mac: brew install mysql-client
# Linux: sudo apt-get install mysql-client

# Conéctate (reemplaza con tus credenciales de Railway)
mysql -h [MYSQLHOST] -P [MYSQLPORT] -u [MYSQLUSER] -p[MYSQLPASSWORD] [MYSQLDATABASE] < INSERTAR_PLATOS.sql
```

---

## 📍 Opción 4: Usar un Cliente Web (phpMyAdmin, Adminer, etc.)

1. **Instala un cliente web** (si Railway lo permite)
2. **O usa un servicio online** como:
   - [Adminer](https://www.adminer.org/) (cliente web simple)
   - Conéctate con las credenciales de Railway
   - Ejecuta el script SQL

---

## ✅ Verificación

Después de ejecutar, verifica que funcionó:

```sql
SELECT COUNT(*) as total_platos FROM platos;
-- Debería mostrar 40 (o más si ya tenías platos)
```

---

## 🆘 Si no encuentras dónde ejecutar SQL

**Railway a veces no tiene editor SQL integrado.** En ese caso:

1. **Usa MySQL Workbench** (Opción 2) - Es la más confiable
2. **O usa la terminal** (Opción 3) - Si tienes MySQL instalado

---

## 💡 Tip Rápido

Si estás en Windows y no tienes MySQL Workbench:
1. Descárgalo desde: https://dev.mysql.com/downloads/workbench/
2. Es gratis y muy fácil de usar
3. Te permite ejecutar scripts SQL fácilmente

