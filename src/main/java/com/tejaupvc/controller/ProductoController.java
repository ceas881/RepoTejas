package com.tejaupvc.controller;

import com.tejaupvc.model.Producto;
import com.tejaupvc.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "productos/lista"; // Asegúrate de que esta vista exista
    }
    
    @GetMapping("/{id}")
    public String verProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "productos/detalle"; // Asegúrate de que esta vista exista
        }
        return "redirect:/productos";
    }
    
    @GetMapping("/filtrar")
    public String filtrarProductos(@RequestParam(required = false) String color,
                                  @RequestParam(required = false) String tipoCresta,
                                  Model model) {
        List<Producto> productos = productoService.findAll();
        
        // Filtrar por color si se proporciona
        if (color != null && !color.isEmpty()) {
            productos = productos.stream()
                .filter(p -> p.getColor() != null && color.equalsIgnoreCase(p.getColor()))
                .toList();
        }
        
        // Filtrar por tipo de cresta si se proporciona
        if (tipoCresta != null && !tipoCresta.isEmpty()) {
            productos = productos.stream()
                .filter(p -> p.getTipoCresta() != null && tipoCresta.equalsIgnoreCase(p.getTipoCresta()))
                .toList();
        }
        
        model.addAttribute("productos", productos);
        model.addAttribute("color", color);
        model.addAttribute("tipoCresta", tipoCresta);
        return "productos/lista";
    }
    
    // MÉTODO PARA AGREGAR PRODUCTOS AL CARRITO (si no tienes uno separado)
    @PostMapping("/agregar-al-carrito/{id}")
    public String agregarAlCarrito(@PathVariable Long id,
                                  @RequestParam(defaultValue = "1") int cantidad) {
        // Este método debería estar en un CarritoController separado
        // Por ahora lo dejamos aquí como referencia
        Producto producto = productoService.getProductoById(id);
        
        if (producto != null) {
            // Aquí deberías llamar a tu CarritoService
            // Ejemplo: carritoService.agregarProducto(producto, cantidad);
            System.out.println("Agregando al carrito: " + producto.getNombre() + 
                             ", Color: " + producto.getColor() + 
                             ", Cantidad: " + cantidad);
        }
        
        return "redirect:/catalogo"; // Redirigir al catálogo o donde corresponda
    }
}