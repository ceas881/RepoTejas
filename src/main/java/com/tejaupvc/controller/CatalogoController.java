package com.tejaupvc.controller;

import com.tejaupvc.model.Producto;
import com.tejaupvc.model.Accesorio;
import com.tejaupvc.service.ProductoService;
import com.tejaupvc.service.AccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogoController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private AccesorioService accesorioService;
    
    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        List<Producto> productos = productoService.findAll();
        List<Accesorio> accesorios = accesorioService.findAll();
        
        model.addAttribute("productos", productos);
        model.addAttribute("accesorios", accesorios);
        
        return "catalogo"; // Asegúrate de que templates/catalogo.html exista
    }
}