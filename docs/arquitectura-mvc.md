# Arquitectura MVC en Estante

## Patrón MVC en JavaFX

El proyecto **Estante** está desarrollado utilizando el patrón de arquitectura **Modelo-Vista-Controlador (MVC)** junto con JavaFX. Este patrón permite organizar la aplicación separando las responsabilidades de presentación, lógica de negocio y acceso a datos. Gracias a esta separación, el código es más fácil de mantener, extender y probar.

En JavaFX, la capa de presentación se implementa mediante archivos **FXML**, los cuales describen la interfaz gráfica. Los controladores reciben los eventos generados por el usuario y coordinan la interacción entre las vistas, los modelos y los servicios. Finalmente, el modelo representa las entidades y estructuras de información utilizadas por la aplicación.

La aplicación sigue un flujo donde el usuario interactúa con una vista, el controlador procesa la acción, solicita operaciones a los servicios y estos utilizan la capa DAO cuando es necesario acceder a la base de datos.

---

## Capa Modelo

La capa Modelo contiene las clases que representan la información manejada por la aplicación y las estructuras utilizadas durante las operaciones sobre bases de datos.

### Conexion

Representa una conexión configurada por el usuario hacia una base de datos. Contiene información como nombre, motor, host, puerto, usuario y credenciales necesarias para establecer la comunicación.

### Tabla

Representa una tabla existente dentro de un esquema de base de datos.

### Columna

Representa una columna perteneciente a una tabla.

### ColumnaInfo

Almacena información detallada sobre una columna, incluyendo características utilizadas durante la exploración de esquemas.

### Esquema

Representa un esquema o agrupación lógica de tablas dentro de una base de datos.

### FavoritoQuery

Representa una consulta SQL almacenada por el usuario para reutilización futura.

### ResultadoQuery

Representa el conjunto de resultados devuelto después de ejecutar una consulta SQL.

### EntradaHistorial

Representa un registro dentro del historial de consultas ejecutadas.

### ImportacionResultado

Representa el resultado generado durante procesos de importación de información.

### TipoMotor

Define los motores de bases de datos soportados por la aplicación.

---

## Capa Vista

La capa Vista está formada por los archivos FXML que describen la interfaz gráfica de usuario.

| Vista (FXML)              | Controlador Asociado           |
| ------------------------- | ------------------------------ |
| VentanaPrincipal.fxml     | VentanaPrincipalController     |
| DialogoNuevaConexion.fxml | DialogoNuevaConexionController |
| PanelArbolConexiones.fxml | PanelArbolConexionesController |
| PanelEditorSQL.fxml       | PanelEditorSQLController       |
| PanelEstadisticas.fxml    | PanelEstadisticasController    |
| PanelHistorial.fxml       | PanelHistorialController       |
| PanelInfoTabla.fxml       | PanelInfoTablaController       |
| PanelResultadoQuery.fxml  | PanelResultadoQueryController  |
| BarraEstado.fxml          | BarraEstadoController          |

Las vistas son responsables únicamente de mostrar información y capturar acciones realizadas por el usuario. No contienen lógica de negocio ni acceso directo a los datos.

---

## Capa Controlador

Los controladores actúan como intermediarios entre la interfaz gráfica y el resto de las capas de la aplicación.

### VentanaPrincipalController ↔ VentanaPrincipal.fxml

Controla la ventana principal y coordina la interacción entre los distintos paneles de la aplicación.

### DialogoNuevaConexionController ↔ DialogoNuevaConexion.fxml

Gestiona la creación, edición y validación de conexiones.

### PanelArbolConexionesController ↔ PanelArbolConexiones.fxml

Administra la visualización de conexiones, esquemas y tablas disponibles.

### PanelEditorSQLController ↔ PanelEditorSQL.fxml

Permite escribir, editar y ejecutar consultas SQL.

### PanelEstadisticasController ↔ PanelEstadisticas.fxml

Gestiona la visualización de estadísticas relacionadas con las consultas y bases de datos.

### PanelHistorialController ↔ PanelHistorial.fxml

Muestra y administra el historial de consultas ejecutadas.

### PanelInfoTablaController ↔ PanelInfoTabla.fxml

Presenta información detallada sobre tablas y columnas.

### PanelResultadoQueryController ↔ PanelResultadoQuery.fxml

Muestra los resultados obtenidos después de ejecutar consultas SQL.

### BarraEstadoController ↔ BarraEstado.fxml

Actualiza la información mostrada en la barra de estado de la aplicación.

---

## Capa DAO

La capa DAO (Data Access Object) encapsula el acceso a los distintos motores de bases de datos.

### IConexionDAO

Interfaz que define las operaciones necesarias para establecer conexiones y ejecutar operaciones sobre una base de datos.

### ConexionDAOMySQL

Implementación específica para el motor MySQL.

### ConexionDAOPostgreSQL

Implementación específica para PostgreSQL.

### ConexionDAOSQLite

Implementación específica para SQLite.

### IRepositorioConexiones

Interfaz responsable de la persistencia de conexiones configuradas.

### RepositorioConexionesJSON

Implementación que almacena la información de conexiones utilizando archivos JSON.

---

## Capa Servicio

La capa Servicio contiene la lógica de negocio utilizada por los controladores.

### EjecutorQuery

Ejecuta consultas SQL y procesa los resultados obtenidos.

### EjecutorQueryAsync

Permite ejecutar consultas de forma asíncrona sin bloquear la interfaz gráfica.

### ExploradorEsquemas

Obtiene información sobre esquemas, tablas y columnas disponibles.

### ExportadorCSV

Exporta resultados a formato CSV.

### ExportadorJSON

Exporta resultados a formato JSON.

### GeneradorCreateTable

Genera sentencias SQL CREATE TABLE a partir de la estructura de una tabla.

### GeneradorSQL

Genera consultas SQL auxiliares utilizadas por la aplicación.

### GestorFavoritos

Administra consultas favoritas guardadas por el usuario.

### HistorialQuerys

Gestiona el almacenamiento y recuperación del historial de consultas.

### ImportadorCSV

Permite importar información desde archivos CSV.

### ValidadorSQL

Valida consultas SQL antes de su ejecución.

---

## Flujo de ejecución de una consulta SQL

El siguiente diagrama muestra el flujo típico cuando un usuario ejecuta una consulta SQL:

```text
Usuario
   │
   ▼
PanelEditorSQL.fxml
   │
   ▼
PanelEditorSQLController
   │
   ▼
ValidadorSQL
   │
   ▼
EjecutorQuery
   │
   ▼
IConexionDAO
   │
   ▼
Motor de Base de Datos
   │
   ▼
ResultadoQuery
   │
   ▼
PanelResultadoQueryController
   │
   ▼
PanelResultadoQuery.fxml
```

En este proceso, el usuario escribe una consulta en la interfaz. El controlador recibe la solicitud, valida la consulta mediante los servicios correspondientes y delega la ejecución al componente encargado. Posteriormente, la capa DAO se comunica con el motor de base de datos, obtiene los resultados y los devuelve a la aplicación para ser mostrados en la vista correspondiente. Este flujo refleja la correcta separación de responsabilidades establecida por la arquitectura MVC implementada en Estante.
