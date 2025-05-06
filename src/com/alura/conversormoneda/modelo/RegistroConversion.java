package com.alura.conversormoneda.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroConversion {
    private final LocalDateTime fechaHora;
    private final String monedaOrigen;
    private final String monedaDestino;
    private final double cantidadOrigen;
    private final double cantidadDestino;
    private final double tasaConversion;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public RegistroConversion(LocalDateTime fechaHora, String monedaOrigen, String monedaDestino,
                              double cantidadOrigen, double cantidadDestino, double tasaConversion) {
        this.fechaHora = fechaHora;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadOrigen = cantidadOrigen;
        this.cantidadDestino = cantidadDestino;
        this.tasaConversion = tasaConversion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getCantidadOrigen() {
        return cantidadOrigen;
    }

    public double getCantidadDestino() {
        return cantidadDestino;
    }

    public double getTasaConversion() {
        return tasaConversion;
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s → %.2f %s (Tasa: %.6f)",
                fechaHora.format(FORMATO_FECHA),
                cantidadOrigen, monedaOrigen,
                cantidadDestino, monedaDestino,
                tasaConversion);
    }
}