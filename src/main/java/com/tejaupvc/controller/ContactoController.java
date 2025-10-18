/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tejaupvc.controller;

import com.tejaupvc.model.MensajeContacto;
import com.tejaupvc.repository.MensajeContactoRepository;
import jakarta.persistence.*;
// Controladores
import org.springframework.web.bind.annotation.*;
// Servicios
import org.springframework.stereotype.Service;
// Inyección de dependencias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


/**
 *
 * @author AMAF
 */
@RestController @RequestMapping("/api/contacto") @CrossOrigin
public class ContactoController {
  @Autowired MensajeContactoRepository repo; @Autowired JavaMailSender mailSender;
  @Value("${contact.to}") String contactTo;

  @PostMapping
  public MensajeContacto enviar(@RequestBody MensajeContacto m) {
    var saved = repo.save(m);
    try {
      SimpleMailMessage msg = new SimpleMailMessage();
      msg.setTo(contactTo); msg.setSubject("Nuevo contacto Web");
      msg.setText("Nombre: " + m.getNombre() + "\nCorreo: " + m.getCorreo() +
                  "\nTel: " + m.getTelefono() + "\n\n" + m.getMensaje());
      mailSender.send(msg);
    } catch (Exception ignored) {}
    return saved;
  }
}