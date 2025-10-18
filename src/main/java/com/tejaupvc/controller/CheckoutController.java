package com.tejaupvc.controller;

import com.tejaupvc.model.Cliente;
import com.tejaupvc.model.Pedido;
import com.tejaupvc.service.CarritoService;
import com.tejaupvc.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    private final CarritoService carritoService;
    private final PedidoService pedidoService;

    public CheckoutController(CarritoService carritoService, PedidoService pedidoService) {
        this.carritoService = carritoService;
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody Cliente cliente) throws Exception {
        // 1) Guardar pedido en BD
        Pedido pedido = pedidoService.crearPedido(cliente, carritoService.getItems());
        carritoService.clear();

        // 2) Inicializar MercadoPago con tu ACCESS TOKEN
        MercadoPagoConfig.setAccessToken("TU_ACCESS_TOKEN_MERCADOPAGO");

        // 3) Crear ítem de pago
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title("Pedido #" + pedido.getId())
                .quantity(1)
                .unitPrice(new java.math.BigDecimal(pedido.getTotal()))
                .build();

        // 4) Crear preferencia de pago
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(java.util.List.of(item))
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        // 5) Retornar link de pago al frontend
        return ResponseEntity.ok(preference.getInitPoint());
    }
}
