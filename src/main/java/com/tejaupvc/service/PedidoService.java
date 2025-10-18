package com.tejaupvc.service;

import com.tejaupvc.model.*;
import com.tejaupvc.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final ProductoRepository productoRepo; // <-- nuevo

    @Autowired
    public PedidoService(PedidoRepository pedidoRepo,
                         DetallePedidoRepository detalleRepo,
                         ProductoRepository productoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.detalleRepo = detalleRepo;
        this.productoRepo = productoRepo; // <-- nuevo
    }

    public Pedido crearPedido(Cliente cliente, List<CarritoItem> items) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        double total = items.stream().mapToDouble(CarritoItem::getSubtotal).sum();
        pedido.setTotal(total);

        pedidoRepo.save(pedido);

        // recorrer cada ítem del carrito
        for (CarritoItem item : items) {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);

            // recuperar el producto real desde la base de datos usando el ID del carrito
            Producto producto = productoRepo.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + item.getProductoId()));

            detalle.setProducto(producto); // ahora usamos setProducto(), no setProductoId()
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(item.getPrecioUnitario());

            detalleRepo.save(detalle);
        }

        return pedido;
    }
}