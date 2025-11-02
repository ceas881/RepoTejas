package com.tejaupvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo"; // Muestra templates/catalogo.html
    }
    /*@GetMapping("/carrito")
    public String carrito() {
        return "carrito"; // Esto busca templates/carrito.html
    }*/
}