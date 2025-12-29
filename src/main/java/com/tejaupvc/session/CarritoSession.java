package com.tejaupvc.session;

import com.tejaupvc.model.CarritoItem;
import java.util.*;

public class CarritoSession {

    private Map<String, CarritoItem> items = new LinkedHashMap<>();

    private String key(Long id, String tipo) {
        return tipo + "_" + id;
    }

    public void agregar(CarritoItem item) {
        String k = key(item.getId(), item.getTipo());
        if (items.containsKey(k)) {
            items.get(k).setCantidad(items.get(k).getCantidad() + item.getCantidad());
        } else {
            items.put(k, item);
        }
    }

    public void eliminar(Long id, String tipo) {
        items.remove(key(id, tipo));
    }

    public void vaciar() {
        items.clear();
    }

    public Collection<CarritoItem> getItems() {
        return items.values();
    }

    public int getCantidadTotal() {
        return items.values().stream().mapToInt(CarritoItem::getCantidad).sum();
    }
}
