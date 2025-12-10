# 📝 Instrucciones para Insertar Platos en Railway MySQL

## 🎯 Objetivo
Insertar 40 platos peruanos en la base de datos MySQL de Railway.

## 📋 Pasos para Ejecutar el Script

### Opción 1: Desde el Panel de Railway (Recomendado)

1. **Accede a tu proyecto en Railway**
   - Ve a [railway.app](https://railway.app)
   - Selecciona tu proyecto del backend

2. **Abre MySQL**
   - En el panel de Railway, busca el servicio de MySQL
   - Click en "MySQL" o "Database"

3. **Abre la consola de MySQL**
   - Click en "Query" o "Console"
   - O usa "Connect" para obtener las credenciales

4. **Copia y pega el script**
   - Abre el archivo `INSERTAR_PLATOS.sql`
   - Copia TODO el contenido
   - Pégalo en la consola de MySQL
   - Click en "Execute" o "Run"

---

### Opción 2: Desde MySQL Workbench o cliente MySQL

1. **Obtén las credenciales de Railway**
   - En Railway, ve a tu servicio MySQL
   - Click en "Variables" o "Connect"
   - Copia:
     - `MYSQLHOST`
     - `MYSQLPORT`
     - `MYSQLUSER`
     - `MYSQLPASSWORD`
     - `MYSQLDATABASE`

2. **Conéctate con MySQL Workbench**
   - Abre MySQL Workbench
   - Crea una nueva conexión con los datos de Railway
   - Conéctate

3. **Ejecuta el script**
   - Abre el archivo `INSERTAR_PLATOS.sql`
   - Ejecuta el script completo

---

### Opción 3: Desde la terminal (si tienes acceso SSH)

```bash
# Conéctate a Railway MySQL
mysql -h [MYSQLHOST] -P [MYSQLPORT] -u [MYSQLUSER] -p[MYSQLPASSWORD] [MYSQLDATABASE] < INSERTAR_PLATOS.sql
```

---

## ⚠️ Importante

### Verificar IDs de Categorías

El script asume que las categorías tienen estos IDs:
- **1** = Entradas
- **2** = Platos Principales
- **3** = Postres
- **4** = Bebidas Frías
- **5** = Bebidas Calientes

**Si tus categorías tienen otros IDs, ajusta el script:**

```sql
-- Primero verifica los IDs de tus categorías:
SELECT id, nombre FROM categorias;

-- Luego ajusta los valores de categoria_id en el script
```

---

## ✅ Verificación

Después de ejecutar el script, verifica que los platos se insertaron:

```sql
-- Ver todos los platos
SELECT id, nombre, precio, categoria_id FROM platos;

-- Contar platos por categoría
SELECT c.nombre, COUNT(p.id) as total_platos 
FROM categorias c 
LEFT JOIN platos p ON c.id = p.categoria_id 
GROUP BY c.nombre;

-- Ver platos activos
SELECT nombre, precio FROM platos WHERE activo = TRUE;
```

---

## 📊 Resumen de Platos

El script inserta **40 platos** en total:

- **7 Entradas**: Ceviche, Anticuchos, Causa, Papa a la Huancaína, etc.
- **12 Platos Principales**: Lomo Saltado, Arroz con Pollo, Aji de Gallina, etc.
- **7 Postres**: Suspiro Limeño, Mazamorra Morada, Picarones, etc.
- **8 Bebidas Frías**: Chicha Morada, Maracuyá, Inca Kola, etc.
- **6 Bebidas Calientes**: Café, Té, Emoliente, Chocolate, etc.

---

## 🔧 Si hay Errores

### Error: "Foreign key constraint fails"
- **Causa**: Las categorías no existen o tienen IDs diferentes
- **Solución**: Verifica que las categorías existan con `SELECT * FROM categorias;`

### Error: "Duplicate entry"
- **Causa**: Los platos ya existen
- **Solución**: Usa `INSERT IGNORE` o elimina los platos existentes primero

### Error: "Column count doesn't match"
- **Causa**: La estructura de la tabla es diferente
- **Solución**: Verifica la estructura con `DESCRIBE platos;`

---

## 💡 Tips

- Los platos se insertan con `activo = TRUE` y `disponible_domicilio = TRUE`
- Los precios están en soles peruanos (PEN)
- Los tiempos de preparación están en minutos
- Las URLs de imágenes son relativas (ajústalas según tu estructura)

---

## 🎉 ¡Listo!

Una vez ejecutado el script, tus platos estarán disponibles en:
- El panel de administración
- El menú del frontend
- La API REST

