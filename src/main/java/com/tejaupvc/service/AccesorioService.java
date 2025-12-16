package com.tejaupvc.service;

import com.tejaupvc.model.Accesorio;
import java.util.List;

public interface AccesorioService {
    
    Accesorio getAccesorioById(Long id);
    
    List<Accesorio> findAll();
}