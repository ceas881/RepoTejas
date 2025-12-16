package com.tejaupvc.service;

import com.tejaupvc.model.Producto;
import java.util.List;

public interface ProductoService {
    
    List<Producto> findAll();
    
    Producto getProductoById(Long id);
    
    List<Producto> obtenerProductosDestacados();
}