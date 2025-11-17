-- Datos de prueba para la base de datos del restaurante
-- Este archivo se ejecuta automáticamente después del schema.sql

-- Insertar categorías de ejemplo
INSERT IGNORE INTO categorias (nombre, descripcion, tipo, activa) VALUES
('Entradas', 'Aperitivos y entradas deliciosas', 'COMIDA', TRUE),
('Platos Principales', 'Platos principales y especialidades', 'COMIDA', TRUE),
('Postres', 'Dulces y postres caseros', 'COMIDA', TRUE),
('Bebidas Frías', 'Refrescos y bebidas frías', 'BEBIDA', TRUE),
('Bebidas Calientes', 'Café, té y bebidas calientes', 'BEBIDA', TRUE);


