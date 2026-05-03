package edu.sisinf.estante.core;

/**
 * Excepción personalizada para errores de base de datos.
 * Envuelve SQLException con contexto adicional.
 */
public class DatabaseException extends Exception {

    private final String code;
    private final Throwable originalCause;

    /**
     * Constructor que recibe mensaje y causa original.
     *
     * @param message       descripción del error
     * @param code          código identificador del error
     * @param originalCause excepción original que causó el error
     */
    public DatabaseException(String message, String code, Throwable originalCause) {
        super(message, originalCause);
        this.code = code;
        this.originalCause = originalCause;
    }

    public String getCode() {
        return code;
    }

    public Throwable getOriginalCause() {
        return originalCause;
    }
}