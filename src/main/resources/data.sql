-- Datos de prueba para la base de datos del restaurante
-- Este archivo se ejecuta automáticamente después del schema.sql

-- Insertar categorías de ejemplo
INSERT IGNORE INTO categorias (nombre, descripcion, tipo, activa) VALUES
('Entradas', 'Aperitivos y entradas deliciosas', 'COMIDA', TRUE),
('Platos Principales', 'Platos principales y especialidades', 'COMIDA', TRUE),
('Postres', 'Dulces y postres caseros', 'COMIDA', TRUE),
('Bebidas Frías', 'Refrescos y bebidas frías', 'BEBIDA', TRUE),
('Bebidas Calientes', 'Café, té y bebidas calientes', 'BEBIDA', TRUE);

-- Insertar platos de ejemplo
INSERT IGNORE INTO platos (nombre, descripcion, precio, categoria_id, imagen_url, tiempo_preparacion, disponible_domicilio, activo) VALUES
-- Entradas
('Ceviche de Pescado', 'Fresco ceviche de pescado con cebolla, cilantro y ají', 18.50, 1, 'https://example.com/ceviche.jpg', 15, TRUE, TRUE),
('Anticuchos', 'Brochetas de corazón de res marinadas', 16.00, 1, 'https://example.com/anticuchos.jpg', 20, TRUE, TRUE),

-- Platos Principales
('Lomo Saltado', 'Tradicional lomo saltado con papas fritas y arroz', 24.00, 2, 'https://example.com/lomo-saltado.jpg', 25, TRUE, TRUE),
('Arroz con Pollo', 'Arroz con pollo y verduras', 20.00, 2, 'https://example.com/arroz-pollo.jpg', 30, TRUE, TRUE),
('Ají de Gallina', 'Cremoso ají de gallina con papas', 22.00, 2, 'https://example.com/aji-gallina.jpg', 25, TRUE, TRUE),

-- Postres
('Mazamorra Morada', 'Dulce tradicional de maíz morado', 8.00, 3, 'https://example.com/mazamorra.jpg', 10, TRUE, TRUE),
('Arroz con Leche', 'Cremoso arroz con leche', 7.50, 3, 'https://example.com/arroz-leche.jpg', 15, TRUE, TRUE),

-- Bebidas Frías
('Chicha Morada', 'Refrescante chicha morada', 5.00, 4, 'https://example.com/chicha.jpg', 5, TRUE, TRUE),
('Limonada', 'Limonada natural', 4.50, 4, 'https://example.com/limonada.jpg', 5, TRUE, TRUE),

-- Bebidas Calientes
('Café Americano', 'Café negro americano', 3.50, 5, 'https://example.com/cafe.jpg', 3, TRUE, TRUE),
('Té de Manzanilla', 'Relajante té de manzanilla', 2.50, 5, 'https://example.com/te-manzanilla.jpg', 2, TRUE, TRUE);
