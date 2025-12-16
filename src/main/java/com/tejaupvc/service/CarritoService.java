package com.tejaupvc.service;

import com.tejaupvc.model.ItemCarrito;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {

    private final List<ItemCarrito> items = new ArrayList<>();

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.getSubtotal() != null ? item.getSubtotal() : 0.0)
                .sum();
    }

    public void addItem(Long id, String nombre, int cantidad, Double precio,
                        String tipo, String imagen_url, String color) {
        
        // Validar que precio no sea nulo
        if (precio == null) {
            precio = 0.0;
        }
        
        // Validar cantidad
        if (cantidad <= 0) {
            cantidad = 1;
        }
        
        // Calcular subtotal inicial
        double subtotalCalculado = precio * cantidad;
        
        // Buscar si el item ya existe
        for (ItemCarrito it : items) {
            if (it.getId().equals(id) && it.getTipo().equals(tipo)) {
                it.setCantidad(it.getCantidad() + cantidad);
                double nuevoSubtotal = it.getPrecio() * it.getCantidad();
                it.setSubtotal(nuevoSubtotal);
                return;
            }
        }

        // Si no existe, crear nuevo item
        ItemCarrito nuevo = new ItemCarrito();
        nuevo.setId(id);
        nuevo.setNombre(nombre);
        nuevo.setCantidad(cantidad);
        nuevo.setPrecio(precio);
        nuevo.setTipo(tipo);
        nuevo.setImagen_url(imagen_url);
        nuevo.setColor(color);
        nuevo.setSubtotal(subtotalCalculado); // Asegurar que subtotal no sea nulo
        
        items.add(nuevo);
    }

    public void addItem(Long id, String nombre, int cantidad, Double precio,
                        String tipo, String imagen_url) {
        addItem(id, nombre, cantidad, precio, tipo, imagen_url, null);
    }

    public void removeItem(Long id, String tipo) {
        items.removeIf(it -> it.getId().equals(id) && it.getTipo().equals(tipo));
    }

    public void removeItem(Long id) {
        items.removeIf(it -> it.getId().equals(id));
    }

    public void clear() {
        items.clear();
    }

    public int getCantidadTotal() {
        return items.stream()
                .mapToInt(item -> item.getCantidad())
                .sum();
    }

    public void actualizarCantidad(Long id, String tipo, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            removeItem(id, tipo);
            return;
        }
        
        for (ItemCarrito item : items) {
            if (item.getId().equals(id) && item.getTipo().equals(tipo)) {
                item.setCantidad(nuevaCantidad);
                if (item.getPrecio() != null) {
                    item.setSubtotal(item.getPrecio() * nuevaCantidad);
                } else {
                    item.setSubtotal(0.0);
                }
                break;
            }
        }
    }
    
    // Método auxiliar para inicializar subtotal en items existentes
    public void inicializarSubtotales() {
        for (ItemCarrito item : items) {
            if (item.getSubtotal() == null) {
                if (item.getPrecio() != null && item.getCantidad() > 0) {
                    item.setSubtotal(item.getPrecio() * item.getCantidad());
                } else {
                    item.setSubtotal(0.0);
                }
            }
        }
    }
}