package com.tejaupvc.repository;

import com.tejaupvc.model.Accesorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // ¡IMPORTANTE! Esta anotación es clave
public interface AccesorioRepository extends JpaRepository<Accesorio, Long> {
    // Puedes añadir métodos personalizados aquí si los necesitas
}