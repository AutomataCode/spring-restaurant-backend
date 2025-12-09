# 🔧 Solución: Error "cd: FRONTEND: No such file or directory"

## ❌ El Problema:
Vercel está intentando ejecutar `cd FRONTEND` pero no encuentra el directorio.

## ✅ Solución:

### Si el Root Directory está configurado como `FRONTEND`:

**NO uses `cd FRONTEND` en el Build Command**, porque Vercel ya está dentro de ese directorio.

**Configuración correcta:**
- **Root Directory**: `FRONTEND`
- **Build Command**: `npm run build` (SIN `cd FRONTEND`)
- **Output Directory**: `dist/angular-restaurant-frontend/browser` (SIN `FRONTEND/`)
- **Install Command**: `npm install` (o déjalo vacío)

---

### Si el Root Directory NO está configurado (está vacío):

Entonces SÍ necesitas el `cd FRONTEND`:

**Configuración:**
- **Root Directory**: (vacío o `.`)
- **Build Command**: `cd FRONTEND && npm install && npm run build`
- **Output Directory**: `FRONTEND/dist/angular-restaurant-frontend/browser`
- **Install Command**: (vacío, ya está en el build command)

---

## 🎯 Recomendación:

**Usa la primera opción** (Root Directory = `FRONTEND`), es más limpia y es la forma estándar de Vercel.

**Configuración final recomendada:**
- **Root Directory**: `FRONTEND`
- **Build Command**: `npm run build`
- **Output Directory**: `dist/angular-restaurant-frontend/browser`
- **Install Command**: `npm install`

---

## ✅ Verificación:

Después de configurar, haz click en "Deploy" y debería funcionar.

