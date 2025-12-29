package com.tejaupvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) throws IOException {

        List<String> imagenes = new ArrayList<>();

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath:/static/img/productos/*");

        for (Resource resource : resources) {
            String nombre = resource.getFilename();
            imagenes.add("/img/productos/" + nombre);
        }

        model.addAttribute("imagenes", imagenes);
        return "index";
    }
}
