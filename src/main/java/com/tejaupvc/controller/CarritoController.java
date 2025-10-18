package com.tejaupvc.controller;

import com.tejaupvc.model.CarritoItem;
import com.tejaupvc.service.CarritoService;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;
import java.util.List;
// Controladores
import org.springframework.web.bind.annotation.*;
// Servicios
import org.springframework.stereotype.Service;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {
    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public List<CarritoItem> verCarrito() {
        return carritoService.getItems();
    }

    @PostMapping("/add")
    public void agregar(@RequestBody CarritoItem item) {
        carritoService.addItem(item.getProductoId(), item.getNombre(), item.getCantidad(), item.getPrecioUnitario());
    }

    @DeleteMapping("/remove/{id}")
    public void eliminar(@PathVariable Long id) {
        carritoService.removeItem(id);
    }

    @DeleteMapping("/clear")
    public void vaciar() {
        carritoService.clear();
    }

    @GetMapping("/total")
    public double total() {
        return carritoService.getTotal();
    }
}