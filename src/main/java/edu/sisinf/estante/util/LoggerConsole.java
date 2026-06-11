package edu.sisinf.estante.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger de consola con niveles básicos.
 * Implementa el patrón Singleton.
 */
public class LoggerConsole {

    private static LoggerConsole instancia;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");

    private LoggerConsole() {}

    public static LoggerConsole getInstancia() {
        if (instancia == null) {
            instancia = new LoggerConsole();
        }
        return instancia;
    }

    public static void info(String mensaje) {
        System.out.println(ahora() + " [INFO] " + mensaje);
    }

    public static void warn(String mensaje) {
        System.out.println(ahora() + " [WARN] " + mensaje);
    }

    public static void error(String mensaje) {
        System.out.println(ahora() + " [ERROR] " + mensaje);
    }

    private static String ahora() {
        return "[" + LocalTime.now().format(FORMATO) + "]";
    }
}