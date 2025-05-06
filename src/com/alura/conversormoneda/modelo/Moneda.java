package com.alura.conversormoneda.modelo;

public class Moneda {
    private final String codigo;
    private final String nombre;

    public Moneda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}