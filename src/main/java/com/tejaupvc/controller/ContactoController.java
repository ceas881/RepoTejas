package com.tejaupvc.controller;

import com.tejaupvc.model.MensajeContacto;
import com.tejaupvc.repository.MensajeContactoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin
public class ContactoController {

    private static final Logger logger = LoggerFactory.getLogger(ContactoController.class);

    @Autowired
    private MensajeContactoRepository repo;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${contact.to:}")
    private String contactTo;

    @PostMapping
    public MensajeContacto enviar(@RequestBody MensajeContacto mensaje) {

        if (mensaje.getNombre() == null || mensaje.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (mensaje.getCorreo() == null || mensaje.getCorreo().isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        if (mensaje.getMensaje() == null || mensaje.getMensaje().isBlank()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }

        MensajeContacto guardado = repo.save(mensaje);

        if (mailSender != null && !contactTo.isBlank()) {
            try {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(contactTo);
                mail.setSubject("Nuevo contacto – Teja UPVC");
                mail.setText(
                        "Nombre: " + mensaje.getNombre() + "\n" +
                        "Correo: " + mensaje.getCorreo() + "\n" +
                        "Teléfono: " + mensaje.getTelefono() + "\n\n" +
                        "Mensaje:\n" + mensaje.getMensaje()
                );

                mailSender.send(mail);
                logger.info("Correo enviado correctamente.");

            } catch (Exception ex) {
                logger.error("No se pudo enviar correo: {}", ex.getMessage());
            }
        } else {
            logger.warn("Mail deshabilitado. Mensaje guardado sin enviar correo.");
        }

        return guardado;
    }
}
