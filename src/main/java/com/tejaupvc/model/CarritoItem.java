package com.tejaupvc.model;

public class CarritoItem {

    private Long id;
    private String nombre;
    private int cantidad;
    private double precio;
    private String tipo;
    private String imagen_url;
    private String color;

    public CarritoItem() {}

    public CarritoItem(Long id, String nombre, int cantidad, double precio,
                       String tipo, String imagen_url, String color) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen_url = imagen_url;
        this.color = color;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public String getTipo() { return tipo; }
    public String getImagen_url() { return imagen_url; }
    public String getColor() { return color; }

    public double getSubtotal() { return cantidad * precio; }
}
