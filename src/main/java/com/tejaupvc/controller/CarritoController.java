package com.tejaupvc.controller;

import com.tejaupvc.model.Producto;
import com.tejaupvc.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/carrito")
@SessionAttributes("carrito")
public class CarritoController {

    @Autowired
    private ProductoRepository productoRepo;

    // 🛒 Inicializa el carrito de sesión
    @ModelAttribute("carrito")
    public Map<Long, Integer> crearCarrito() {
        return new HashMap<>();
    }

    // 📦 Mostrar carrito
    @GetMapping
    public String verCarrito(@ModelAttribute("carrito") Map<Long, Integer> carrito, Model model) {
        List<Map<String, Object>> items = new ArrayList<>();
        double total = 0;

        for (var entry : carrito.entrySet()) {
            Producto p = productoRepo.findById(entry.getKey()).orElse(null);
            if (p != null) {
                double subtotal = p.getPrecio() * entry.getValue();
                total += subtotal;

                Map<String, Object> item = new HashMap<>();
                item.put("producto", p);
                item.put("cantidad", entry.getValue());
                item.put("subtotal", subtotal);
                items.add(item);
            }
        }

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "carrito";
    }

    // ➕ Agregar producto al carrito
    @PostMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id,
                                   @ModelAttribute("carrito") Map<Long, Integer> carrito) {
        carrito.put(id, carrito.getOrDefault(id, 0) + 1);
        return "redirect:/catalogo";
    }

    // 🧹 Vaciar carrito
    @PostMapping("/vaciar")
    public String vaciarCarrito(@ModelAttribute("carrito") Map<Long, Integer> carrito) {
        carrito.clear();
        return "redirect:/carrito";
    }

    // 💳 Simular compra
    @PostMapping("/comprar")
    public String comprar(@ModelAttribute("carrito") Map<Long, Integer> carrito, Model model) {
        carrito.clear();
        model.addAttribute("mensaje", "¡Compra realizada con éxito!");
        return "redirect:/catalogo";
    }
}