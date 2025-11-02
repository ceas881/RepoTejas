package com.tejaupvc.controller;

import com.tejaupvc.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @Autowired
    private ProductoRepository productoRepo;

    /*@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "catalogo";
    }*/
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto"; // Busca contacto.html en /templates

    }
}
