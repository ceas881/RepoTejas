package com.tejaupvc.model;

public class ItemCarrito {
    private Long id;
    private String nombre;
    private String tipo;
    private Double precio;
    private int cantidad;
    private Double subtotal;
    private String imagen_url;
    private String color; // ← Agregar campo color

    // Constructores
    public ItemCarrito() {}
    
    public ItemCarrito(Long id, String nombre, String tipo, Double precio, 
                      int cantidad, String imagen_url, String color) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen_url = imagen_url;
        this.color = color;
        this.subtotal = precio * cantidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    
    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String imagen_url) { this.imagen_url = imagen_url; }
    
    public String getColor() { return color; } // ← Getter para color
    public void setColor(String color) { this.color = color; } // ← Setter para color
}