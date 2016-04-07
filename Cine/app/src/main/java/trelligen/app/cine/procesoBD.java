package trelligen.app.cine;

import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Proceso encargado de realizar las operaciones del gestor de la base de datos
 */
public class procesoBD implements Runnable{
    private final int CONSULTA=1,CONEXION=0,UPDATE=2;
    private datosCompartidosGestorDB datos;

    public procesoBD(datosCompartidosGestorDB datos){
        this.datos = datos;
    }

    @Override
    public void run() {
        if(datos.getAccion()==CONSULTA) {
            try {
                //Se realiza la consulta.
                Statement stmt = datos.getConex().createStatement();
                datos.setRst(stmt.executeQuery(datos.getConsulta()));
            } catch (Exception e) {

            }
        } else if(datos.getAccion()== CONEXION) {
            //Se define el driver que se va a utilizar.
            String driver = "oracle.jdbc.driver.OracleDriver";
            try {
                //Se conecta a la base mediante el driver.
                Class.forName(driver).newInstance();
                datos.setConex(DriverManager.getConnection(datos.getUrl(), datos.getUsr(), datos.getPass()));
            } catch (Exception e) {
                e.toString();
            }
        } else if (datos.getAccion()== UPDATE) {
            try {
                //Se realiza la consulta.
                Statement stmt = datos.getConex().createStatement();
                stmt.executeUpdate(datos.getConsulta());
            } catch (Exception e) {

            }
        }
    }
}
