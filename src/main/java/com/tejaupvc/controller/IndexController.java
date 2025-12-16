package com.tejaupvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {

        File folder = new File("src/main/resources/static/images");

        // Extensiones permitidas
        File[] files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".jpeg") ||
                name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".webp")
        );

        List<String> imagenes = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                imagenes.add("/images/" + file.getName());
            }
        }

        model.addAttribute("imagenes", imagenes);

        return "index";
    }
}