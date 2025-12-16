package com.tejaupvc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accesorios") // Ajusta al nombre de tu tabla
public class Accesorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;
    private Double precio;
    private String color;
    
    @Column(name = "imagen_url")
    private String imagen_url;
    
    // Constructores
    public Accesorio() {}
    
    public Accesorio(String nombre, String descripcion, Double precio, String imagen_url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen_url = imagen_url;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String imagen_url) { this.imagen_url = imagen_url; }
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}