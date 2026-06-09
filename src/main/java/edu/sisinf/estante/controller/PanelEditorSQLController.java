package edu.sisinf.estante.controller;

import edu.sisinf.estante.util.SqlValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller del panel editor SQL con resaltado de sintaxis básico.
 * Usa RichTextFX CodeArea para mostrar keywords SQL en azul,
 * strings en verde y comentarios en gris.
 */
public class PanelEditorSQLController {

    private static final String[] KEYWORDS = {
        "SELECT", "FROM", "WHERE", "JOIN", "ON", "GROUP BY", "ORDER BY",
        "HAVING", "INSERT", "UPDATE", "DELETE", "INTO", "VALUES", "SET",
        "CREATE", "DROP", "ALTER", "TABLE", "INDEX", "VIEW", "AND", "OR",
        "NOT", "NULL", "AS", "DISTINCT", "LIMIT", "OFFSET", "INNER",
        "LEFT", "RIGHT", "OUTER", "FULL", "CROSS", "UNION", "ALL"
    };

    private static final Pattern PATRON = Pattern.compile(
        "(?<KEYWORD>\\b(" + String.join("|", KEYWORDS) + ")\\b)"
        + "|(?<STRING>'[^']*')"
        + "|(?<COMMENT>--[^\n]*)",
        Pattern.CASE_INSENSITIVE
    );

    @FXML private Button btnEjecutar;
    @FXML private Button btnLimpiar;
    @FXML private Label labelConexionActiva;
    @FXML private CodeArea areaSQL;

    private Consumer<String> onEjecutar;

    /**
     * Inicializa el controller conectando los botones y el resaltado de sintaxis.
     */
    @FXML
    public void initialize() {
        btnLimpiar.setOnAction(e -> areaSQL.clear());
        btnEjecutar.setOnAction(e -> disparar());

        areaSQL.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && event.isControlDown()) {
                event.consume();
                disparar();
            }
        });

        // Resaltado de sintaxis en tiempo real
        areaSQL.textProperty().addListener((obs, oldText, newText) ->
            areaSQL.setStyleSpans(0, calcularResaltado(newText))
        );

        // CSS inline para los estilos
        areaSQL.getStylesheets().add(
            "data:text/css," +
            ".keyword { -fx-fill: #0000cc; -fx-font-weight: bold; }" +
            ".string  { -fx-fill: #008000; }" +
            ".comment { -fx-fill: #808080; -fx-font-style: italic; }"
        );
    }

    /**
     * Calcula los StyleSpans para resaltar keywords, strings y comentarios.
     */
    private StyleSpans<Collection<String>> calcularResaltado(String texto) {
        Matcher matcher = PATRON.matcher(texto);
        StyleSpansBuilder<Collection<String>> builder = new StyleSpansBuilder<>();
        int ultimo = 0;

        while (matcher.find()) {
            String estilo =
                matcher.group("KEYWORD") != null ? "keyword" :
                matcher.group("STRING")  != null ? "string"  :
                "comment";

            builder.add(Collections.emptyList(), matcher.start() - ultimo);
            builder.add(Collections.singleton(estilo), matcher.end() - matcher.start());
            ultimo = matcher.end();
        }

        builder.add(Collections.emptyList(), texto.length() - ultimo);
        return builder.create();
    }

    /**
     * Registra el callback que se invoca al ejecutar una query.
     */
    public void setOnEjecutar(Consumer<String> callback) {
        this.onEjecutar = callback;
    }

    /**
     * Actualiza el label de conexión activa.
     */
    public void setConexionActiva(String descripcion) {
        labelConexionActiva.setText(descripcion != null ? descripcion : "Sin conexión");
    }

    /**
     * Devuelve el texto actual del editor SQL.
     */
    public String getTextoSQL() {
        return areaSQL.getText();
    }

    /**
     * Dispara el callback si el texto no está vacío.
     */
    private void disparar() {
        String texto = areaSQL.getText();
        if (texto.isBlank() || onEjecutar == null) {
            return;
        }
        if (SqlValidator.esDestructiva(texto)) {
            boolean confirmar = DialogoConfirmacionDML.confirmar(texto);
            if (!confirmar) {
                return;
            }
        }
        onEjecutar.accept(texto);
    }
}