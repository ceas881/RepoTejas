package com.tejaupvc.repository;

import com.tejaupvc.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Métodos personalizados si los necesitas
    // List<Producto> findByDestacadoTrue();
}