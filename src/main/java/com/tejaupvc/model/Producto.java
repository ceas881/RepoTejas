package com.tejaupvc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos") // Ajusta según el nombre de tu tabla
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre") // Ajusta según tu BD
    private String nombre;
    
    @Column(name = "descripcion") // Ajusta según tu BD
    private String descripcion;
    
    @Column(name = "precio") // Ajusta según tu BD
    private Double precio;
    
    @Column(name = "imagen_url") // Ajusta según tu BD
    private String imagen_url;
    
    @Column(name = "color") // Campo color - ajusta según tu BD
    private String color;
    
    @Column(name = "tipo_cresta") // Campo tipo_cresta - ajusta según tu BD
    private String tipoCresta;
    
    @Column(name = "espesor") // Campo espesor - ajusta según tu BD
    private Double espesor;
    
    // Constructores
    public Producto() {}
    
    public Producto(String nombre, String descripcion, Double precio, 
                   String imagen_url, String color, String tipoCresta, Double espesor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen_url = imagen_url;
        this.color = color;
        this.tipoCresta = tipoCresta;
        this.espesor = espesor;
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
    
    // Nuevos getters y setters para los campos adicionales
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public String getTipoCresta() { return tipoCresta; }
    public void setTipoCresta(String tipoCresta) { this.tipoCresta = tipoCresta; }
    
    public Double getEspesor() { return espesor; }
    public void setEspesor(Double espesor) { this.espesor = espesor; }
}