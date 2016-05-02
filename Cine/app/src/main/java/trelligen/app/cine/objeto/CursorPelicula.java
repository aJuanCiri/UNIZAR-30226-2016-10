package trelligen.app.cine.objeto;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;

import trelligen.app.cine.base.GestorDB;

/**
 * Clase que encapsula una serie de información vista como un cursor.
 */
public class CursorPelicula implements Runnable {
    private ResultSet rst;
    private ArrayList<Pelicula> array;
    private String titulo;
    private GestorDB gestordb;
    private String consulta = "SELECT categoria FROM Es WHERE pelicula=";

    public CursorPelicula(ResultSet rst,GestorDB gestordb) {
        array = new ArrayList<Pelicula>();
        this.rst = rst;
        this.gestordb = gestordb;
        this.titulo = null;
    }

    public CursorPelicula(ResultSet rst,GestorDB gestordb,String titulo) {
        array = new ArrayList<Pelicula>();
        this.rst = rst;
        this.gestordb = gestordb;
        this.titulo = titulo.toLowerCase();
    }

    public ArrayList<Pelicula> getArray(){
        return array;
    }

    public void run() {
        int id;
        int anterior = -1;
        ArrayList<String> genero = null;
        if(titulo == null) {
            try {
                while(rst.next()) {
                    id = rst.getInt(1);
                    if(id != anterior) {
                        ResultSet generoRst = gestordb.getRst(consulta+id);
                        genero = new ArrayList<String>();
                        while(generoRst.next()) {
                            genero.add(generoRst.getString(1));
                        }
                        anterior = id;
                        array.add(new Pelicula(id,rst.getString(2),
                                rst.getString(3), rst.getString(4),
                                rst.getString(7), rst.getInt(5),
                                rst.getDouble(6), genero,
                                rst.getString(9), rst.getString(10)));
                    }
                }
                rst.close();
            } catch (SQLException e) {
                array = null;
            }
        } else {
            try {
                String tituloRST;
                while(rst.next()) {
                    tituloRST = rst.getString(2).toLowerCase();
                    if(tituloRST.contains(titulo)) {
                        id = rst.getInt(1);
                        if(id != anterior) {
                            ResultSet generoRst = gestordb.getRst(consulta+id);
                            genero = new ArrayList<String>();
                            while(generoRst.next()) {
                                genero.add(generoRst.getString(1));
                            }
                            anterior = id;
                            array.add(new Pelicula(id,rst.getString(2),
                                    rst.getString(3), rst.getString(4),
                                    rst.getString(7), rst.getInt(5),
                                    rst.getDouble(6), genero,
                                    rst.getString(9), rst.getString(10)));
                        }
                    }
                }
                rst.close();
            } catch (SQLException e) {
                array = null;
            }
        }
    }
}