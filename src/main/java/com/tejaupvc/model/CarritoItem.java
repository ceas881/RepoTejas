package com.tejaupvc.model;

public class CarritoItem {
    private Long id;
    private String nombre;
    private int cantidad;
    private double precio;
    private String tipo; // "producto" o "accesorio"
    private String imagen_Url;

    public CarritoItem() {}

    public CarritoItem(Long id, String nombre, int cantidad, double precio, String tipo, String imagen_Url) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen_Url = imagen_Url;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public String getTipo() { return tipo; }
    public String getImagen_Url() { return imagen_Url; }

    public double getSubtotal() { return precio * cantidad; }
    
}
