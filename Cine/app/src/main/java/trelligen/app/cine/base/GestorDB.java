package trelligen.app.cine.base;

import android.content.Context;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Conecta con la base de datos, realiza consultas y devuelve resultados
 */
public class GestorDB{
    private Context context;
    private String configuracion = "oracle.properties";
    //Se define el nombre y la clave de usuario de la base de datos y la @basededatos
    private String pass,usr,url;
    private final int CONSULTA=1,CONEXION=0,UPDATE=2;
    CompartidosGestorDB datos;

    public GestorDB(Context context) {
        this.context = context;
        Properties propiedades = new Properties();
        try {
            InputStream fichero =  context.getAssets().open(configuracion);
            propiedades.load(fichero);
            url = propiedades.getProperty("basedatos");
            usr = propiedades.getProperty("usuario");
            pass = propiedades.getProperty("contrasena");
            datos = new CompartidosGestorDB(
                    "jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious","a679184","hola1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getConex();
    }

    /**
     * Conecta con la base de datos.
     */
    public void getConex() {
        datos.setAccion(CONEXION);
        try {
            Thread t = new Thread(new ProcesoDB(datos));
            t.start();
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve el resultado de la consulta [consulta].
     */
    public ResultSet getRst(String consulta) {
        datos.setConsulta(consulta);
        datos.setAccion(CONSULTA);
        try {
            Thread t = new Thread(new ProcesoDB(datos));
                t.start();
                t.join();
        } catch (Exception e) {

        }
        return datos.getRst();
    }

    /**
     * Realiza acciones que no son consultas.
     */
    public boolean realiza(String consulta) {
        datos.setConsulta(consulta);
        datos.setAccion(UPDATE);
        try {
            Thread t = new Thread(new ProcesoDB(datos));
            t.start();
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}