# 🚀 Guía Paso a Paso: Desplegar en Vercel (5 minutos)

## ✅ Paso 1: Preparar el Repositorio

1. Asegúrate de que todos tus cambios estén en GitHub:
   ```bash
   git add .
   git commit -m "Preparar para despliegue en Vercel"
   git push
   ```

## ✅ Paso 2: Crear Cuenta en Vercel

1. Ve a [vercel.com](https://vercel.com)
2. Click en **"Sign Up"**
3. Selecciona **"Continue with GitHub"**
4. Autoriza a Vercel a acceder a tu repositorio

## ✅ Paso 3: Importar Proyecto

1. En el dashboard de Vercel, click en **"Add New..."** → **"Project"**
2. Selecciona tu repositorio de GitHub
3. Si no aparece, click en **"Adjust GitHub App Permissions"** y autoriza el repositorio

## ✅ Paso 4: Configurar el Proyecto

En la pantalla de configuración:

### Configuración Básica:
- **Framework Preset**: Angular (se detecta automáticamente)
- **Root Directory**: `FRONTEND` ⚠️ **IMPORTANTE**
- **Build Command**: `npm run build` (o déjalo en blanco, Vercel lo detecta)
- **Output Directory**: `dist/angular-restaurant-frontend/browser` ⚠️ **IMPORTANTE**

### Variables de Entorno (Opcional):
No necesitas configurar nada aquí por ahora, ya que la URL del backend está hardcodeada en `api.config.ts`

## ✅ Paso 5: Desplegar

1. Click en **"Deploy"**
2. Espera 2-3 minutos mientras Vercel:
   - Instala dependencias
   - Construye la aplicación
   - Despliega

## ✅ Paso 6: Verificar

1. Una vez terminado, Vercel te dará una URL como:
   `https://tu-proyecto.vercel.app`
2. Abre la URL en tu navegador
3. Verifica que todo funcione:
   - ✅ La página carga
   - ✅ Puedes hacer login
   - ✅ Puedes registrar usuarios
   - ✅ Puedes ver el menú

## 🔧 Si hay Problemas

### Error: "Output Directory not found"
- Verifica que el Output Directory sea: `dist/angular-restaurant-frontend/browser`
- O prueba con: `FRONTEND/dist/angular-restaurant-frontend/browser`

### Error: "Build failed"
- Revisa los logs en Vercel
- Asegúrate de que `package.json` tenga el script `build`
- Verifica que todas las dependencias estén en `package.json`

### La app carga pero da errores de API
- Verifica que la URL del backend en `api.config.ts` sea correcta
- Asegúrate de que el backend esté corriendo en Railway

## 📝 Notas Importantes

- ✅ Vercel es **100% GRATIS** para proyectos personales
- ✅ Despliegue automático en cada push a la rama principal
- ✅ HTTPS automático
- ✅ Puedes agregar un dominio personalizado gratis
- ✅ El archivo `vercel.json` ya está configurado en tu proyecto

## 🎉 ¡Listo!

Tu frontend estará disponible en una URL de Vercel y podrás usarlo para tu presentación del miércoles.

