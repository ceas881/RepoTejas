package com.tejaupvc.controller;

import com.tejaupvc.model.ItemCarrito;
import com.tejaupvc.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

@Controller
public class WhatsAppController {

    @Autowired
    private CarritoService carritoService;
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @GetMapping("/whatsapp")
    public String enviarWhatsApp() {
        StringBuilder sb = new StringBuilder();
        
        // Encabezado del mensaje
        sb.append("¡Hola!\n");
        sb.append("PEDIDO DESDE LA WEB\n");
        sb.append("Estoy interesado en los siguientes productos: \n");
        
        // Formateador para precios
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        
        // Construir lista de productos
        int contador = 1;
        double totalGeneral = 0.0;
        
        for (ItemCarrito item : carritoService.getItems()) {
            sb.append("").append(contador).append(". ").append(item.getNombre()).append("");
            
            // Mostrar tipo
           // sb.append("   📋 *Tipo:* ").append(item.getTipo()).append("");
            
            // DEBUG: Verificar qué contiene el color
            System.out.println("DEBUG WhatsApp - Item: " + item.getNombre() + 
                              ", Color: " + item.getColor() + 
                              ", Color null? " + (item.getColor() == null) + 
                              ", Color empty? " + (item.getColor() != null && item.getColor().isEmpty()));
            
            // Mostrar color SI ESTÁ DISPONIBLE Y NO ESTÁ VACÍO
            if (item.getColor() != null && !item.getColor().trim().isEmpty()) {
                sb.append("\n*Color:* ").append(item.getColor().trim()).append("\n");
            }
            
            sb.append("*Cantidad:* ").append(item.getCantidad()).append("\n");
            //sb.append("   💰 *Precio unitario:* $").append(formatter.format(item.getPrecio())).append("%0A");
            //sb.append("   💵 *Subtotal:* $").append(formatter.format(item.getSubtotal())).append("%0A");
            
            // Agregar enlace a la imagen si existe
            if (item.getImagen_url() != null && !item.getImagen_url().trim().isEmpty()) {
                // Asegurar que la URL comience con /
                String imagenPath = item.getImagen_url().trim();
                if (!imagenPath.startsWith("/")) {
                    imagenPath = "/" + imagenPath;
                }
                String imagenUrlCompleta = baseUrl + imagenPath;
                //sb.append("   🖼️ *Imagen:* ").append(imagenUrlCompleta).append("");
            }
            
            sb.append("");
            totalGeneral += item.getSubtotal();
            contador++;
        }
        
        // Resumen del pedido
        sb.append("——————————————");
        sb.append("*RESUMEN DEL PEDIDO*");
        sb.append("——————————————");
        sb.append("\n*Productos diferentes:* ").append(carritoService.getItems().size()).append("\n");
        sb.append("*Total de unidades:* ").append(carritoService.getCantidadTotal()).append("\n");
        sb.append("——————————————");
        //sb.append("💳 *TOTAL A PAGAR:* $").append(formatter.format(totalGeneral)).append("*%0A%0A");
        
        // Información de contacto
        sb.append("\n*Información de contacto:\n");
        //sb.append("(Solicitar datos para completar el pedido\n)");
        
        sb.append("¡Quedo atento a su respuesta!\n");
        sb.append("¡Gracias!");
        
        String texto = sb.toString();
        System.out.println("DEBUG WhatsApp - Mensaje completo:");
        System.out.println(texto.replace("", "\n"));
        
        String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8);
        String telefono = "573213537875";
        String url = "https://wa.me/" + telefono + "?text=" + textoCodificado;
        
        return "redirect:" + url;
    }
}