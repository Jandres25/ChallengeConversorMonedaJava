package com.alura.conversormoneda.ui;

import com.alura.conversormoneda.modelo.Moneda;
import com.alura.conversormoneda.modelo.RegistroConversion;
import com.alura.conversormoneda.servicio.ServicioConversion;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsolaCLI {
    private final Scanner scanner;
    private final ServicioConversion servicioConversion;
    private boolean ejecutando;

    public ConsolaCLI() {
        this.scanner = new Scanner(System.in);
        this.servicioConversion = new ServicioConversion();
        this.ejecutando = true;
    }

    /**
     * Inicia la interfaz de línea de comandos.
     */
    public void iniciar() {
        mostrarBienvenida();

        while (ejecutando) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            if (opcion >= 1 && opcion <= servicioConversion.getMonedasDisponibles().size()) {
                procesarConversion(opcion - 1);
            } else if (opcion == servicioConversion.getMonedasDisponibles().size() + 1) {
                mostrarHistorial();
            } else if (opcion == 0) {
                terminar();
            } else {
                mostrarError("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    /**
     * Muestra un mensaje de bienvenida.
     */
    private void mostrarBienvenida() {
        System.out.println("================================");
        System.out.println("  CONVERSOR DE MONEDA - ALURA  ");
        System.out.println("================================");
        System.out.println("Bienvenido al conversor de monedas");
        System.out.println("Challenge ONE - Backend");
    }

    /**
     * Muestra el menú principal con las opciones de monedas.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n=== SELECCIONE UNA OPCIÓN ===");
        List<Moneda> monedas = servicioConversion.getMonedasDisponibles();

        for (int i = 0; i < monedas.size(); i++) {
            System.out.printf("%d. Convertir de %s\n", i + 1, monedas.get(i));
        }

        System.out.printf("%d. Ver historial de conversiones\n", monedas.size() + 1);
        System.out.println("0. Salir");
        System.out.print("\nElija una opción: ");
    }

    /**
     * Lee una opción del usuario desde la consola.
     *
     * @return La opción elegida como entero
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Procesa una solicitud de conversión.
     *
     * @param indiceMonedaOrigen El índice de la moneda de origen seleccionada
     */
    private void procesarConversion(int indiceMonedaOrigen) {
        List<Moneda> monedas = servicioConversion.getMonedasDisponibles();
        Moneda monedaOrigen = monedas.get(indiceMonedaOrigen);

        System.out.printf("\nConvirtiendo desde %s\n", monedaOrigen);
        System.out.print("Ingrese la cantidad a convertir: ");

        double cantidad;
        try {
            cantidad = Double.parseDouble(scanner.nextLine().trim());
            if (cantidad <= 0) {
                mostrarError("La cantidad debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarError("Cantidad inválida. Debe ingresar un número.");
            return;
        }

        mostrarMenuMonedaDestino(monedaOrigen);
        int opcionDestino = leerOpcion();

        if (opcionDestino < 1 || opcionDestino > monedas.size()) {
            mostrarError("Opción inválida.");
            return;
        }

        Moneda monedaDestino = monedas.get(opcionDestino - 1);

        try {
            String resultado = servicioConversion.obtenerInformacionConversion(
                    cantidad,
                    monedaOrigen.getCodigo(),
                    monedaDestino.getCodigo()
            );

            System.out.println("\n=== RESULTADO DE LA CONVERSIÓN ===");
            System.out.println(resultado);
        } catch (IOException | InterruptedException e) {
            mostrarError("Error al realizar la conversión: " + e.getMessage());
        }
    }

    /**
     * Muestra el historial de conversiones realizadas.
     */
    private void mostrarHistorial() {
        List<RegistroConversion> historial = servicioConversion.getHistorialConversiones();

        if (historial.isEmpty()) {
            System.out.println("\nNo hay conversiones registradas en el historial.");
            return;
        }

        System.out.println("\n=== HISTORIAL DE CONVERSIONES ===");
        for (int i = 0; i < historial.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, historial.get(i));
        }
    }

    /**
     * Muestra el menú para seleccionar la moneda de destino.
     *
     * @param monedaOrigen La moneda de origen seleccionada
     */
    private void mostrarMenuMonedaDestino(Moneda monedaOrigen) {
        System.out.println("\n=== SELECCIONE MONEDA DE DESTINO ===");
        List<Moneda> monedas = servicioConversion.getMonedasDisponibles();

        for (int i = 0; i < monedas.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, monedas.get(i));
        }

        System.out.print("Elija una opción: ");
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param mensaje El mensaje de error
     */
    private void mostrarError(String mensaje) {
        System.out.println("\n¡ERROR! " + mensaje);
    }

    /**
     * Termina la ejecución del programa.
     */
    private void terminar() {
        this.ejecutando = false;
        System.out.println("\n¡Gracias por usar nuestro conversor de monedas!");
        System.out.println("Programa desarrollado para Challenge ONE - Backend");
    }
}