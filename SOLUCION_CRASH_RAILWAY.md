# 🔧 Solución para Crashes en Railway

## Problema
La aplicación crashea periódicamente y necesita reinicio manual.

## Causas Posibles

1. **Healthcheck fallando** - Railway reinicia el servicio si el healthcheck no responde
2. **Memoria insuficiente** - La aplicación se queda sin memoria
3. **Timeout muy corto** - El healthcheck no tiene suficiente tiempo para responder
4. **Conexión a BD perdida** - La conexión a MySQL se pierde

## Soluciones Aplicadas

### 1. ✅ Endpoint de Health Check Mejorado

He creado un endpoint `/api/health` que:
- Verifica la conexión a la base de datos
- Responde rápidamente
- Retorna estado 200 si todo está bien, 503 si hay problemas

### 2. ✅ Configuración de Railway Mejorada

- **healthcheckPath**: Cambiado a `/api/health` (más rápido y confiable)
- **healthcheckTimeout**: Aumentado a 300 segundos (5 minutos)
- **restartPolicyType**: Mantenido en `ON_FAILURE`

### 3. ✅ Optimización de Memoria Java

Agregadas opciones JVM:
- `-XX:+UseContainerSupport` - Mejor uso de memoria del contenedor
- `-XX:MaxRAMPercentage=75.0` - Usa hasta 75% de la RAM disponible

## Verificación

Después de hacer commit y push, verifica:

1. **El endpoint de health funciona:**
   ```
   https://spring-restaurant-backend-production-1aa3.up.railway.app/api/health
   ```
   
   Debería responder:
   ```json
   {
     "status": "UP",
     "database": "CONNECTED"
   }
   ```

2. **Revisa los logs en Railway:**
   - Ve a Deployments → Último deployment
   - Verifica que no haya errores de memoria
   - Verifica que el healthcheck esté pasando

## Configuración Adicional (Opcional)

Si el problema persiste, puedes:

### Aumentar Memoria en Railway

1. Ve a tu servicio → Settings
2. Busca "Resources" o "Scaling"
3. Aumenta la RAM si es posible

### Agregar Variable de Entorno

Puedes agregar en Railway:
- `JAVA_OPTS=-Xmx768m -Xms384m` (si tienes más RAM disponible)

### Monitorear Logs

Revisa los logs para ver:
- Errores de memoria (OutOfMemoryError)
- Errores de conexión a BD
- Timeouts

## Próximos Pasos

1. **Haz commit y push:**
   ```bash
   git add .
   git commit -m "Fix: Improve healthcheck and memory configuration"
   git push origin main
   ```

2. **Espera el despliegue** en Railway

3. **Prueba el healthcheck:**
   - Abre: `https://spring-restaurant-backend-production-1aa3.up.railway.app/api/health`
   - Debería responder con `{"status":"UP","database":"CONNECTED"}`

4. **Monitorea** durante las próximas horas para ver si el problema se resuelve

## Si el Problema Persiste

1. Revisa los logs en Railway para ver el error exacto
2. Verifica el uso de memoria en Railway Metrics
3. Considera aumentar los recursos del servicio
4. Revisa si hay queries lentas en la base de datos

