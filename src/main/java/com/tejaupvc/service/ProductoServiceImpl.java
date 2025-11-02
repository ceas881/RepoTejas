package com.tejaupvc.service;

import org.springframework.stereotype.Service;
import com.tejaupvc.model.Producto;
import com.tejaupvc.repository.ProductoRepository;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerProductosDestacados() {
        return productoRepository.findTop5ByOrderByIdAsc();
    }
}
