# 📸 Guía de Manejo de Imágenes

## ✅ **Recomendación: Almacenamiento Local**

He implementado un sistema **híbrido** que permite tanto **subir archivos localmente** como usar **URLs externas**. Esto te da la máxima flexibilidad.

---

## 🎯 **Ventajas del Almacenamiento Local**

### ✅ **Pros:**
- ✅ **Control total** sobre las imágenes
- ✅ **No depende** de servicios externos
- ✅ **Mejor rendimiento** (servidas desde el mismo servidor)
- ✅ **Privacidad y seguridad** (tus imágenes no están en internet)
- ✅ **Sin costos adicionales** de almacenamiento en la nube
- ✅ **Fácil de migrar** a servicios en la nube después (AWS S3, Cloudinary, etc.)

### ⚠️ **Contras:**
- ⚠️ Ocupa espacio en el servidor
- ⚠️ Necesitas hacer backups de la carpeta `uploads/`
- ⚠️ En producción, considera usar un CDN o servicio en la nube

---

## 🚀 **Cómo Funciona**

### **1. Estructura de Carpetas**
```
spring-restaurant-backend/
└── uploads/
    ├── platos/
    │   ├── uuid1.jpg
    │   ├── uuid2.png
    │   └── ...
    └── categorias/
        ├── uuid3.jpg
        └── ...
```

### **2. Endpoints Disponibles**

#### **Subir Imagen de Plato**
```http
POST /api/files/upload/plato
Content-Type: multipart/form-data

file: [archivo de imagen]
```

**Respuesta:**
```json
{
  "url": "/uploads/platos/uuid.jpg",
  "message": "Imagen subida exitosamente"
}
```

#### **Subir Imagen de Categoría**
```http
POST /api/files/upload/categoria
Content-Type: multipart/form-data

file: [archivo de imagen]
```

#### **Crear Plato con Imagen (Endpoint Híbrido)**
```http
POST /api/admin/platos/con-imagen
Content-Type: multipart/form-data

nombre: "Ceviche"
descripcion: "Ceviche de pescado"
precio: 25.00
categoriaId: 1
imagen: [archivo]  // Opcional: sube archivo
imagenUrl: "https://..."  // Opcional: usa URL externa
tiempoPreparacion: 20
disponibleDomicilio: true
```

#### **Crear Plato con URL Externa (Método Tradicional)**
```http
POST /api/admin/platos
Content-Type: application/json

{
  "nombre": "Ceviche",
  "descripcion": "Ceviche de pescado",
  "precio": 25.00,
  "categoriaId": 1,
  "imagenUrl": "https://ejemplo.com/imagen.jpg"
}
```

### **3. Acceder a las Imágenes**

Las imágenes subidas localmente se sirven automáticamente en:
```
http://localhost:8080/uploads/platos/uuid.jpg
http://localhost:8080/uploads/categorias/uuid.png
```

---

## 📝 **Ejemplo de Uso en Angular**

### **Subir Imagen y Crear Plato**

```typescript
// En tu servicio Angular
uploadPlatoImage(file: File): Observable<any> {
  const formData = new FormData();
  formData.append('file', file);
  
  return this.http.post('http://localhost:8080/api/files/upload/plato', formData);
}

// En tu componente
onFileSelected(event: any) {
  const file = event.target.files[0];
  if (file) {
    this.adminService.uploadPlatoImage(file).subscribe({
      next: (response) => {
        // response.url contiene "/uploads/platos/uuid.jpg"
        this.platoForm.patchValue({ imagenUrl: response.url });
      },
      error: (error) => {
        console.error('Error al subir imagen:', error);
      }
    });
  }
}
```

### **Crear Plato con Imagen en un Solo Paso**

```typescript
createPlatoWithImage(platoData: any, imagen: File) {
  const formData = new FormData();
  formData.append('nombre', platoData.nombre);
  formData.append('descripcion', platoData.descripcion);
  formData.append('precio', platoData.precio.toString());
  formData.append('categoriaId', platoData.categoriaId.toString());
  formData.append('imagen', imagen);
  formData.append('tiempoPreparacion', platoData.tiempoPreparacion?.toString() || '');
  formData.append('disponibleDomicilio', platoData.disponibleDomicilio?.toString() || 'true');
  
  return this.http.post('http://localhost:8080/api/admin/platos/con-imagen', formData);
}
```

---

## ⚙️ **Configuración**

### **application.properties**
```properties
# Directorio de almacenamiento
app.upload.dir=uploads

# URL base del servidor
app.base.url=http://localhost:8080

# Tamaño máximo de archivos (10MB)
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### **Formatos Soportados**
- ✅ JPEG/JPG
- ✅ PNG
- ✅ GIF
- ✅ WebP

---

## 🔄 **Migración a Producción**

Cuando vayas a producción, considera:

1. **AWS S3** - Almacenamiento en la nube de Amazon
2. **Cloudinary** - Servicio especializado en imágenes
3. **Google Cloud Storage** - Almacenamiento de Google
4. **Azure Blob Storage** - Almacenamiento de Microsoft

Para migrar, solo necesitas cambiar la implementación de `FileStorageServiceImpl` para que guarde en el servicio en la nube en lugar del sistema de archivos local.

---

## 🎨 **Recomendación Final**

**Para desarrollo y proyectos pequeños/medianos:**
- ✅ **Usa almacenamiento local** (lo que acabamos de implementar)

**Para producción y proyectos grandes:**
- ✅ **Usa un servicio en la nube** (AWS S3, Cloudinary, etc.) para mejor escalabilidad y rendimiento global

---

## 📌 **Notas Importantes**

1. La carpeta `uploads/` está en `.gitignore`, así que **no se subirá al repositorio**
2. Las imágenes se guardan con nombres únicos (UUID) para evitar conflictos
3. El sistema valida que los archivos sean imágenes antes de guardarlos
4. Puedes usar **ambos métodos** (archivos locales y URLs) en el mismo proyecto

---

## 🐛 **Solución de Problemas**

### **Error: "El archivo debe ser una imagen"**
- Verifica que el archivo sea realmente una imagen (JPG, PNG, GIF, WebP)

### **Error: "El archivo es demasiado grande"**
- Aumenta el límite en `application.properties`: `spring.servlet.multipart.max-file-size=20MB`

### **Las imágenes no se muestran**
- Verifica que el servidor esté corriendo en `http://localhost:8080`
- Verifica que la ruta en la base de datos sea correcta (ej: `/uploads/platos/uuid.jpg`)
- Verifica que el archivo exista en la carpeta `uploads/`

---

¿Tienes preguntas? ¡Pregúntame! 🚀

