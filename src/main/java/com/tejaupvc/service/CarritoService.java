package com.tejaupvc.service;

import com.tejaupvc.model.CarritoItem;
import org.springframework.stereotype.Service;
// Controladores
import org.springframework.web.bind.annotation.*;
// Servicios
import org.springframework.stereotype.Service;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

@Service
public class CarritoService {
    private Map<Long, CarritoItem> items = new HashMap<>();

    public List<CarritoItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public void addItem(Long productoId, String nombre, int cantidad, double precio) {
        if (items.containsKey(productoId)) {
            CarritoItem item = items.get(productoId);
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            items.put(productoId, new CarritoItem(productoId, nombre, cantidad, precio));
        }
    }

    public void removeItem(Long productoId) {
        items.remove(productoId);
    }

    public double getTotal() {
        return items.values().stream().mapToDouble(CarritoItem::getSubtotal).sum();
    }

    public void clear() {
        items.clear();
    }
}