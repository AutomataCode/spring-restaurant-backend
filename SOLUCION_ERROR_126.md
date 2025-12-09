# 🔧 Solución Error 126 en Vercel

## ❌ El Error:
```
Error: Command "npm run build" exited with 126
```

## ✅ Solución:

### Opción 1: Verificar Root Directory (RECOMENDADO)

En la configuración de Vercel, asegúrate de que:

1. **Root Directory** esté configurado como: `FRONTEND`
2. **Build Command** sea: `npm run build` (sin `cd FRONTEND`)
3. **Output Directory** sea: `dist/angular-restaurant-frontend/browser`

Si el Root Directory está bien, Vercel automáticamente ejecuta los comandos desde ahí.

---

### Opción 2: Si el Root Directory no funciona

Si sigue fallando, prueba cambiar el **Build Command** a:

```
cd FRONTEND && npm install && npm run build
```

Y el **Output Directory** a:
```
FRONTEND/dist/angular-restaurant-frontend/browser
```

---

### Opción 3: Usar el comando completo

Cambia el **Build Command** a:
```
npm install && npm run build
```

---

## 🔍 Verificar en los Logs

Revisa los logs de Vercel para ver:
- ¿Desde qué directorio se está ejecutando?
- ¿Hay algún error de permisos?
- ¿Se están instalando las dependencias correctamente?

---

## ✅ Configuración Recomendada Final:

- **Root Directory**: `FRONTEND`
- **Build Command**: `npm run build`
- **Output Directory**: `dist/angular-restaurant-frontend/browser`
- **Install Command**: `npm install` (debe estar activado)

---

## 💡 Nota:

El error 126 generalmente significa que:
- El comando no se puede ejecutar (permisos)
- El directorio de trabajo es incorrecto
- Faltan dependencias

**Prueba primero la Opción 1, y si no funciona, usa la Opción 2.**

