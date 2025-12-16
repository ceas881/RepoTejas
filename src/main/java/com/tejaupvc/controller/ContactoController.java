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

    @Autowired
    private JavaMailSender mailSender;

    @Value("${contact.to}")
    private String contactTo;

    @PostMapping
    public MensajeContacto enviar(@RequestBody MensajeContacto mensaje) {

        // VALIDACIÓN BÁSICA
        if (mensaje.getNombre() == null || mensaje.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (mensaje.getCorreo() == null || mensaje.getCorreo().isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        if (mensaje.getMensaje() == null || mensaje.getMensaje().isBlank()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }

        // GUARDAR EN BASE DE DATOS
        MensajeContacto guardado = repo.save(mensaje);

        // ENVÍO DE CORREO
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(contactTo);
            mail.setSubject("Nuevo contacto desde la Web – Teja UPVC Bogotá");
            mail.setText(
                "Nuevo mensaje de contacto:\n\n" +
                "Nombre: " + mensaje.getNombre() + "\n" +
                "Correo: " + mensaje.getCorreo() + "\n" +
                "Teléfono: " + mensaje.getTelefono() + "\n\n" +
                "Mensaje:\n" + mensaje.getMensaje()
            );

            mailSender.send(mail);
            logger.info("Correo enviado correctamente a {}", contactTo);

        } catch (Exception ex) {
            logger.error("Error enviando correo: {}", ex.getMessage());
        }

        return guardado;
    }
}