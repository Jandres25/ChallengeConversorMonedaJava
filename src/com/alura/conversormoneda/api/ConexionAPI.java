package com.alura.conversormoneda.api;

import com.alura.conversormoneda.modelo.TasaCambio;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionAPI {
    // Reemplazar con tu API key real
    private static final String API_KEY = "d7dc184ba70354107adee163";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private final HttpClient httpClient;
    private final Gson gson;

    public ConexionAPI() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    /**
     * Realiza una solicitud HTTP a la API para obtener las tasas de cambio.
     *
     * @param monedaBase El código de la moneda base para la conversión
     * @return Objeto TasaCambio con la respuesta de la API
     * @throws IOException Si hay un error en la comunicación HTTP
     * @throws InterruptedException Si la operación es interrumpida
     */
    public TasaCambio obtenerTasasDeCambio(String monedaBase) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + monedaBase))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar el código de estado HTTP
        if (response.statusCode() != 200) {
            throw new IOException("Error en la solicitud HTTP: " + response.statusCode());
        }

        // Convertir la respuesta JSON a objeto TasaCambio
        return gson.fromJson(response.body(), TasaCambio.class);
    }

    /**
     * Obtiene la tasa de conversión entre dos monedas.
     *
     * @param monedaOrigen Código de la moneda de origen
     * @param monedaDestino Código de la moneda de destino
     * @return La tasa de conversión
     * @throws IOException Si hay un error en la comunicación HTTP
     * @throws InterruptedException Si la operación es interrumpida
     */
    public double obtenerTasaConversion(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        TasaCambio tasaCambio = obtenerTasasDeCambio(monedaOrigen);

        // Verificar si la solicitud fue exitosa
        if (!"success".equals(tasaCambio.result())) {
            throw new IOException("Error en la respuesta de la API: " + tasaCambio.result());
        }

        // Obtener la tasa de conversión específica
        Double tasa = tasaCambio.conversion_rates().get(monedaDestino);
        if (tasa == null) {
            throw new IOException("No se encontró tasa de conversión para " + monedaDestino);
        }

        return tasa;
    }
}