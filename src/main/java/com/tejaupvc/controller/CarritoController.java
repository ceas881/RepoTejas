package com.tejaupvc.controller;

import com.tejaupvc.model.*;
import com.tejaupvc.session.CarritoSession;
import com.tejaupvc.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AccesorioService accesorioService;

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

    @PostMapping("/agregar/producto/{id}")
    public String agregarProducto(@PathVariable Long id,
                                  @RequestParam(defaultValue = "1") int cantidad,
                                  HttpSession session) {

        Producto p = productoService.getProductoById(id);

        if (p != null) {
            CarritoItem item = new CarritoItem(
                p.getId(),
                p.getNombre(),
                cantidad,
                p.getPrecio(),
                "producto",
                p.getImagen_url(),
                null
            );
            getCarrito(session).agregar(item);
        }
        return "redirect:/carrito";
    }

    @PostMapping("/agregar/accesorio/{id}")
    public String agregarAccesorio(@PathVariable Long id,
                                   @RequestParam(defaultValue = "1") int cantidad,
                                   HttpSession session) {

        Accesorio a = accesorioService.getAccesorioById(id);

        if (a != null) {
            CarritoItem item = new CarritoItem(
                a.getId(),
                a.getNombre(),
                cantidad,
                a.getPrecio(),
                "accesorio",
                a.getImagen_url(),
                a.getColor()
            );
            getCarrito(session).agregar(item);
        }
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id,
                           @RequestParam String tipo,
                           HttpSession session) {
        getCarrito(session).eliminar(id, tipo);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciar(HttpSession session) {
        getCarrito(session).vaciar();
        return "redirect:/carrito";
    }
}
