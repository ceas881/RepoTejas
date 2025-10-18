/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tejaupvc.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
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
// Mensaje de contacto
@Entity @Table(name="mensajes_contacto")
public class MensajeContacto {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String nombre, correo, telefono;
  @Column(columnDefinition="TEXT") private String mensaje;
  private LocalDateTime fecha = LocalDateTime.now();
  /* getters/setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
  
}