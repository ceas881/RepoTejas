package com.tejaupvc.controller;

import com.tejaupvc.model.ItemCarrito;
import com.tejaupvc.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/checkout")
    public String checkout(Model model) {

        // Cambiar CarritoItem → ItemCarrito
        model.addAttribute("items", carritoService.getItems());
        model.addAttribute("total", carritoService.getItems().stream()
                //.mapToDouble(i -> i.getPrecio() * i.getCantidad())
                //.sum()
        );

        return "checkout";
    }
}