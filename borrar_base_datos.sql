-- ============================================
-- SCRIPT PARA BORRAR COMPLETAMENTE LA BASE DE DATOS
-- ============================================
-- 
-- INSTRUCCIONES:
-- 1. Abre MySQL Workbench o tu cliente MySQL
-- 2. Conéctate al servidor MySQL (no necesitas estar en una base de datos específica)
-- 3. Copia y pega este script completo
-- 4. Ejecuta el script
--
-- IMPORTANTE: 
-- - Esto BORRARÁ COMPLETAMENTE la base de datos restaurante_db
-- - TODOS los datos se perderán (usuarios, categorías, platos, pedidos, etc.)
-- - La base de datos se RECREARÁ automáticamente cuando ejecutes el proyecto Spring Boot
-- - Las tablas se crearán automáticamente gracias a Hibernate
-- - Los datos iniciales se insertarán desde data.sql

-- Borrar la base de datos completa
DROP DATABASE IF EXISTS restaurante_db;

-- Mensaje de confirmación
SELECT 'Base de datos restaurante_db eliminada exitosamente.' AS resultado;
SELECT 'La base de datos se recreará automáticamente al ejecutar el proyecto Spring Boot.' AS siguiente_paso;

