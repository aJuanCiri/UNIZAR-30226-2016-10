package trelligen.app.cine.objeto;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import trelligen.app.cine.base.GestorDB;

/**
 * Clase que encapsula una serie de información vista como un cursor.
 */
public class CursorPelicula implements Runnable {
    private ResultSet rst;  // Resultado de una consulta.
    private ArrayList<Pelicula> array;  // Array de películas.
    private String titulo = "";  // Título de la película.
    private String director = ""; // Director de la pelicula.
    private GestorDB gestordb;  // Conexión con la base de datos.
    // Consulta a realizar.
    private String consulta = "SELECT categoria FROM Es WHERE pelicula=";

    /*
    * Constructor del objeto.
     */
    public CursorPelicula(ResultSet rst,GestorDB gestordb) {
        array = new ArrayList<Pelicula>();
        this.rst = rst;
        this.gestordb = gestordb;
    }

    /*
    * Constructor del objeto.
     */
    public CursorPelicula(ResultSet rst,GestorDB gestordb,String titulo,String director) {
        array = new ArrayList<Pelicula>();
        this.rst = rst;
        this.gestordb = gestordb;
        this.titulo = titulo.toLowerCase();
        this.director = director.toLowerCase();
    }

    /*
    * Devuelve el array de peliculas.
     */
    public ArrayList<Pelicula> getArray(){
        return array;
    }

    /*
    * Método run que se ejecuta al lanzar un proceso de este objeto.
     */
    public void run() {
        int id;
        int anterior = -1;
        ArrayList<String> genero = null;
        try {
            String tituloRST;
            String directorRST;
            while(rst.next()) { // Bucle que transforma los resultados en películas.
                tituloRST = rst.getString(2).toLowerCase();
                directorRST = rst.getString(4).toLowerCase();
                if(tituloRST.contains(titulo) && directorRST.contains(director)) {
                    id = rst.getInt(1);
                    ResultSet generoRst = gestordb.getRst(consulta+id);
                    genero = new ArrayList<String>();
                    while(generoRst.next()) {   // Bucle que obtiene los géneros.
                        genero.add(generoRst.getString(1));
                    }
                    array.add(new Pelicula(id,rst.getString(2),
                            rst.getString(3), rst.getString(4),
                            rst.getString(7), rst.getInt(5),
                            rst.getDouble(6), genero,
                            rst.getString(8), rst.getString(9)));
                }
            }
            rst.close();
        } catch (SQLException e) {
            array = null;
        }
    }
}