# Etapa 1: Build de la aplicación Angular
FROM node:18-alpine AS build

WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm ci

# Copiar código fuente
COPY . .

# Construir la aplicación para producción
RUN npm run build -- --configuration production

# Etapa 2: Servir con Nginx
FROM nginx:alpine

# Copiar los archivos construidos desde la etapa de build
# Angular 17+ puede generar en browser/ o directamente en dist/
COPY --from=build /app/dist/angular-restaurant-frontend/browser /usr/share/nginx/html 2>/dev/null || \
COPY --from=build /app/dist/angular-restaurant-frontend /usr/share/nginx/html

# Copiar configuración de nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer puerto 80
EXPOSE 80

# Comando para iniciar nginx
CMD ["nginx", "-g", "daemon off;"]

