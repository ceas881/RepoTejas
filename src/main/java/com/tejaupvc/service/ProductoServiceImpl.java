package com.tejaupvc.service;

import com.tejaupvc.model.Producto;
import com.tejaupvc.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    private final ProductoRepository productoRepository;
    
    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    @Override
    public Producto getProductoById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }
    
    @Override
    public List<Producto> obtenerProductosDestacados() {
        // Implementa la lógica real aquí
        // Ejemplo: return productoRepository.findByDestacadoTrue();
        return productoRepository.findAll(); // Temporal
    }
}