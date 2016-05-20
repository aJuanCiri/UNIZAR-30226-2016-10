package trelligen.app.cine.objeto;

import android.util.Log;

import java.sql.ResultSet;

/**
 * Clase que encapsula un proceso que será lanzado para comprobar
 * si existe un cierto resultado buscado en la base de datos.
 */
public class CursorObtieneResultados implements Runnable {

    private boolean encontrado = false; // Booleano encontrado.
    private ResultSet rst;  // Resultado de una consulta.

    /*
    * Constructor del objeto.
     */
    public CursorObtieneResultados(ResultSet rst) {
        this.rst = rst;
    }

    /*
    * Método que devuelve el valor del booleano encontrado.
     */
    public boolean getEncontrado() {
        return encontrado;
    }

    /*
    * Método run que se ejecuta al lanzar un thread de este objeto.
     */
    public void run() {
        try {
            encontrado = rst.next();
            rst.close();
        } catch (Exception e){
            e.toString();
        }
    }
}
