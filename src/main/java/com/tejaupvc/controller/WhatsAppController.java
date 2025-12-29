package com.tejaupvc.controller;

import com.tejaupvc.model.CarritoItem;
import com.tejaupvc.session.CarritoSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class WhatsAppController {

    @GetMapping("/whatsapp")
    public String enviar(HttpSession session) throws Exception {

        CarritoSession carrito = (CarritoSession) session.getAttribute("CARRITO");
        if (carrito == null || carrito.getItems().isEmpty()) {
            return "redirect:/carrito";
        }

        StringBuilder sb = new StringBuilder("PEDIDO:\n");

        for (CarritoItem i : carrito.getItems()) {
            sb.append("- ").append(i.getNombre())
              .append(" | Cantidad: ").append(i.getCantidad()).append("\n");
        }

        String texto = URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);
        return "redirect:https://wa.me/573213537875?text=" + texto;
    }
}