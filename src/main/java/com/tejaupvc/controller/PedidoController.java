/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tejaupvc.controller;

import com.tejaupvc.model.Cliente;
import com.tejaupvc.model.DetallePedido;
import com.tejaupvc.model.Pedido;
import com.tejaupvc.repository.AccesorioRepository;
import com.tejaupvc.repository.ClienteRepository;
import com.tejaupvc.repository.DetallePedidoRepository;
import com.tejaupvc.repository.PedidoRepository;
import com.tejaupvc.repository.ProductoRepository;

import java.util.List;
import java.util.Map;

// Controladores
import org.springframework.web.bind.annotation.*;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AMAF
 */
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepo;

    @Autowired
    ClienteRepository clienteRepo;

    @Autowired
    ProductoRepository prodRepo;

    @Autowired
    AccesorioRepository accRepo;

    @Autowired
    DetallePedidoRepository detRepo;

    // 👇 NUEVO: GET para ver desde navegador
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    @PostMapping
    public Pedido crear(@RequestBody Map<String, Object> body) {
        var cdata = (Map<String, Object>) body.get("cliente");
        var items = (List<Map<String, Object>>) body.get("items");
        double total = Double.parseDouble(body.getOrDefault("total", 0).toString());

        Cliente c = new Cliente();
        c.setNombre((String) cdata.get("nombre"));
        c.setTelefono((String) cdata.get("telefono"));
        c.setCorreo((String) cdata.get("correo"));
        c.setDireccion((String) cdata.get("direccion"));
        c = clienteRepo.save(c);

        Pedido p = new Pedido();
        p.setCliente(c);
        p.setTotal(total);
        p = pedidoRepo.save(p);

        for (var it : items) {
            DetallePedido d = new DetallePedido();
            d.setPedido(p);

            if (it.get("productoId") != null) {
                prodRepo.findById(Long.valueOf(it.get("productoId").toString()))
                        .ifPresent(d::setProducto);
            }

            if (it.get("accesorioId") != null) {
                accRepo.findById(Long.valueOf(it.get("accesorioId").toString()))
                        .ifPresent(d::setAccesorio);
            }

            d.setCantidad(Integer.valueOf(it.getOrDefault("cantidad", 1).toString()));
            d.setPrecioUnitario(Double.valueOf(it.getOrDefault("precioUnitario", 0).toString()));
            detRepo.save(d);
        }

        return p;
    }

    @PatchMapping("/{id}/estado")
    public Pedido estado(@PathVariable Long id, @RequestParam String estado) {
        return pedidoRepo.findById(id)
                .map(p -> {
                    p.setEstado(estado);
                    return pedidoRepo.save(p);
                })
                .orElse(null);
    }
}