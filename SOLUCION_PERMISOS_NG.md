# 🔧 Solución: Error "Permission denied" en ng

## ❌ El Problema:
```
sh: line 1: /vercel/path0/FRONTEND/node_modules/.bin/ng: Permission denied
```

Esto ocurre porque Vercel está instalando dependencias en el directorio raíz en lugar de en FRONTEND.

## ✅ Solución:

### Opción 1: Usar npx (RECOMENDADO)

Cambia el **Build Command** a:
```
npx ng build --configuration production
```

Esto evita problemas de permisos porque `npx` maneja la ejecución del binario.

---

### Opción 2: Configurar Install Command correctamente

Si el **Root Directory** es `FRONTEND`, entonces:

- **Root Directory**: `FRONTEND`
- **Install Command**: `npm install` (debe estar activado)
- **Build Command**: `npm run build` o `npx ng build --configuration production`
- **Output Directory**: `dist/angular-restaurant-frontend/browser`

---

### Opción 3: Usar el comando completo con npx

**Build Command**:
```
npm install && npx ng build --configuration production
```

Y desactiva el **Install Command** (déjalo vacío).

---

## 🎯 Configuración Recomendada Final:

- **Root Directory**: `FRONTEND`
- **Install Command**: `npm install` (activado)
- **Build Command**: `npx ng build --configuration production`
- **Output Directory**: `dist/angular-restaurant-frontend/browser`

---

## ✅ Prueba esto primero:

Cambia el **Build Command** a:
```
npx ng build --configuration production
```

Esto debería resolver el problema de permisos.

