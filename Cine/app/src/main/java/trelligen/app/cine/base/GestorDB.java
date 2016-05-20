package trelligen.app.cine.base;

import android.content.Context;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Conecta con la base de datos, realiza consultas y devuelve resultados
 */
public class GestorDB{
    private Context context;    // Ontexto de la base.
    private String configuracion = "oracle.properties"; // Configuración de la base.
    //Se define el nombre y la clave de usuario de la base de datos y la @basededatos
    private String pass,usr,url;
    private final int CONSULTA=1,CONEXION=0,UPDATE=2;   // Entero que indica el tipo de conexión.
    CompartidosGestorDB datos;  // Objeto de datos compartidos.

    /*
    * Constructor del objeto.
     */
    public GestorDB(Context context) {
        // Obtiene el contexto y propiedades.
        this.context = context;
        Properties propiedades = new Properties();
        try {
            // Lee los datos del usuario.
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

    /*
     * Método que conecta con la base de datos.
     */
    public void getConex() {
        datos.setAccion(CONEXION);  // Conecta con la base de datos.
        try {
            Thread t = new Thread(new ProcesoDB(datos));
            t.start();
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Devuelve el resultado de la consulta [consulta].
     */
    public ResultSet getRst(String consulta) {
        datos.setConsulta(consulta);    // Fija la consulta.
        datos.setAccion(CONSULTA);  // Realiza la consulta en la base.
        try {
            Thread t = new Thread(new ProcesoDB(datos));
                t.start();
                t.join();
        } catch (Exception e) {

        }
        return datos.getRst();  // Devuelve el resultado.
    }

    /*
     * Realiza acciones que no son consultas.
     */
    public boolean realiza(String consulta) {
        datos.setConsulta(consulta);    // Fija la sentencia sql.
        datos.setAccion(UPDATE);    // Realiza la actualiación.
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
