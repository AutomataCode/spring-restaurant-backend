# ⚡ Guía Rápida: Desplegar en Vercel (3 minutos)

## 🚀 Pasos Rápidos

### 1. Ve a Vercel
👉 [vercel.com](https://vercel.com) → Sign Up con GitHub

### 2. Importa tu Proyecto
- Click "Add New..." → "Project"
- Selecciona tu repositorio

### 3. Configuración IMPORTANTE ⚠️

En la pantalla de configuración, cambia:

**Root Directory**: 
```
FRONTEND
```

**Build Command** (déjalo vacío o pon):
```
npm run build
```

**Output Directory**:
```
dist/angular-restaurant-frontend/browser
```

### 4. Deploy
Click "Deploy" y espera 2-3 minutos

### 5. ¡Listo! 🎉
Vercel te dará una URL como: `https://tu-proyecto.vercel.app`

---

## ✅ Verificación Rápida

1. Abre la URL que te dio Vercel
2. Prueba:
   - ✅ Ver el menú
   - ✅ Registrar un usuario
   - ✅ Hacer login
   - ✅ Hacer un pedido

---

## 🔧 Si algo falla

**Error de build:**
- Verifica que el Root Directory sea `FRONTEND`
- Revisa los logs en Vercel

**Error de API:**
- Verifica que la URL del backend en `FRONTEND/src/app/config/api.config.ts` sea correcta
- Debe ser: `https://spring-restaurant-backend-production-1aa3.up.railway.app`

---

## 💡 Tips

- ✅ Vercel es 100% GRATIS
- ✅ Cada push a GitHub despliega automáticamente
- ✅ HTTPS automático
- ✅ Muy rápido (CDN global)

**¡Perfecto para tu presentación del miércoles!** 🎯

