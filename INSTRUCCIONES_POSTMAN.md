# 📮 Cómo Usar el Endpoint en Postman

## 🎯 Endpoint para Insertar Platos

### URL del Endpoint
```
POST https://spring-restaurant-backend-production-1aa3.up.railway.app/api/admin/data/insertar-platos
```

✅ **Esta es tu URL correcta**

---

## 📋 Pasos en Postman

### 1. Abre Postman
- Abre la aplicación Postman (o descárgala si no la tienes)

### 2. Crea una Nueva Request
- Click en **"New"** → **"HTTP Request"**
- O presiona `Ctrl + N` (Windows) / `Cmd + N` (Mac)

### 3. Configura la Request

#### Método HTTP
- Selecciona **POST** en el dropdown (por defecto dice "GET")

#### URL
- Pega esta URL en el campo de URL:
  ```
  https://spring-restaurant-backend-production-1aa3.up.railway.app/api/admin/data/insertar-platos
  ```
  ✅ **Esta es tu URL correcta**

#### Headers (Opcional pero recomendado)
- Click en la pestaña **"Headers"**
- Agrega:
  - **Key:** `Content-Type`
  - **Value:** `application/json`

#### Body
- **NO necesitas Body** para este endpoint
- El endpoint no requiere parámetros

### 4. Envía la Request
- Click en el botón **"Send"** (azul, a la derecha)
- O presiona `Ctrl + Enter` (Windows) / `Cmd + Enter` (Mac)

### 5. Verifica la Respuesta

#### Respuesta Exitosa (200 OK)
```json
{
  "success": true,
  "platosInsertados": 40,
  "platosExistentes": 0,
  "mensaje": "Se insertaron 40 platos exitosamente. "
}
```

#### Si ya existen platos
```json
{
  "success": true,
  "platosInsertados": 0,
  "platosExistentes": 40,
  "mensaje": "Se insertaron 0 platos exitosamente. 40 platos ya existían."
}
```

#### Si hay error (categorías faltantes)
```json
{
  "error": "Faltan categorías. Asegúrate de que existan: Entradas, Platos Principales, Postres, Bebidas Frías, Bebidas Calientes"
}
```

---

## 🖼️ Captura de Pantalla de Configuración

```
┌─────────────────────────────────────────────────────────┐
│ POST  │  https://spring-restaurant-backend-production...│  [Send]
├─────────────────────────────────────────────────────────┤
│ Params │ Authorization │ Headers │ Body │ Pre-request │ Tests │
├─────────────────────────────────────────────────────────┤
│                                                          │
│  Headers (1)                                             │
│  ┌──────────────────┬──────────────────────────────┐   │
│  │ Content-Type     │ application/json             │   │
│  └──────────────────┴──────────────────────────────┘   │
│                                                          │
│  Body                                                    │
│  ○ none  ○ form-data  ○ x-www-form-urlencoded          │
│  ○ raw  ○ binary  ○ GraphQL                            │
│                                                          │
└─────────────────────────────────────────────────────────┘
```

---

## 🔧 Configuración Rápida

### Método: `POST`
### URL: `https://spring-restaurant-backend-production-1aa3.up.railway.app/api/admin/data/insertar-platos`
### Headers: `Content-Type: application/json`
### Body: `none` (no se necesita)

---

## ✅ Verificación

Después de ejecutar, verifica que funcionó:

1. **En Postman:** Deberías ver `"platosInsertados": 40` en la respuesta

2. **En tu Frontend:** Ve al menú y deberías ver los 40 platos

3. **O verifica con otro endpoint:**
   ```
   GET https://spring-restaurant-backend-production-1aa3.up.railway.app/api/platos
   ```
   Deberías ver una lista con 40 platos

---

## 🆘 Si hay Problemas

### Error 404 (Not Found)
- Verifica que la URL sea correcta
- Asegúrate de que el backend esté desplegado en Railway

### Error 500 (Internal Server Error)
- Verifica que las categorías existan en la base de datos
- Revisa los logs de Railway

### Error de CORS
- El endpoint tiene `@CrossOrigin(origins = "*")`, así que no debería haber problemas
- Si persiste, verifica la configuración de CORS en el backend

---

## 💡 Tip

Puedes guardar esta request en Postman:
- Click en **"Save"**
- Dale un nombre: "Insertar Platos"
- Guárdala en una colección para usarla después

