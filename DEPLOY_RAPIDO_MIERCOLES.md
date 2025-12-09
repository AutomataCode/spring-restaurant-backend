# 🚀 Despliegue Rápido para el Miércoles - 100% GRATIS

## ⚡ OPCIÓN MÁS RÁPIDA: Vercel (5 minutos)

### Pasos:

1. **Ve a vercel.com** y crea cuenta con GitHub
2. **Click en "Add New Project"**
3. **Importa tu repositorio** de GitHub
4. **Configuración:**
   - Framework: Angular (se detecta automáticamente)
   - Root Directory: `FRONTEND`
   - Build Command: `npm run build`
   - Output Directory: `dist/angular-restaurant-frontend/browser`
5. **Click "Deploy"**

✅ **Listo en 2-3 minutos - 100% GRATIS**

---

## 💰 Sobre Railway

**Railway Plan Gratis:**
- $5 crédito gratis/mes
- El frontend consume MUY POCO (solo archivos estáticos)
- Probablemente te alcance para todo el mes
- Pero si quieres estar 100% seguro, usa Vercel

---

## 🎯 Comparación Rápida

| Plataforma | Costo | Tiempo Setup | Dificultad |
|------------|-------|--------------|------------|
| **Vercel** | 100% Gratis | 5 min | ⭐ Muy Fácil |
| **Netlify** | 100% Gratis | 5 min | ⭐ Muy Fácil |
| **Railway** | $5 crédito/mes | 10 min | ⭐⭐ Fácil |
| **GitHub Pages** | 100% Gratis | 15 min | ⭐⭐⭐ Medio |

---

## 📝 IMPORTANTE: Verificar URL del Backend

Antes de desplegar, asegúrate que en `FRONTEND/src/app/config/api.config.ts` esté la URL correcta de tu backend en Railway:

```typescript
export const API_CONFIG = {
  baseUrl: 'https://spring-restaurant-backend-production-1aa3.up.railway.app',
  apiUrl: 'https://spring-restaurant-backend-production-1aa3.up.railway.app/api'
};
```

---

## ✅ Checklist para el Miércoles

- [ ] Backend desplegado en Railway ✅ (ya lo tienes)
- [ ] Frontend desplegado en Vercel/Netlify
- [ ] URL del backend correcta en `api.config.ts`
- [ ] Probar registro de usuarios
- [ ] Probar login
- [ ] Probar hacer pedido
- [ ] Probar ver mis pedidos

---

**¿Quieres que te guíe paso a paso en Vercel ahora mismo?** Es lo más rápido y seguro para tu presentación.

