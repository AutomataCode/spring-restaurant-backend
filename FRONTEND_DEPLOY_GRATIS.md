# Opciones GRATUITAS para Desplegar el Frontend

## 🎯 Opción 1: Vercel (RECOMENDADO - 100% Gratis)

Vercel es perfecto para Angular y es completamente gratis.

### Pasos:
1. Ve a [vercel.com](https://vercel.com) y crea una cuenta (puedes usar GitHub)
2. Haz clic en "Add New Project"
3. Conecta tu repositorio de GitHub
4. Configuración:
   - **Framework Preset**: Angular
   - **Root Directory**: `FRONTEND`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist/angular-restaurant-frontend/browser`
5. Haz clic en "Deploy"

**Ventajas:**
- ✅ 100% Gratis
- ✅ Despliegue automático en cada push
- ✅ HTTPS automático
- ✅ Dominio personalizado gratis
- ✅ Muy rápido

---

## 🎯 Opción 2: Netlify (100% Gratis)

### Pasos:
1. Ve a [netlify.com](https://netlify.com) y crea una cuenta
2. Haz clic en "Add new site" → "Import an existing project"
3. Conecta tu repositorio de GitHub
4. Configuración:
   - **Base directory**: `FRONTEND`
   - **Build command**: `npm run build`
   - **Publish directory**: `FRONTEND/dist/angular-restaurant-frontend/browser`
5. Haz clic en "Deploy site"

**Ventajas:**
- ✅ 100% Gratis
- ✅ Despliegue automático
- ✅ HTTPS automático
- ✅ Muy fácil de usar

---

## 🎯 Opción 3: GitHub Pages (100% Gratis)

### Pasos:
1. En tu repositorio de GitHub, ve a Settings → Pages
2. Source: GitHub Actions
3. Crea el workflow (ver abajo)

**Ventajas:**
- ✅ 100% Gratis
- ✅ Integrado con GitHub
- ⚠️ Requiere configuración adicional

---

## 🎯 Opción 4: Railway (Plan Gratis - $5 crédito/mes)

Si prefieres Railway (mismo lugar que el backend):

### Pasos:
1. En Railway, crea un nuevo servicio
2. Selecciona tu repositorio
3. Configura:
   - **Root Directory**: `FRONTEND`
   - **Build Command**: `npm run build`
   - **Start Command**: `npx serve -s dist/angular-restaurant-frontend/browser -l 80`

**Costo:**
- Plan Hobby: $5 crédito gratis/mes
- El frontend consume muy poco (solo hosting estático)
- Probablemente te alcance para todo el mes

---

## ⚡ RECOMENDACIÓN RÁPIDA PARA EL MIÉRCOLES

**Usa Vercel** - Es lo más rápido y fácil:
1. Toma 5 minutos configurarlo
2. Despliegue automático
3. URL lista en 2 minutos
4. 100% gratis para siempre

¿Quieres que te ayude a configurarlo en Vercel ahora mismo?

