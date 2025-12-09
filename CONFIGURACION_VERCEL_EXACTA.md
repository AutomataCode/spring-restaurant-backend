# ⚙️ Configuración Exacta para Vercel

## 📋 Configuración que debes poner:

### 1. Nombre Del Proyecto
```
restaurante-gustitos-frontend
```
(O cualquier nombre único que prefieras)

### 2. Root Directory
```
FRONTEND
```
✅ Ya está correcto

### 3. Build Command
```
npm run build
```
(Activa el toggle si está desactivado)

### 4. Output Directory ⚠️ IMPORTANTE
```
dist/angular-restaurant-frontend/browser
```
**Cambia esto** - NO dejes `público si existe, o .`

### 5. Install Command
```
npm install
```
(Activa el toggle si está desactivado)

### 6. Framework Preset
Puedes dejarlo en "Other" o cambiarlo a "Angular" si aparece

---

## ✅ Después de configurar:

1. Click en **"Deploy"**
2. Espera 2-3 minutos
3. ¡Listo! 🎉

---

## 🔧 Si el Output Directory no funciona:

Si después del build te da error, prueba con:
```
FRONTEND/dist/angular-restaurant-frontend/browser
```

