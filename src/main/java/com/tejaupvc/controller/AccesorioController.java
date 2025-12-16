package com.tejaupvc.controller;

import com.tejaupvc.model.Accesorio;
import com.tejaupvc.service.AccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accesorios")
public class AccesorioController {
    
    @Autowired
    private AccesorioService accesorioService;
    
    @GetMapping
    public String listarAccesorios(Model model) {
        List<Accesorio> accesorios = accesorioService.findAll();
        model.addAttribute("accesorios", accesorios);
        return "accesorios/lista"; // Asegúrate de que esta vista exista
    }
    
    @GetMapping("/{id}")
    public String verAccesorio(@PathVariable Long id, Model model) {
        Accesorio accesorio = accesorioService.getAccesorioById(id);
        if (accesorio != null) {
            model.addAttribute("accesorio", accesorio);
            return "accesorios/detalle"; // Asegúrate de que esta vista exista
        }
        return "redirect:/accesorios";
    }
    
    @GetMapping("/filtrar")
    public String filtrarAccesorios(@RequestParam(required = false) String color,
                                   Model model) {
        List<Accesorio> accesorios = accesorioService.findAll();
        
        // Filtrar por color si se proporciona
        if (color != null && !color.isEmpty()) {
            accesorios = accesorios.stream()
                .filter(a -> a.getColor() != null && color.equalsIgnoreCase(a.getColor()))
                .toList();
        }
        
        model.addAttribute("accesorios", accesorios);
        model.addAttribute("color", color);
        return "accesorios/lista";
    }
    
    // MÉTODO PARA AGREGAR ACCESORIOS AL CARRITO
    @PostMapping("/agregar-al-carrito/{id}")
    public String agregarAccesorioAlCarrito(@PathVariable Long id,
                                           @RequestParam(defaultValue = "1") int cantidad) {
        Accesorio accesorio = accesorioService.getAccesorioById(id);
        
        if (accesorio != null) {
            // Aquí deberías llamar a tu CarritoService
            // Ejemplo: carritoService.agregarAccesorio(accesorio, cantidad);
            System.out.println("Agregando al carrito: " + accesorio.getNombre() + 
                             ", Color: " + accesorio.getColor() + 
                             ", Cantidad: " + cantidad);
        }
        
        return "redirect:/catalogo"; // Redirigir al catálogo o donde corresponda
    }
}