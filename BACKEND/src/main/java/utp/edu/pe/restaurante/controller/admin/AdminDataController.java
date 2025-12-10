package utp.edu.pe.restaurante.controller.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.pe.restaurante.entity.Categoria;
import utp.edu.pe.restaurante.entity.Plato;
import utp.edu.pe.restaurante.service.CategoriaService;
import utp.edu.pe.restaurante.service.PlatoService;

/**
 * Controlador temporal para insertar datos de prueba
 * IMPORTANTE: Eliminar o desactivar este controlador después de usar
 */
@RestController
@RequestMapping("/api/admin/data")
@CrossOrigin(origins = "*")
public class AdminDataController {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Endpoint para insertar todos los platos de ejemplo
     * POST /api/admin/data/insertar-platos
     */
    @PostMapping("/insertar-platos")
    public ResponseEntity<Map<String, Object>> insertarPlatos() {
        Map<String, Object> resultado = new HashMap<>();
        int platosInsertados = 0;
        int platosExistentes = 0;

        try {
            // Obtener categorías por nombre
            List<Categoria> categorias = categoriaService.findAllActive();
            Map<String, Long> categoriaMap = new HashMap<>();
            for (Categoria cat : categorias) {
                categoriaMap.put(cat.getNombre().toLowerCase(), cat.getId());
            }

            // Verificar que existan las categorías
            Long idEntradas = categoriaMap.get("entradas");
            Long idPrincipales = categoriaMap.get("platos principales");
            Long idPostres = categoriaMap.get("postres");
            Long idBebidasFrias = categoriaMap.get("bebidas frías");
            Long idBebidasCalientes = categoriaMap.get("bebidas calientes");

            if (idEntradas == null || idPrincipales == null || idPostres == null || 
                idBebidasFrias == null || idBebidasCalientes == null) {
                resultado.put("error", "Faltan categorías. Asegúrate de que existan: Entradas, Platos Principales, Postres, Bebidas Frías, Bebidas Calientes");
                return ResponseEntity.badRequest().body(resultado);
            }

            // ENTRADAS
            platosInsertados += insertarPlatoSiNoExiste("Ceviche de Pescado", 
                "Fresco pescado marinado en limón con cebolla, ají y cilantro. Tradicional plato peruano.", 
                new BigDecimal("28.00"), idEntradas, "/assets/images/platos/ceviche.jpg", 15);
            
            platosInsertados += insertarPlatoSiNoExiste("Anticuchos", 
                "Brochetas de corazón de res marinadas en ají panca y especias, a la parrilla. Acompañadas de papa y choclo.", 
                new BigDecimal("18.00"), idEntradas, "/assets/images/platos/anticuchos.jpg", 20);
            
            platosInsertados += insertarPlatoSiNoExiste("Causa Limeña", 
                "Puré de papa amarilla con ají amarillo, relleno de pollo o atún, con mayonesa y aceitunas.", 
                new BigDecimal("22.00"), idEntradas, "/assets/images/platos/causa.jpg", 25);
            
            platosInsertados += insertarPlatoSiNoExiste("Papa a la Huancaína", 
                "Papas cocidas bañadas en cremosa salsa de ají amarillo, queso y leche. Decoradas con huevo y aceitunas.", 
                new BigDecimal("16.00"), idEntradas, "/assets/images/platos/papa-huancaina.jpg", 20);
            
            platosInsertados += insertarPlatoSiNoExiste("Tequeños", 
                "Palitos de queso envueltos en masa de harina, fritos hasta dorar. Crujientes por fuera, cremosos por dentro.", 
                new BigDecimal("14.00"), idEntradas, "/assets/images/platos/tequenos.jpg", 15);
            
            platosInsertados += insertarPlatoSiNoExiste("Chicharrón de Calamar", 
                "Aros de calamar fritos hasta quedar dorados y crujientes. Acompañados de salsa tártara.", 
                new BigDecimal("24.00"), idEntradas, "/assets/images/platos/chicharron-calamar.jpg", 18);
            
            platosInsertados += insertarPlatoSiNoExiste("Tamal Verde", 
                "Masa de maíz rellena de pollo o cerdo, envuelta en hojas de plátano y cocida al vapor.", 
                new BigDecimal("12.00"), idEntradas, "/assets/images/platos/tamal.jpg", 30);

            // PLATOS PRINCIPALES
            platosInsertados += insertarPlatoSiNoExiste("Lomo Saltado", 
                "Trozos de lomo de res salteados con cebolla, tomate y ají amarillo. Servido con papas fritas y arroz blanco.", 
                new BigDecimal("32.00"), idPrincipales, "/assets/images/platos/lomo-saltado.jpg", 25);
            
            platosInsertados += insertarPlatoSiNoExiste("Arroz con Pollo", 
                "Arroz verde cocido con pollo, cilantro y especias. Acompañado de papa a la huancaína y ají.", 
                new BigDecimal("26.00"), idPrincipales, "/assets/images/platos/arroz-con-pollo.jpg", 30);
            
            platosInsertados += insertarPlatoSiNoExiste("Aji de Gallina", 
                "Pollo deshilachado en cremosa salsa de ají amarillo, leche y queso. Servido con arroz, papa y aceitunas.", 
                new BigDecimal("28.00"), idPrincipales, "/assets/images/platos/aji-gallina.jpg", 35);
            
            platosInsertados += insertarPlatoSiNoExiste("Pollo a la Brasa", 
                "Pollo entero marinado en especias y hierbas, asado a la brasa. Acompañado de papas fritas y ensalada.", 
                new BigDecimal("35.00"), idPrincipales, "/assets/images/platos/pollo-brasa.jpg", 45);
            
            platosInsertados += insertarPlatoSiNoExiste("Seco de Res", 
                "Carne de res guisada con culantro, ají y especias. Servido con frejoles, arroz y yuca sancochada.", 
                new BigDecimal("30.00"), idPrincipales, "/assets/images/platos/seco-res.jpg", 40);
            
            platosInsertados += insertarPlatoSiNoExiste("Tallarines Verdes", 
                "Pasta bañada en cremosa salsa de albahaca, espinaca y queso. Acompañada de bistec a la plancha.", 
                new BigDecimal("27.00"), idPrincipales, "/assets/images/platos/tallarines-verdes.jpg", 25);
            
            platosInsertados += insertarPlatoSiNoExiste("Chaufa de Pollo", 
                "Arroz frito estilo chifa con pollo, verduras, huevo y salsa de soya. Plato fusión peruano-chino.", 
                new BigDecimal("24.00"), idPrincipales, "/assets/images/platos/chaufa.jpg", 20);
            
            platosInsertados += insertarPlatoSiNoExiste("Pescado a la Chorrillana", 
                "Filete de pescado frito bañado en salsa de cebolla, tomate y ají. Acompañado de arroz y yuca.", 
                new BigDecimal("29.00"), idPrincipales, "/assets/images/platos/pescado-chorrillana.jpg", 30);
            
            platosInsertados += insertarPlatoSiNoExiste("Carapulcra", 
                "Guiso tradicional de papa seca con cerdo, maní y especias. Servido con arroz y yuca frita.", 
                new BigDecimal("28.00"), idPrincipales, "/assets/images/platos/carapulcra.jpg", 45);
            
            platosInsertados += insertarPlatoSiNoExiste("Rocoto Relleno", 
                "Rocoto relleno de carne molida, queso y especias, gratinado al horno. Acompañado de pastel de papa.", 
                new BigDecimal("26.00"), idPrincipales, "/assets/images/platos/rocoto-relleno.jpg", 40);
            
            platosInsertados += insertarPlatoSiNoExiste("Cau Cau", 
                "Guiso de mondongo con papa, ají amarillo y hierbabuena. Servido con arroz blanco.", 
                new BigDecimal("25.00"), idPrincipales, "/assets/images/platos/cau-cau.jpg", 35);
            
            platosInsertados += insertarPlatoSiNoExiste("Tacu Tacu", 
                "Mezcla de arroz y frejoles fritos, servido con bistec, huevo frito y salsa criolla.", 
                new BigDecimal("27.00"), idPrincipales, "/assets/images/platos/tacu-tacu.jpg", 25);

            // POSTRES
            platosInsertados += insertarPlatoSiNoExiste("Suspiro Limeño", 
                "Dulce de leche suave cubierto con merengue italiano. Postre tradicional de Lima.", 
                new BigDecimal("12.00"), idPostres, "/assets/images/platos/suspiro-limeno.jpg", 15);
            
            platosInsertados += insertarPlatoSiNoExiste("Mazamorra Morada", 
                "Postre de maíz morado con frutas, canela y clavo de olor. Servido frío.", 
                new BigDecimal("10.00"), idPostres, "/assets/images/platos/mazamorra-morada.jpg", 20);
            
            platosInsertados += insertarPlatoSiNoExiste("Arroz con Leche", 
                "Arroz cocido en leche con canela, clavo de olor y pasas. Postre casero tradicional.", 
                new BigDecimal("9.00"), idPostres, "/assets/images/platos/arroz-leche.jpg", 25);
            
            platosInsertados += insertarPlatoSiNoExiste("Picarones", 
                "Rosquillas fritas de camote y zapallo, bañadas en miel de chancaca. Postre callejero peruano.", 
                new BigDecimal("11.00"), idPostres, "/assets/images/platos/picarones.jpg", 20);
            
            platosInsertados += insertarPlatoSiNoExiste("Tres Leches", 
                "Bizcocho esponjoso empapado en tres tipos de leche, cubierto con crema batida.", 
                new BigDecimal("13.00"), idPostres, "/assets/images/platos/tres-leches.jpg", 10);
            
            platosInsertados += insertarPlatoSiNoExiste("Flan de Coco", 
                "Flan cremoso con sabor a coco, bañado en caramelo. Postre refrescante.", 
                new BigDecimal("11.00"), idPostres, "/assets/images/platos/flan-coco.jpg", 15);
            
            platosInsertados += insertarPlatoSiNoExiste("Turrón de Doña Pepa", 
                "Postre tradicional de Lima hecho con masa de harina, anís y miel de chancaca.", 
                new BigDecimal("10.00"), idPostres, "/assets/images/platos/turron.jpg", 5);

            // BEBIDAS FRÍAS
            platosInsertados += insertarPlatoSiNoExiste("Chicha Morada", 
                "Bebida refrescante de maíz morado con piña, manzana, canela y clavo de olor.", 
                new BigDecimal("8.00"), idBebidasFrias, "/assets/images/platos/chicha-morada.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Maracuyá", 
                "Jugo natural de maracuyá, refrescante y ácido. Perfecto para acompañar cualquier plato.", 
                new BigDecimal("7.00"), idBebidasFrias, "/assets/images/platos/maracuya.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Limonada", 
                "Limonada fresca y natural, endulzada al gusto. Bebida clásica peruana.", 
                new BigDecimal("6.00"), idBebidasFrias, "/assets/images/platos/limonada.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Inca Kola", 
                "Gaseosa peruana de sabor único, amarilla y dulce. La bebida nacional del Perú.", 
                new BigDecimal("5.00"), idBebidasFrias, "/assets/images/platos/inca-kola.jpg", 2);
            
            platosInsertados += insertarPlatoSiNoExiste("Jugo de Naranja", 
                "Jugo natural de naranja recién exprimido. Rico en vitamina C.", 
                new BigDecimal("7.00"), idBebidasFrias, "/assets/images/platos/naranja.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Agua de Cebada", 
                "Bebida refrescante de cebada con limón y hierbabuena. Tradicional y saludable.", 
                new BigDecimal("6.00"), idBebidasFrias, "/assets/images/platos/cebada.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Coca Cola", 
                "Gaseosa clásica, fría y refrescante.", 
                new BigDecimal("5.00"), idBebidasFrias, "/assets/images/platos/coca-cola.jpg", 2);
            
            platosInsertados += insertarPlatoSiNoExiste("Agua Mineral", 
                "Agua mineral natural, sin gas. Hidratación pura.", 
                new BigDecimal("4.00"), idBebidasFrias, "/assets/images/platos/agua.jpg", 1);

            // BEBIDAS CALIENTES
            platosInsertados += insertarPlatoSiNoExiste("Café Pasado", 
                "Café peruano de altura, preparado al estilo tradicional. Aromático y delicioso.", 
                new BigDecimal("6.00"), idBebidasCalientes, "/assets/images/platos/cafe.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Té de Hierbas", 
                "Infusión caliente de hierbas naturales: manzanilla, menta o anís. Relajante y digestivo.", 
                new BigDecimal("5.00"), idBebidasCalientes, "/assets/images/platos/te-hierbas.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Emoliente", 
                "Bebida caliente tradicional peruana de cebada, linaza y hierbas. Saludable y reconfortante.", 
                new BigDecimal("5.00"), idBebidasCalientes, "/assets/images/platos/emoliente.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Chocolate Caliente", 
                "Chocolate caliente cremoso y espeso, perfecto para acompañar postres.", 
                new BigDecimal("7.00"), idBebidasCalientes, "/assets/images/platos/chocolate.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Café con Leche", 
                "Café pasado con leche caliente. Bebida clásica y reconfortante.", 
                new BigDecimal("7.00"), idBebidasCalientes, "/assets/images/platos/cafe-leche.jpg", 5);
            
            platosInsertados += insertarPlatoSiNoExiste("Té de Coca", 
                "Infusión de hojas de coca, tradicional de los Andes. Ayuda con la altura y digestión.", 
                new BigDecimal("6.00"), idBebidasCalientes, "/assets/images/platos/te-coca.jpg", 5);

            resultado.put("success", true);
            resultado.put("platosInsertados", platosInsertados);
            resultado.put("platosExistentes", platosExistentes);
            resultado.put("mensaje", "Se insertaron " + platosInsertados + " platos exitosamente. " + 
                (platosExistentes > 0 ? platosExistentes + " platos ya existían." : ""));

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            resultado.put("success", false);
            resultado.put("error", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(resultado);
        }
    }

    /**
     * Método auxiliar para insertar un plato solo si no existe
     */
    private int insertarPlatoSiNoExiste(String nombre, String descripcion, BigDecimal precio, 
                                       Long categoriaId, String imagenUrl, Integer tiempoPreparacion) {
        try {
            // Verificar si el plato ya existe
            if (platoService.existsByNombre(nombre)) {
                return 0; // Ya existe, no insertar
            }

            // Crear el plato
            Plato plato = new Plato();
            plato.setNombre(nombre);
            plato.setDescripcion(descripcion);
            plato.setPrecio(precio);
            plato.setImagenUrl(imagenUrl);
            plato.setTiempoPreparacion(tiempoPreparacion);
            plato.setActivo(true);
            plato.setDisponibleDomicilio(true);

            // Asignar categoría
            Categoria categoria = new Categoria();
            categoria.setId(categoriaId);
            plato.setCategoria(categoria);

            // Guardar
            platoService.save(plato);
            return 1; // Insertado exitosamente

        } catch (Exception e) {
            System.err.println("Error al insertar plato: " + nombre + " - " + e.getMessage());
            return 0;
        }
    }
}

