package com.tejaupvc.controller;

import com.tejaupvc.model.Cliente;
import com.tejaupvc.model.DetallePedido;
import com.tejaupvc.model.Pedido;
import com.tejaupvc.repository.*;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ProductoRepository prodRepo;

    @Autowired
    private AccesorioRepository accRepo;

    @Autowired
    private DetallePedidoRepository detRepo;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    @PostMapping
    public Pedido crear(@RequestBody Map<String, Object> body) {

        if (body == null || !body.containsKey("cliente") || !body.containsKey("items")) {
            throw new IllegalArgumentException("Datos incompletos del pedido.");
        }

        Map<String, Object> cdata = (Map<String, Object>) body.get("cliente");
        List<Map<String, Object>> items = (List<Map<String, Object>>) body.get("items");
        double total = Double.parseDouble(body.getOrDefault("total", 0).toString());

        // Crear cliente
        Cliente c = new Cliente();
        c.setNombre((String) cdata.get("nombre"));
        c.setTelefono((String) cdata.get("telefono"));
        c.setCorreo((String) cdata.get("correo"));
        c.setDireccion((String) cdata.get("direccion"));
        c = clienteRepo.save(c);

        // Crear pedido
        Pedido p = new Pedido();
        p.setCliente(c);
        p.setTotal(total);
        p = pedidoRepo.save(p);

        // Agregar detalles del pedido
        for (Map<String, Object> it : items) {

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