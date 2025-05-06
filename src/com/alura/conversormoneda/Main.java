package com.alura.conversormoneda;

import com.alura.conversormoneda.ui.ConsolaCLI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Conversor de Moneda - Challenge ONE");
        ConsolaCLI consola = new ConsolaCLI();
        consola.iniciar();
    }
}