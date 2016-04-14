package trelligen.app.cine.base;

import android.util.Log;

import java.sql.ResultSet;
import java.util.ArrayList;

import trelligen.app.cine.objeto.Cursor;
import trelligen.app.cine.objeto.Pelicula;

/**
 * Encapsula las consultas y funciones necesarias para la gestión de la base de datos de películas
 */
public class DBPelicula {
    private GestorDB gestordb;	//Gestor que conecta con la base de datos.
    private Pelicula pelicula;

    public DBPelicula(GestorDB gestorDB){
        this.gestordb = gestorDB;
    }

	/**
	 * Devuelve la información de una pelicula.
	 */
    public Pelicula getInformacion(int id){
		//Consulta a realizar
        String consulta = "SELECT p.id, p.titulo, p.fecha, p.director, p.duracion, " +
				"p.valoracion, p.sinopsis, c.nombre, pub.nombre FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e WHERE p.id="+id+" AND " +
				"p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre";
		//Obtiene el resultado de la consulta
        ResultSet resultado = gestordb.getRst(consulta);
		try {
			resultado.next();
			//Encapsula la informacion en la clase Pelicula.
			pelicula = new Pelicula(resultado.getInt(1),resultado.getString(2),
					resultado.getString(3), resultado.getString(4),
					resultado.getString(7), resultado.getInt(5),
					resultado.getDouble(6), resultado.getString(8),
					resultado.getString(9));

		} catch(Exception e) {
			Log.d("RST","Error al obtener el datos");
			//Si ocurre un error devuelve null.
		}
		//Devuelve la pelicula.
        return pelicula;
    }

	/**
	 * Asigna un nuevo titulo a la pelicula con el id [id].
	 */
    public boolean setTitulo(int id, String titulo){
		return gestordb.realiza("UPDATE Pelicula SET titulo="+titulo+" WHERE id="+id);
	}

	/**
	 * Asigna una nueva fecha a la pelicula con el id [id].
	 */
	public boolean setFecha(int id, String fecha){
		return gestordb.realiza("UPDATE Pelicula SET fecha="+fecha+" WHERE id="+id);
	}

	/**
	 * Asigna un nuevo director a la pelicula con el id [id].
	 */
	public boolean setDirector(int id, String director){
		return gestordb.realiza("UPDATE Pelicula SET director="+director+" WHERE id="+id);
	}

	/**
	 * Asigna una nueva sinopsis a la pelicula con el id [id].
	 */
	public boolean setSinopsis(int id, String sinopsis){
		return gestordb.realiza("UPDATE Pelicula SET sinopsis="+sinopsis+" WHERE id="+id);
	}

	/**
	 * Asigna una nueva duracion a la pelicula con el id [id].
	 */
	public boolean setDuracion(int id, int duracion){
		return gestordb.realiza("UPDATE Pelicula SET duracion="+duracion+" WHERE id="+id);
	}

	/**
	 * Asigna una nueva valoracion a la pelicula con el id [id].
	 */
	public boolean setValoracion(int id, double valoracion){
		return gestordb.realiza("UPDATE Pelicula SET duracion="+valoracion+" WHERE id="+id);
	}

	/**
	 * Asigna un nuevo titulo a la pelicula con el id [id].
	 */
	public boolean setPublico(int id, String publico){
		return gestordb.realiza("UPDATE Dirigida SET categoria="+publico+" WHERE pelicula="+id);
	}

	/**
	 * Inserta una nueva pelicula en la base de datos.
	 * Si se realiza correctamente devuelve True,
	 * en caso contrario, devuelve False.
	 */
	public boolean nuevaPelicula(int id, String titulo, String fecha,String director, String sinopsis, int duracion,
												double valoracion, String categoria, String publico){
		boolean peli = gestordb.realiza("INSERT INTO Pelicula VALUES ("+id+", '"+fecha+"', '"+director+"', '" +
				sinopsis+"', "+duracion+", "+valoracion+")");
		boolean cat = gestordb.realiza("INSERT INTO Es VALUES ("+id+", "+categoria+")");
		boolean pub = gestordb.realiza("INSERT INTO Dirigiada VALUES ("+id+", "+publico+")");

		return peli && cat && pub;
	}

	/**
	 * Devuelve una lista con peliculas que cumplan las condiciones de los parametros.
	 */
	public ArrayList<Pelicula> buscarPeliculas(String titulo, String fecha, String director,
											   Integer duracion,
											Double valoracion, String categoria, String publico){
		//Comprueba que parametros son validos y construye la consulta.
		String condiciones = "";
		if (titulo!=null) {
			condiciones = condiciones+ " AND p.titulo='" + titulo+"'";
		}
		if(fecha!=null) {
			condiciones = condiciones+" AND p.fecha='" + fecha+"'";
		}
		if(director!=null) {
			condiciones = condiciones+" AND p.director='" + director+"'";
		}
		if(duracion>0) {
			condiciones = condiciones+" AND p.duracion='" + duracion+"'";
		}
		if(valoracion>0) {
			condiciones = condiciones+" AND p.valoracion='" + valoracion+"'";
		}
		if(categoria!=null) {
			condiciones = condiciones+" AND e.categoria='" + categoria+"'";
		}
		if(publico!=null) {
			condiciones = condiciones+" AND d.publico='" + publico+"'";
		}
		Log.d("CONDICIONES",condiciones);
		//Realiza la consulta.
		ResultSet resultado = gestordb.getRst("SELECT p.id, p.titulo, p.fecha, p.director, " +
				"p.duracion,p.valoracion, p.sinopsis, c.nombre, pub.nombre FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e WHERE"+
				" p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre"+condiciones);

		ArrayList<Pelicula> array = new ArrayList<Pelicula>();
		Cursor cursor = new Cursor(resultado);
		try {
			avanzar(cursor);
			//Añade las consultas al array.
			while(cursor.hasNext()) {
				resultado = cursor.getRst();
				array.add(pelicula = new Pelicula(resultado.getInt(1),resultado.getString(2),
						resultado.getString(3), resultado.getString(4),
						resultado.getString(7), resultado.getInt(5),
						resultado.getDouble(6), resultado.getString(8),
						resultado.getString(9)));
				avanzar(cursor);
			}
		} catch(Exception e) {
			//Si se produce un error devuelve null.
			return null;
		}
		//Devuelve la lista de peliculas.
		return array;
	}

	/**
	 * Elimina una pelicula de la base de datos.
	 */
	public boolean eliminarPelicula(int id){
		boolean cat = gestordb.realiza("DELETE FROM Es WHERE pelicula="+id);
		boolean pub = gestordb.realiza("DELETE FROM Dirigida WHERE pelicula="+id);
		boolean peli = gestordb.realiza("DELETE FROM Pelicula WHERE id="+id);
		return cat && pub && peli;
	}

	/**
	 * Obtiene todas las peliculas almacenadas en la base de datos.
	 */
	public ArrayList<Pelicula> obtenerTodas(){
		//Realiza la consulta.


		ArrayList<Pelicula> array = new ArrayList<Pelicula>();
		ResultSet resultado = gestordb.getRst("SELECT p.id, p.titulo, p.fecha, p.director, " +
				"p.duracion,p.valoracion, p.sinopsis, c.nombre, pub.nombre FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e WHERE"+
				" p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre");
		Cursor cursor = new Cursor(resultado);
		try {
			avanzar(cursor);
			//Añade las peliculas a la lista.
			while(cursor.hasNext()) {
				resultado = cursor.getRst();
				array.add(new Pelicula(resultado.getInt(1),resultado.getString(2),
						resultado.getString(3), resultado.getString(4),
						resultado.getString(7), resultado.getInt(5),
						resultado.getDouble(6), resultado.getString(8),
						resultado.getString(9)));
				avanzar(cursor);
			}
			if (cursor.hasNext()) {
				Log.d("CURSOR","TRUE");
			} else {
				Log.d("CURSOR","FALSE");
			}
		} catch(Exception e) {
			//Si hay algun error devuelve null.
			Log.d("DATOS",e.toString());
			return null;
		}
		//Devuelve el array.
		return array;
	}

	private void avanzar(Cursor cursor) {
		try {
			Thread t = new Thread(cursor);
			t.start();
			t.join();
		} catch (Exception e) {

		}
	}
}