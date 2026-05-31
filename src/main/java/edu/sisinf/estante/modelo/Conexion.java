package edu.sisinf.estante.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa los datos de conexion necesarios para establecer
 * una sesion de base de datos.
 */
public class Conexion {

    private String id;
    private String nombre;
    private String host;
    private Integer puerto;
    private String basedatos;
    private String usuario;
    private String password;
    private TipoMotor tipoMotor;
    private boolean usarSSL = false;

    /**
     * Lista de etiquetas para organizar conexiones por categoría.
     * Ejemplos: "produccion", "desarrollo", "local", "cliente-X".
     * Por defecto es una lista vacía para compatibilidad con versiones anteriores.
     */
    private List<String> etiquetas = new ArrayList<>();

    public Conexion() {
        this.puerto = 3306;
    }

    public Conexion(String nombre, String host, Integer puerto,
                    String basedatos, String usuario, String password) {
        this.nombre   = nombre;
        this.host     = host;
        this.puerto   = puerto;
        this.basedatos = basedatos;
        this.usuario  = usuario;
        this.password = password;
    }

    // Getters y Setters
    public String getId()                  { return id; }
    public void setId(String id)           { this.id = id; }

    public String getNombre()              { return nombre; }
    public void setNombre(String nombre)   { this.nombre = nombre; }

    public String getHost()                { return host; }
    public void setHost(String host)       { this.host = host; }

    public Integer getPuerto()             { return puerto; }
    public void setPuerto(Integer puerto)  { this.puerto = puerto; }

    public String getBasedatos()           { return basedatos; }
    public void setBasedatos(String bd)    { this.basedatos = bd; }

    public String getUsuario()             { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword()                { return password; }
    public void setPassword(String password)   { this.password = password; }

    public TipoMotor getTipoMotor()             { return tipoMotor; }
    public void setTipoMotor(TipoMotor motor)   { this.tipoMotor = motor; }

    public boolean isUsarSSL()             { return usarSSL; }
    public void setUsarSSL(boolean ssl)    { this.usarSSL = ssl; }

    /**
     * Devuelve la lista de etiquetas de la conexión.
     * @return lista de etiquetas, nunca null.
     */
    public List<String> getEtiquetas() {
        return etiquetas;
    }

    /**
     * Establece la lista de etiquetas de la conexión.
     * @param etiquetas lista de etiquetas.
     */
    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas != null ? etiquetas : new ArrayList<>();
    }

    @Override
    public String toString() {
        return nombre + "@" + host + ":" + puerto + "/" + basedatos;
    }
}