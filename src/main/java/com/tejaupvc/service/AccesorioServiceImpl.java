package com.tejaupvc.service;

import com.tejaupvc.model.Accesorio;
import com.tejaupvc.repository.AccesorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccesorioServiceImpl implements AccesorioService {
    
    private final AccesorioRepository accesorioRepository;
    
    @Autowired  // Puedes usar @Autowired en el constructor
    public AccesorioServiceImpl(AccesorioRepository accesorioRepository) {
        this.accesorioRepository = accesorioRepository;
    }
    
    @Override
    public Accesorio getAccesorioById(Long id) {
        Optional<Accesorio> optional = accesorioRepository.findById(id);
        return optional.orElse(null);
    }
    
    @Override
    public List<Accesorio> findAll() {
        return accesorioRepository.findAll();
    }
}