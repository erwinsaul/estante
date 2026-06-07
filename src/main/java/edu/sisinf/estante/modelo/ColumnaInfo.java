package edu.sisinf.estante.modelo;

public record ColumnaInfo(
        String nombre,
        String tipoSQL,
        boolean nullable,
        Integer tamano,      //"tamaño"
        String valorDefault
) {} 