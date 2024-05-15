package com.unialex.libreryImproved.modelos;

public class Usuario {
    private String nombre;
    private String direccion;
    private long telefono;
    private String ced;
    public boolean prestoLibros;

    public Usuario(String nombre, String direccion, Long telefono, String ced) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ced=ced;
        this.prestoLibros = false;
    }

    public String getCed() {
        return ced;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
