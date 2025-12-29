package com.tejaupvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) throws IOException {

        List<String> imagenes = new ArrayList<>();

        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources(
                "classpath:static/img/productos/*"
        );

        for (Resource resource : resources) {
            String filename = resource.getFilename();
            if (filename != null) {
                imagenes.add("/img/productos/" + filename);
            }
        }

        model.addAttribute("imagenes", imagenes);
        return "index";
    }
}
