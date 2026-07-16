# Cobertura de Tests

## Estado actual

Actualmente se identificó la siguiente clase con pruebas unitarias:

| Clase | Cobertura % | Estado |
|---------|---------|---------|
| Conexion | No disponible | Existe ConexionTest |

## Clases con cobertura menor al 50%

No fue posible determinar este dato debido a que no se encuentra disponible un reporte JaCoCo generado y publicado en el repositorio.

## Observaciones

Se identificó la existencia de pruebas unitarias para la clase Conexion mediante el archivo ConexionTest.java.

Para obtener los porcentajes reales de cobertura se requiere generar un reporte JaCoCo mediante:

```bash
mvn test jacoco:report
```

y analizar los resultados producidos por la herramienta.
