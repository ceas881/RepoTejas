package com.tejaupvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index"; // busca src/main/resources/templates/index.html
    }

    //@GetMapping("/catalogo")
    //public String catalogo() {
    //    return "catalogo"; // busca templates/catalogo.html
    //}

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}