/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tejaupvc.controller;

import com.tejaupvc.model.Producto;
import com.tejaupvc.repository.ProductoRepository;
import java.util.List;
// Controladores
import org.springframework.web.bind.annotation.*;
// Servicios
import org.springframework.stereotype.Service;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author AMAF
 */
@RestController @RequestMapping("/api/productos") @CrossOrigin
public class ProductoController {
  @Autowired ProductoRepository repo;
  @GetMapping public List<Producto> all(){ return repo.findAll(); }
  @PostMapping public Producto create(@RequestBody Producto p){ return repo.save(p); }
  @GetMapping("/{id}") public Producto one(@PathVariable Long id){ return repo.findById(id).orElse(null); }
  @PutMapping("/{id}") public Producto update(@PathVariable Long id, @RequestBody Producto d){
    return repo.findById(id).map(p -> { p.setNombre(d.getNombre()); p.setDescripcion(d.getDescripcion());
      p.setPrecio(d.getPrecio()); p.setColor(d.getColor()); p.setTipoCresta(d.getTipoCresta());
      p.setEspesor(d.getEspesor()); p.setImagenUrl(d.getImagenUrl()); return repo.save(p); }).orElse(null);
  }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
