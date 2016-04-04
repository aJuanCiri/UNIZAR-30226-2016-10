package trelligen.app.cine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Conecta con la base de datos, realiza consultas y devuelve resultados
 */
public class GestorDB implements Runnable {
    //Se define el nombre y la clave de usuario de la base de datos.
    private String pass="*";
    private String usr="*";
    //Se define la url de la base a la que se quiere conectar.
    private String url ="jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious";
    //String que contiene el texto de consultas a realizar
    private String consulta;
    //Referencia la conexion con la Base de Datos
    private Connection conex;
    //Contendra los resultados devueltos por las consultas
    private ResultSet rst;
    //Indica si se va a realizar una consulta o se va realizar la conexion
    private int accion = 1;

    public GestorDB() {}

    /**
     * Conecta con la base de datos.
     */
    public void getConex() {
        accion = 1;
        try {
            Thread t = new Thread(this);
            t.start();
            t.join();
        } catch (Exception e) {

        }
    }

    /**
     * Devuelve el resultado de la consulta [consulta].
     */
    public ResultSet getRst(String consulta) {
        this.consulta = consulta;
        accion = 0;
        try {
                Thread t = new Thread(this);
                t.start();
                t.join();
        } catch (Exception e) {
            return null;
        }
        return rst;
    }

    /**
     * Realiza acciones que no son consultas.
     */
    public boolean realiza(String consulta) {
        this.consulta = consulta;
        accion = 3;
        try {
            Thread t = new Thread(this);
            t.start();
            t.join();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Conecta o realiza una consulta a la base de datos en funcion de [accion].
     */
    public void run() {
        if(accion == 0) {
            try {
                //Se realiza la consulta.
                Statement stmt = conex.createStatement();
                rst = stmt.executeQuery(consulta);
            } catch (Exception e) {

            }
        } else if(accion == 1) {
            //Se define el driver que se va a utilizar.
            String driver = "oracle.jdbc.driver.OracleDriver";
            try {
                //Se conecta a la base mediante el driver.
                Class.forName(driver).newInstance();
                conex= DriverManager.getConnection(url, usr, pass);
            } catch (Exception e) {
                e.toString();
            }
        } else if (accion == 3) {
            try {
                //Se realiza la consulta.
                Statement stmt = conex.createStatement();
                stmt.executeUpdate(consulta);
            } catch (Exception e) {

            }
        }
    }
}
