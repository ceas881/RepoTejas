package com.tejaupvc.controller;

import com.tejaupvc.session.CarritoSession;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        CarritoSession carrito = (CarritoSession) session.getAttribute("CARRITO");
        model.addAttribute("items", carrito != null ? carrito.getItems() : List.of());
        return "checkout";
    }
}