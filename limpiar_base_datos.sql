-- ============================================
-- SCRIPT PARA LIMPIAR BASE DE DATOS
-- Elimina todos los datos y reinicia autoincrement
-- ============================================
-- 
-- INSTRUCCIONES:
-- 1. Abre MySQL Workbench o tu cliente MySQL
-- 2. Conéctate a la base de datos: restaurante_db
-- 3. Copia y pega este script completo
-- 4. Ejecuta el script
--
-- IMPORTANTE: Este script eliminará TODOS los datos de:
-- - Detalles de pedidos
-- - Pedidos
-- - Platos
-- 
-- NO elimina:
-- - Usuarios
-- - Categorías
-- - Reservas

USE restaurante_db;

-- Desactivar verificación de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- 1. Eliminar detalles de pedidos (primero por las foreign keys)
DELETE FROM detalle_pedidos;

-- 2. Eliminar pedidos
DELETE FROM pedidos;

-- 3. Eliminar platos
DELETE FROM platos;

-- Reiniciar autoincrement de las tablas
ALTER TABLE detalle_pedidos AUTO_INCREMENT = 1;
ALTER TABLE pedidos AUTO_INCREMENT = 1;
ALTER TABLE platos AUTO_INCREMENT = 1;

-- Reactivar verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;

-- Verificar que se hayan eliminado los datos
SELECT 'Detalles de pedidos restantes: ' AS mensaje, COUNT(*) AS cantidad FROM detalle_pedidos;
SELECT 'Pedidos restantes: ' AS mensaje, COUNT(*) AS cantidad FROM pedidos;
SELECT 'Platos restantes: ' AS mensaje, COUNT(*) AS cantidad FROM platos;

-- Mensaje de confirmación
SELECT 'Base de datos limpiada exitosamente. Los próximos pedidos empezarán desde el número 1.' AS resultado;

