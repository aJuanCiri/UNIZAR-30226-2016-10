package trelligen.app.cine.base;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Datos comunes entre el gestor de la base de datos y su
 * proceso encargado de realizar las operaciones.
 */
public class CompartidosGestorDB {

    private String consulta; // String que contiene el texto de consultas a realizar.
    private Connection conex;  // Referencia la conexión con la Base de Datos.
    private ResultSet rst; // Contendrá los resultados devueltos por las consultas.
    // Indica si se va a realizar una consulta o se va realizar la conexión.
    private int accion = 1;
    private String url,usr,pass;

    /*
    * Constructor del objeto.
     */
    public CompartidosGestorDB(String url, String usr, String pass){
        this.url=url;
        this.usr=usr;
        this.pass=pass;
    }

    /*
    * Método que fija la consulta.
     */
    public void setConsulta(String consulta){
        this.consulta = consulta;
    }

    /*
    * Método que fija la conexión.
     */
    public void setConex(Connection conex){
        this.conex = conex;
    }

    /*
    * Método que fija los resultados de la consulta.
     */
    public void setRst(ResultSet rst){
        this.rst = rst;
    }

    /*
    * Método que fija la acción a realizar.
     */
    public void setAccion(int accion){
        this.accion = accion;
    }

    /*
    * Método que obtiene la consulta.
     */
    public String getConsulta(){
        return consulta;
    }

    /*
    * Método que obtiene la conexión.
     */
    public Connection getConex(){
        return conex;
    }

    /*
    * Método que obtiene los resultados.
     */
    public ResultSet getRst(){
        return rst;
    }

    /*
    * Método que obtiene la acción.
     */
    public int getAccion(){
        return accion;
    }

    /*
    * Método que obtiene el usuario.
     */
    public String getUsr(){
        return usr;
    }

    /*
    * Método que obtiene la url.
     */
    public String getUrl(){
        return url;
    }

    /*
    * Método que obtiene la pass.
     */
    public String getPass(){
        return pass;
    }

}