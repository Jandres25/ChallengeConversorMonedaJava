package com.alura.conversormoneda.servicio;

import com.alura.conversormoneda.api.ConexionAPI;
import com.alura.conversormoneda.modelo.Moneda;
import com.alura.conversormoneda.modelo.RegistroConversion;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServicioConversion {
    private final ConexionAPI conexionAPI;
    private final List<Moneda> monedasDisponibles;
    private final LinkedList<RegistroConversion> historialConversiones;
    private final DecimalFormat formatoDecimal;
    private static final int MAX_HISTORIAL = 10; // Máximo número de conversiones a guardar

    public ServicioConversion() {
        this.conexionAPI = new ConexionAPI();
        this.monedasDisponibles = new ArrayList<>();
        this.historialConversiones = new LinkedList<>();
        this.formatoDecimal = new DecimalFormat("#,##0.00");

        // Inicializar las monedas disponibles
        inicializarMonedas();
    }

    private void inicializarMonedas() {
        // Las 6 monedas originales
        monedasDisponibles.add(new Moneda("ARS", "Peso argentino"));
        monedasDisponibles.add(new Moneda("BOB", "Boliviano"));
        monedasDisponibles.add(new Moneda("BRL", "Real brasileño"));
        monedasDisponibles.add(new Moneda("CLP", "Peso chileno"));
        monedasDisponibles.add(new Moneda("COP", "Peso colombiano"));
        monedasDisponibles.add(new Moneda("USD", "Dólar estadounidense"));

        // Añadir más monedas
        monedasDisponibles.add(new Moneda("EUR", "Euro"));
        monedasDisponibles.add(new Moneda("GBP", "Libra esterlina"));
        monedasDisponibles.add(new Moneda("JPY", "Yen japonés"));
        monedasDisponibles.add(new Moneda("CAD", "Dólar canadiense"));
        monedasDisponibles.add(new Moneda("AUD", "Dólar australiano"));
        monedasDisponibles.add(new Moneda("CNY", "Yuan chino"));
        monedasDisponibles.add(new Moneda("MXN", "Peso mexicano"));
        monedasDisponibles.add(new Moneda("PEN", "Sol peruano"));
        monedasDisponibles.add(new Moneda("UYU", "Peso uruguayo"));
    }

    /**
     * Obtiene la lista de monedas disponibles para conversión.
     *
     * @return Lista de monedas disponibles
     */
    public List<Moneda> getMonedasDisponibles() {
        return new ArrayList<>(monedasDisponibles);
    }

    /**
     * Obtiene el historial de conversiones realizadas.
     *
     * @return Lista de registros de conversiones
     */
    public List<RegistroConversion> getHistorialConversiones() {
        return new ArrayList<>(historialConversiones);
    }

    /**
     * Convierte un valor de una moneda a otra y registra la conversión.
     *
     * @param valor El valor a convertir
     * @param monedaOrigen Código de la moneda de origen
     * @param monedaDestino Código de la moneda de destino
     * @return El valor convertido formateado como String
     * @throws IOException Si hay un error en la comunicación con la API
     * @throws InterruptedException Si la operación es interrumpida
     */
    public String convertir(double valor, String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        double tasaConversion = conexionAPI.obtenerTasaConversion(monedaOrigen, monedaDestino);
        double resultado = valor * tasaConversion;

        // Registrar la conversión en el historial
        registrarConversion(monedaOrigen, monedaDestino, valor, resultado, tasaConversion);

        return formatoDecimal.format(resultado);
    }

    /**
     * Obtiene información detallada sobre una conversión.
     *
     * @param valor El valor a convertir
     * @param monedaOrigen Código de la moneda de origen
     * @param monedaDestino Código de la moneda de destino
     * @return Información detallada sobre la conversión
     * @throws IOException Si hay un error en la comunicación con la API
     * @throws InterruptedException Si la operación es interrumpida
     */
    public String obtenerInformacionConversion(double valor, String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        double tasaConversion = conexionAPI.obtenerTasaConversion(monedaOrigen, monedaDestino);
        double resultado = valor * tasaConversion;

        // Registrar la conversión en el historial
        registrarConversion(monedaOrigen, monedaDestino, valor, resultado, tasaConversion);

        String monedaOrigenNombre = obtenerNombreMoneda(monedaOrigen);
        String monedaDestinoNombre = obtenerNombreMoneda(monedaDestino);

        StringBuilder info = new StringBuilder();
        info.append(formatoDecimal.format(valor)).append(" ").append(monedaOrigenNombre);
        info.append(" = ");
        info.append(formatoDecimal.format(resultado)).append(" ").append(monedaDestinoNombre);
        info.append("\nTasa de conversión: 1 ").append(monedaOrigen);
        info.append(" = ").append(formatoDecimal.format(tasaConversion)).append(" ").append(monedaDestino);

        return info.toString();
    }

    /**
     * Registra una conversión en el historial.
     */
    private void registrarConversion(String monedaOrigen, String monedaDestino,
                                     double cantidadOrigen, double cantidadDestino,
                                     double tasaConversion) {
        // Crear el registro con la fecha y hora actual
        RegistroConversion registro = new RegistroConversion(
                LocalDateTime.now(),
                monedaOrigen,
                monedaDestino,
                cantidadOrigen,
                cantidadDestino,
                tasaConversion
        );

        // Añadir al inicio de la lista
        historialConversiones.addFirst(registro);

        // Mantener el historial limitado
        if (historialConversiones.size() > MAX_HISTORIAL) {
            historialConversiones.removeLast();
        }
    }

    /**
     * Obtiene el nombre completo de una moneda a partir de su código.
     *
     * @param codigoMoneda El código de la moneda
     * @return El nombre completo de la moneda
     */
    public String obtenerNombreMoneda(String codigoMoneda) {
        for (Moneda moneda : monedasDisponibles) {
            if (moneda.getCodigo().equals(codigoMoneda)) {
                return moneda.getNombre();
            }
        }
        return codigoMoneda;
    }
}