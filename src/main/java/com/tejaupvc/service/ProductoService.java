package com.tejaupvc.service;

import com.tejaupvc.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> obtenerProductosDestacados();
}