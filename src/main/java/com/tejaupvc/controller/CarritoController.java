package com.tejaupvc.controller;

import com.tejaupvc.model.*;
import com.tejaupvc.service.*;
import com.tejaupvc.session.CarritoSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private CarritoSession getCarrito(HttpSession session) {
        CarritoSession carrito = (CarritoSession) session.getAttribute("CARRITO");
        if (carrito == null) {
            carrito = new CarritoSession();
            session.setAttribute("CARRITO", carrito);
        }
        return carrito;
    }

    @GetMapping
    public String verCarrito(Model model, HttpSession session) {
        model.addAttribute("items", getCarrito(session).getItems());
        return "carrito";
    }

    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable Long id,
                                  @RequestParam String nombre,
                                  @RequestParam double precio,
                                  @RequestParam String imagen_url,
                                  @RequestParam(defaultValue = "1") int cantidad,
                                  HttpSession session) {

        CarritoItem item = new CarritoItem(id, nombre, cantidad, precio,
                "producto", imagen_url, null);

        getCarrito(session).agregar(item);
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, @RequestParam String tipo, HttpSession session) {
        getCarrito(session).eliminar(id, tipo);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciar(HttpSession session) {
        getCarrito(session).vaciar();
        return "redirect:/carrito";
    }
}
