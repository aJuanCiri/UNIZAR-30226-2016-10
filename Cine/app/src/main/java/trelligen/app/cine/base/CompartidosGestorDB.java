package trelligen.app.cine.base;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Datos comunes entre el gestor de la base de datos y su proceso encargado de realizar las operaciones
 */
public class CompartidosGestorDB {
    //String que contiene el texto de consultas a realizar
    private String consulta;
    //Referencia la conexion con la Base de Datos
    private Connection conex;
    //Contendra los resultados devueltos por las consultas
    private ResultSet rst;
    //Indica si se va a realizar una consulta o se va realizar la conexion
    private int accion = 1;
    private String url,usr,pass;

    public CompartidosGestorDB(String url, String usr, String pass){
        this.url=url;
        this.usr=usr;
        this.pass=pass;
    }

    public void setConsulta(String consulta){
        this.consulta = consulta;
    }

    public void setConex(Connection conex){
        this.conex = conex;
    }

    public void setRst(ResultSet rst){
        this.rst = rst;
    }

    public void setAccion(int accion){
        this.accion = accion;
    }

    public String getConsulta(){
        return consulta;
    }

    public Connection getConex(){
        return conex;
    }

    public ResultSet getRst(){
        return rst;
    }

    public int getAccion(){
        return accion;
    }

    public String getUsr(){
        return usr;
    }

    public String getUrl(){
        return url;
    }

    public String getPass(){
        return pass;
    }

}