# Conversor de Monedas - Challenge ONE Back End

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Gson-4285F4?style=for-the-badge&logo=google&logoColor=white" alt="Gson">
  <img src="https://img.shields.io/badge/API-REST-009688?style=for-the-badge&logoColor=white" alt="API REST">
  <img src="https://img.shields.io/badge/Exchange_Rate_API-0FAAFF?style=for-the-badge&logoColor=white" alt="Exchange Rate API">
</div>

## 📝 Descripción

Este proyecto es parte del Challenge ONE Back End, donde se desarrolló un conversor de monedas utilizando Java que consume la API de ExchangeRate para obtener tasas de cambio en tiempo real.

El programa permite convertir entre diferentes monedas latinoamericanas (Peso argentino, Boliviano, Real brasileño, Peso chileno, Peso colombiano) y el Dólar estadounidense, utilizando datos actualizados de tasas de cambio.
## 🚀 Características principales

- Interfaz de consola intuitiva para una experiencia de usuario fluida
- Conexión con la API de ExchangeRate para obtener tasas de cambio actualizadas
- Soporte para múltiples monedas:
    - 🇦🇷 Peso argentino (ARS)
    - 🇧🇴 Boliviano (BOB)
    - 🇧🇷 Real brasileño (BRL)
    - 🇨🇱 Peso chileno (CLP)
    - 🇨🇴 Peso colombiano (COP)
    - 🇺🇸 Dólar estadounidense (USD)
- Visualización de tasas de cambio actuales entre las monedas seleccionadas
- Implementación usando principios sólidos de programación orientada a objetos

## ✨ Características adicionales

Este conversor de monedas incluye algunas funcionalidades adicionales para mejorar la experiencia de usuario:

- **Historial de Conversiones**: Guarda un registro de las últimas 10 conversiones realizadas, permitiendo al usuario revisar sus operaciones anteriores.
- **Amplio Soporte de Monedas**: Además de las monedas latinoamericanas básicas, el conversor soporta otras monedas internacionales como Euro, Libra esterlina, Yen japonés, entre otras.
- **Registros con Marca de Tiempo**: Cada conversión se guarda con la fecha y hora exacta en que fue realizada, facilitando el seguimiento de las operaciones.

## 🔧 Tecnologías utilizadas

- Java 21
- Biblioteca Gson para procesamiento de JSON
- HttpClient para realizar peticiones a la API
- Programación orientada a objetos con separación clara de responsabilidades
- Manejo de excepciones para una experiencia de usuario robusta

## 📋 Requisitos previos

Para ejecutar este proyecto, necesitarás:

- JDK 11 o superior
- Conexión a Internet (para comunicarse con la API)
- Una clave API de [ExchangeRate-API](https://www.exchangerate-api.com/)
- Biblioteca Gson

## ⚙️ Configuración e instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/TU_USUARIO/ChallengeConversorMonedaJava.git
   cd ChallengeConversorMonedaJava
   ```

2. **Añadir biblioteca Gson al proyecto**
    - Descarga la biblioteca Gson desde [Maven Repository](https://mvnrepository.com/artifact/com.google.code.gson/gson)
    - Añade el archivo JAR al classpath de tu proyecto

3. **Configurar la API Key**
    - Regístrate en [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener una API key gratuita
    - Abre el archivo `src/com/alura/conversormoneda/api/ConexionAPI.java`
    - Reemplaza `TU_API_KEY` con tu clave personal
   ```java
   private static final String API_KEY = "tu_clave_api_real";
   ```

4. **Compilar y ejecutar el proyecto**
    - Compila el proyecto con tu IDE favorito o desde línea de comandos
    - Ejecuta la clase `Main.java`

## 🖥️ Uso

1. Al iniciar el programa, se mostrará el menú principal con las monedas disponibles para conversión
2. Selecciona la moneda de origen ingresando el número correspondiente
3. Ingresa la cantidad que deseas convertir
4. Selecciona la moneda de destino
5. El programa mostrará el resultado de la conversión y la tasa de cambio actual
6. Puedes realizar más conversiones o salir del programa seleccionando "0"

## 🏗️ Estructura del proyecto

```
src/
└── com/
    └── alura/
        └── conversormoneda/
            ├── Main.java                          // Punto de entrada de la aplicación
            ├── modelo/
            │   ├── Moneda.java                    // Clase para representar monedas
            │   └── TasaCambio.java                // Record para respuesta de API
            ├── api/
            │   └── ConexionAPI.java               // Maneja las solicitudes HTTP
            ├── servicio/
            │   └── ServicioConversion.java        // Lógica de conversión
            └── ui/
                └── ConsolaCLI.java                // Interfaz de usuario en consola
```

## 🤝 Contribución

Las contribuciones son bienvenidas. Si tienes sugerencias para mejorar este proyecto:

1. Haz fork del repositorio
2. Crea una rama para tu característica (`git checkout -b feature/nueva-caracteristica`)
3. Haz commit de tus cambios (`git commit -m 'Añadir nueva característica'`)
4. Haz push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

## 👨‍💻 Autor

- [Jose Andres Meneces Lopez](https://github.com/Jandres25)

## 🙏 Agradecimientos

- [Alura Latam](https://www.aluracursos.com/) y [Oracle Next Education](https://www.oracle.com/mx/education/oracle-next-education/) por el challenge y la formación
- [ExchangeRate-API](https://www.exchangerate-api.com/) por proporcionar la API para las tasas de cambio

---

<div align="center">
  <p>Desarrollado con ❤️ como parte del programa Oracle Next Education - Alura</p>
</div>