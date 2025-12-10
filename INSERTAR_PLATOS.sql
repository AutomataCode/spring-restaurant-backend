-- Script SQL para insertar platos en la base de datos
-- Ejecutar este script en MySQL desde Railway
-- Las categorías deben existir primero (se crean automáticamente con data.sql)

-- NOTA: Los IDs de categorías asumen este orden:
-- 1 = Entradas
-- 2 = Platos Principales  
-- 3 = Postres
-- 4 = Bebidas Frías
-- 5 = Bebidas Calientes

-- Si las categorías tienen otros IDs, ajusta los valores de categoria_id

-- ============================================
-- ENTRADAS (categoria_id = 1)
-- ============================================

INSERT INTO platos (nombre, descripcion, precio, categoria_id, imagen_url, activo, tiempo_preparacion, disponible_domicilio, fecha_creacion, fecha_actualizacion) VALUES
('Ceviche de Pescado', 'Fresco pescado marinado en limón con cebolla, ají y cilantro. Tradicional plato peruano.', 28.00, 1, '/assets/images/platos/ceviche.jpg', TRUE, 15, TRUE, NOW(), NOW()),

('Anticuchos', 'Brochetas de corazón de res marinadas en ají panca y especias, a la parrilla. Acompañadas de papa y choclo.', 18.00, 1, '/assets/images/platos/anticuchos.jpg', TRUE, 20, TRUE, NOW(), NOW()),

('Causa Limeña', 'Puré de papa amarilla con ají amarillo, relleno de pollo o atún, con mayonesa y aceitunas.', 22.00, 1, '/assets/images/platos/causa.jpg', TRUE, 25, TRUE, NOW(), NOW()),

('Papa a la Huancaína', 'Papas cocidas bañadas en cremosa salsa de ají amarillo, queso y leche. Decoradas con huevo y aceitunas.', 16.00, 1, '/assets/images/platos/papa-huancaina.jpg', TRUE, 20, TRUE, NOW(), NOW()),

('Tequeños', 'Palitos de queso envueltos en masa de harina, fritos hasta dorar. Crujientes por fuera, cremosos por dentro.', 14.00, 1, '/assets/images/platos/tequenos.jpg', TRUE, 15, TRUE, NOW(), NOW()),

('Chicharrón de Calamar', 'Aros de calamar fritos hasta quedar dorados y crujientes. Acompañados de salsa tártara.', 24.00, 1, '/assets/images/platos/chicharron-calamar.jpg', TRUE, 18, TRUE, NOW(), NOW()),

('Tamal Verde', 'Masa de maíz rellena de pollo o cerdo, envuelta en hojas de plátano y cocida al vapor.', 12.00, 1, '/assets/images/platos/tamal.jpg', TRUE, 30, TRUE, NOW(), NOW());

-- ============================================
-- PLATOS PRINCIPALES (categoria_id = 2)
-- ============================================

INSERT INTO platos (nombre, descripcion, precio, categoria_id, imagen_url, activo, tiempo_preparacion, disponible_domicilio, fecha_creacion, fecha_actualizacion) VALUES
('Lomo Saltado', 'Trozos de lomo de res salteados con cebolla, tomate y ají amarillo. Servido con papas fritas y arroz blanco.', 32.00, 2, '/assets/images/platos/lomo-saltado.jpg', TRUE, 25, TRUE, NOW(), NOW()),

('Arroz con Pollo', 'Arroz verde cocido con pollo, cilantro y especias. Acompañado de papa a la huancaína y ají.', 26.00, 2, '/assets/images/platos/arroz-con-pollo.jpg', TRUE, 30, TRUE, NOW(), NOW()),

('Aji de Gallina', 'Pollo deshilachado en cremosa salsa de ají amarillo, leche y queso. Servido con arroz, papa y aceitunas.', 28.00, 2, '/assets/images/platos/aji-gallina.jpg', TRUE, 35, TRUE, NOW(), NOW()),

('Pollo a la Brasa', 'Pollo entero marinado en especias y hierbas, asado a la brasa. Acompañado de papas fritas y ensalada.', 35.00, 2, '/assets/images/platos/pollo-brasa.jpg', TRUE, 45, TRUE, NOW(), NOW()),

('Seco de Res', 'Carne de res guisada con culantro, ají y especias. Servido con frejoles, arroz y yuca sancochada.', 30.00, 2, '/assets/images/platos/seco-res.jpg', TRUE, 40, TRUE, NOW(), NOW()),

('Tallarines Verdes', 'Pasta bañada en cremosa salsa de albahaca, espinaca y queso. Acompañada de bistec a la plancha.', 27.00, 2, '/assets/images/platos/tallarines-verdes.jpg', TRUE, 25, TRUE, NOW(), NOW()),

('Chaufa de Pollo', 'Arroz frito estilo chifa con pollo, verduras, huevo y salsa de soya. Plato fusión peruano-chino.', 24.00, 2, '/assets/images/platos/chaufa.jpg', TRUE, 20, TRUE, NOW(), NOW()),

('Pescado a la Chorrillana', 'Filete de pescado frito bañado en salsa de cebolla, tomate y ají. Acompañado de arroz y yuca.', 29.00, 2, '/assets/images/platos/pescado-chorrillana.jpg', TRUE, 30, TRUE, NOW(), NOW()),

('Carapulcra', 'Guiso tradicional de papa seca con cerdo, maní y especias. Servido con arroz y yuca frita.', 28.00, 2, '/assets/images/platos/carapulcra.jpg', TRUE, 45, TRUE, NOW(), NOW()),

('Rocoto Relleno', 'Rocoto relleno de carne molida, queso y especias, gratinado al horno. Acompañado de pastel de papa.', 26.00, 2, '/assets/images/platos/rocoto-relleno.jpg', TRUE, 40, TRUE, NOW(), NOW()),

('Cau Cau', 'Guiso de mondongo con papa, ají amarillo y hierbabuena. Servido con arroz blanco.', 25.00, 2, '/assets/images/platos/cau-cau.jpg', TRUE, 35, TRUE, NOW(), NOW()),

('Tacu Tacu', 'Mezcla de arroz y frejoles fritos, servido con bistec, huevo frito y salsa criolla.', 27.00, 2, '/assets/images/platos/tacu-tacu.jpg', TRUE, 25, TRUE, NOW(), NOW());

-- ============================================
-- POSTRES (categoria_id = 3)
-- ============================================

INSERT INTO platos (nombre, descripcion, precio, categoria_id, imagen_url, activo, tiempo_preparacion, disponible_domicilio, fecha_creacion, fecha_actualizacion) VALUES
('Suspiro Limeño', 'Dulce de leche suave cubierto con merengue italiano. Postre tradicional de Lima.', 12.00, 3, '/assets/images/platos/suspiro-limeno.jpg', TRUE, 15, TRUE, NOW(), NOW()),

('Mazamorra Morada', 'Postre de maíz morado con frutas, canela y clavo de olor. Servido frío.', 10.00, 3, '/assets/images/platos/mazamorra-morada.jpg', TRUE, 20, TRUE, NOW(), NOW()),

('Arroz con Leche', 'Arroz cocido en leche con canela, clavo de olor y pasas. Postre casero tradicional.', 9.00, 3, '/assets/images/platos/arroz-leche.jpg', TRUE, 25, TRUE, NOW(), NOW()),

('Picarones', 'Rosquillas fritas de camote y zapallo, bañadas en miel de chancaca. Postre callejero peruano.', 11.00, 3, '/assets/images/platos/picarones.jpg', TRUE, 20, TRUE, NOW(), NOW()),

('Tres Leches', 'Bizcocho esponjoso empapado en tres tipos de leche, cubierto con crema batida.', 13.00, 3, '/assets/images/platos/tres-leches.jpg', TRUE, 10, TRUE, NOW(), NOW()),

('Flan de Coco', 'Flan cremoso con sabor a coco, bañado en caramelo. Postre refrescante.', 11.00, 3, '/assets/images/platos/flan-coco.jpg', TRUE, 15, TRUE, NOW(), NOW()),

('Turrón de Doña Pepa', 'Postre tradicional de Lima hecho con masa de harina, anís y miel de chancaca.', 10.00, 3, '/assets/images/platos/turron.jpg', TRUE, 5, TRUE, NOW(), NOW());

-- ============================================
-- BEBIDAS FRÍAS (categoria_id = 4)
-- ============================================

INSERT INTO platos (nombre, descripcion, precio, categoria_id, imagen_url, activo, tiempo_preparacion, disponible_domicilio, fecha_creacion, fecha_actualizacion) VALUES
('Chicha Morada', 'Bebida refrescante de maíz morado con piña, manzana, canela y clavo de olor.', 8.00, 4, '/assets/images/platos/chicha-morada.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Maracuyá', 'Jugo natural de maracuyá, refrescante y ácido. Perfecto para acompañar cualquier plato.', 7.00, 4, '/assets/images/platos/maracuya.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Limonada', 'Limonada fresca y natural, endulzada al gusto. Bebida clásica peruana.', 6.00, 4, '/assets/images/platos/limonada.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Inca Kola', 'Gaseosa peruana de sabor único, amarilla y dulce. La bebida nacional del Perú.', 5.00, 4, '/assets/images/platos/inca-kola.jpg', TRUE, 2, TRUE, NOW(), NOW()),

('Jugo de Naranja', 'Jugo natural de naranja recién exprimido. Rico en vitamina C.', 7.00, 4, '/assets/images/platos/naranja.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Agua de Cebada', 'Bebida refrescante de cebada con limón y hierbabuena. Tradicional y saludable.', 6.00, 4, '/assets/images/platos/cebada.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Coca Cola', 'Gaseosa clásica, fría y refrescante.', 5.00, 4, '/assets/images/platos/coca-cola.jpg', TRUE, 2, TRUE, NOW(), NOW()),

('Agua Mineral', 'Agua mineral natural, sin gas. Hidratación pura.', 4.00, 4, '/assets/images/platos/agua.jpg', TRUE, 1, TRUE, NOW(), NOW());

-- ============================================
-- BEBIDAS CALIENTES (categoria_id = 5)
-- ============================================

INSERT INTO platos (nombre, descripcion, precio, categoria_id, imagen_url, activo, tiempo_preparacion, disponible_domicilio, fecha_creacion, fecha_actualizacion) VALUES
('Café Pasado', 'Café peruano de altura, preparado al estilo tradicional. Aromático y delicioso.', 6.00, 5, '/assets/images/platos/cafe.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Té de Hierbas', 'Infusión caliente de hierbas naturales: manzanilla, menta o anís. Relajante y digestivo.', 5.00, 5, '/assets/images/platos/te-hierbas.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Emoliente', 'Bebida caliente tradicional peruana de cebada, linaza y hierbas. Saludable y reconfortante.', 5.00, 5, '/assets/images/platos/emoliente.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Chocolate Caliente', 'Chocolate caliente cremoso y espeso, perfecto para acompañar postres.', 7.00, 5, '/assets/images/platos/chocolate.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Café con Leche', 'Café pasado con leche caliente. Bebida clásica y reconfortante.', 7.00, 5, '/assets/images/platos/cafe-leche.jpg', TRUE, 5, TRUE, NOW(), NOW()),

('Té de Coca', 'Infusión de hojas de coca, tradicional de los Andes. Ayuda con la altura y digestión.', 6.00, 5, '/assets/images/platos/te-coca.jpg', TRUE, 5, TRUE, NOW(), NOW());

-- ============================================
-- RESUMEN
-- ============================================
-- Total de platos insertados: 40
-- - Entradas: 7 platos
-- - Platos Principales: 12 platos
-- - Postres: 7 platos
-- - Bebidas Frías: 8 bebidas
-- - Bebidas Calientes: 6 bebidas

