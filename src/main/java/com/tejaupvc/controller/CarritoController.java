package com.tejaupvc.controller;

import com.tejaupvc.model.Producto;
import com.tejaupvc.model.Accesorio;
import com.tejaupvc.service.CarritoService;
import com.tejaupvc.service.ProductoService;
import com.tejaupvc.service.AccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    
    @Autowired
    private CarritoService carritoService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private AccesorioService accesorioService;
    
    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("items", carritoService.getItems());
        model.addAttribute("total", carritoService.getTotal());
        return "carrito";
    }
    
    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable Long id,
                                 @RequestParam(defaultValue = "1") int cantidad) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            carritoService.addItem(
                producto.getId(),
                producto.getNombre(),
                cantidad,
                producto.getPrecio(),
                "producto",
                producto.getImagen_url()  // ← Pasar imagen_url
            );
        }
        return "redirect:/catalogo";
    }
    
    @PostMapping("/agregar/accesorio/{id}")
    public String agregarAccesorio(@PathVariable Long id,
                                  @RequestParam(defaultValue = "1") int cantidad) {
        Accesorio accesorio = accesorioService.getAccesorioById(id);
        if (accesorio != null) {
            carritoService.addItem(
                accesorio.getId(),
                accesorio.getNombre(),
                cantidad,
                accesorio.getPrecio(),
                "accesorio",
                accesorio.getImagen_url(),  // ← Pasar imagen_url
                accesorio.getColor() 
            );
        }
        return "redirect:/catalogo";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Long id,
                                    @RequestParam String tipo) {
        carritoService.removeItem(id, tipo);
        return "redirect:/carrito";
    }
    
    @PostMapping("/vaciar")
    public String vaciarCarrito() {
        carritoService.clear();
        return "redirect:/carrito";
    }
}