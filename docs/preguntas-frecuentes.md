# Preguntas Frecuentes — Estante

## ¿Qué es Estante?

Estante es un gestor de base de datos liviano desarrollado en Java que permite ejecutar consultas SQL sobre bases de datos MySQL de forma programática, sin depender de herramientas externas de administración.

## ¿Qué motor de base de datos usa Estante?

Estante utiliza **MySQL** como motor de base de datos. La conexión se configura mediante la clase `DBConfig`, que requiere host, puerto, nombre de base de datos, usuario y contraseña.

## ¿Cómo se instala Estante?

Para instalar y ejecutar Estante se necesita:

1. Tener instalado **Java 17** o superior.
2. Tener instalado **Maven 3.8** o superior.
3. Clonar el repositorio y ejecutar:

```bash
mvn install
```

4. Para correr los tests:

```bash
mvn test
```

## ¿Qué operaciones soporta Estante?

Estante soporta las siguientes operaciones SQL:

- **SELECT**: consultas de lectura que retornan un `QueryResult` con columnas y filas.
- **INSERT**: inserción de nuevos registros.
- **UPDATE**: actualización de registros existentes.
- **DELETE**: eliminación de registros.

La clase `SqlValidator` permite verificar el tipo de operación antes de ejecutarla.

## ¿Cómo se configura la conexión a la base de datos?

La conexión se configura usando la clase `DBConfig`:

```java
DBConfig config = new DBConfig(
    "localhost",  // host
    3306,         // puerto
    "mi_base",    // nombre de la BD
    "usuario",    // usuario
    "contraseña"  // contraseña
);
```

La URL JDBC se genera automáticamente con el método `toJdbcUrl()`.

## ¿Cómo se puede contribuir al proyecto?

Para contribuir a Estante se debe seguir el **Forking Workflow**:

1. Hacer un fork del repositorio original.
2. Crear una rama con el nombre del issue (por ejemplo `feat/nueva-funcionalidad`).
3. Realizar los cambios y hacer commit con mensajes descriptivos.
4. Abrir un Pull Request referenciando el issue con `Closes #N`.

Consultar el archivo `docs/flujo-de-trabajo.md` para más detalles.
