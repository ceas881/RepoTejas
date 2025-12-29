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
            CarritoItem existente = items.get(k);
            existente.setCantidad(existente.getCantidad() + item.getCantidad());
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
}
