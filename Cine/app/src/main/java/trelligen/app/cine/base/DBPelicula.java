package trelligen.app.cine.base;

import java.sql.ResultSet;
import java.util.ArrayList;

import trelligen.app.cine.objeto.CursorObtieneResultados;
import trelligen.app.cine.objeto.CursorPelicula;
import trelligen.app.cine.objeto.Pelicula;

/**
 * Encapsula las consultas y funciones necesarias para la
 * gestión de la base de datos de películas.
 */
public class DBPelicula {
    private GestorDB gestordb;	// Gestor que conecta con la base de datos.
    private Pelicula pelicula;	// Objeto de una película.

	/*
	* Constructor del objeto.
	 */
    public DBPelicula(GestorDB gestorDB){
        this.gestordb = gestorDB;
    }

	/*
	 * Devuelve la información de una pelicula.
	 */
    public Pelicula getInformacion(int id){
		// Consulta a realizar.
        String consulta = "SELECT DISTINCT p.id, p.titulo, p.fecha, p.director, p.duracion, " +
				"p.valoracion, p.sinopsis, pub.nombre, p.URL FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e WHERE p.id="+id+" AND " +
				"p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre";
		// Obtiene el resultado de la consulta.
        ResultSet resultado = gestordb.getRst(consulta);
		CursorPelicula cursor = new CursorPelicula(resultado,gestordb);
		Thread t = new Thread(cursor);
		t.start();
		try {
			t.join();
		} catch(Exception e) {

		}
		pelicula = cursor.getArray().get(0);
		// Devuelve la pelicula.
        return pelicula;
    }

	/*
	 * Asigna un nuevo titulo a la pelicula con el id [id].
	 */
    public boolean setTitulo(int id, String titulo){
		return gestordb.realiza("UPDATE Pelicula SET titulo="+titulo+" WHERE id="+id);
	}

	/*
	 * Asigna una nueva fecha a la pelicula con el id [id].
	 */
	public boolean setFecha(int id, String fecha){
		return gestordb.realiza("UPDATE Pelicula SET fecha="+fecha+" WHERE id="+id);
	}

	/*
	 * Asigna un nuevo director a la pelicula con el id [id].
	 */
	public boolean setDirector(int id, String director){
		return gestordb.realiza("UPDATE Pelicula SET director="+director+" WHERE id="+id);
	}

	/*
	 * Asigna una nueva sinopsis a la pelicula con el id [id].
	 */
	public boolean setSinopsis(int id, String sinopsis){
		return gestordb.realiza("UPDATE Pelicula SET sinopsis="+sinopsis+" WHERE id="+id);
	}

	/*
	 * Asigna una nueva duracion a la pelicula con el id [id].
	 */
	public boolean setDuracion(int id, int duracion){
		return gestordb.realiza("UPDATE Pelicula SET duracion="+duracion+" WHERE id="+id);
	}

	/*
	 * Asigna una nueva valoracion a la pelicula con el id [id].
	 */
	public boolean setValoracion(int id, double valoracion){
		return gestordb.realiza("UPDATE Pelicula SET duracion="+valoracion+" WHERE id="+id);
	}

	/*
	 * Asigna un nuevo titulo a la pelicula con el id [id].
	 */
	public boolean setPublico(int id, String publico){
		return gestordb.realiza("UPDATE Dirigida SET categoria="+publico+" WHERE pelicula="+id);
	}

	/*
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

	/*
	 * Devuelve una lista con peliculas que cumplan las condiciones de los parametros.
	 */
	public ArrayList<Pelicula> buscarPeliculas(String titulo, String fecha, String director,
											   Integer duracion,
											Double valoracion, ArrayList<String> categoria, String publico){

		// Comprueba que parametros son validos y construye la consulta.
		String condiciones = " AND p.valoracion>="+valoracion;
		if(fecha!=null) {
			condiciones = condiciones+" AND p.fecha='" + fecha+"'";
		}
		if(duracion>0) {
			condiciones = condiciones+" AND p.duracion>=" + (duracion-15)+" AND p.duracion<="+(duracion+15);
		}
		if(publico!=null) {
			condiciones = condiciones+" AND d.publico='" + publico+"'";
		}
		if(categoria != null && !categoria.isEmpty()) {
			condiciones = condiciones+" AND (e.categoria='" +
					categoria.get(0)+"'";
			for(int i = 1; i<categoria.size(); i++) {
				condiciones = condiciones+" OR e.categoria='" +
						categoria.get(i)+"'";
			}
			condiciones = condiciones+")";
		}

		// Realiza la consulta.
		ResultSet resultado = gestordb.getRst("SELECT DISTINCT p.id, p.titulo, p.fecha, p.director, " +
				"p.duracion,p.valoracion, p.sinopsis, pub.nombre, p.URL FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e WHERE"+
				" p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre"+condiciones+" ORDER BY p.titulo");
		CursorPelicula cursor = null;
		cursor = new CursorPelicula(resultado,gestordb,titulo,director);
		ArrayList<Pelicula> array = null;
		try {
			Thread t = new Thread(cursor);
			t.start();
			t.join();
			array = cursor.getArray();
		} catch(Exception e) {
			// Si se produce un error devuelve null.
			return null;
		}
		// Devuelve la lista de peliculas.
		return array;
	}

	/*
	 * Elimina una pelicula de la base de datos.
	 */
	public boolean eliminarPelicula(int id){

		boolean cat = gestordb.realiza("DELETE FROM Es WHERE pelicula="+id);
		boolean pub = gestordb.realiza("DELETE FROM Dirigida WHERE pelicula="+id);
		boolean peli = gestordb.realiza("DELETE FROM Pelicula WHERE id="+id);
		return cat && pub && peli;
	}

	/*
	 * Obtiene las películas pendientes por un usuario.
	 */
	public ArrayList<Pelicula> obtenerPendientes(String usuario){

		// Realiza la consulta.
		ArrayList<Pelicula> array = null;
		String consulta = "SELECT DISTINCT p.id, p.titulo, p.fecha, p.director, p.duracion, " +
				"p.valoracion, p.sinopsis, pub.nombre, p.URL FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e, Pendiente pen" +
				" WHERE p.id=pen.pelicula AND " +
				"p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre AND pen.usuario='"+usuario+"' ORDER BY p.titulo";
		ResultSet resultado = gestordb.getRst(consulta);
		CursorPelicula cursor = new CursorPelicula(resultado,gestordb);
		try {
			Thread t = new Thread(cursor);
			t.start();
			t.join();
			array = cursor.getArray();
		} catch(Exception e) {
			// Si hay algun error devuelve null.
			return null;
		}
		// Devuelve el array.
		return array;
	}

	/*
	 * Obtiene las películas vistas de un usuario.
	*/
	public ArrayList<Pelicula> obtenerVistas(String usuario){

		// Realiza la consulta.
		ArrayList<Pelicula> array = null;
		String consulta = "SELECT DISTINCT p.id, p.titulo, p.fecha, p.director, p.duracion, " +
				"p.valoracion, p.sinopsis, pub.nombre, p.URL FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e, Vista v" +
				" WHERE p.id=v.pelicula AND " +
				"p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre AND v.usuario='"+usuario+"' ORDER BY p.titulo";
		ResultSet resultado = gestordb.getRst(consulta);
		CursorPelicula cursor = new CursorPelicula(resultado,gestordb);
		try {
			Thread t = new Thread(cursor);
			t.start();
			t.join();
			array = cursor.getArray();
		} catch(Exception e) {
			// Si hay algun error devuelve null.
			return null;
		}
		// Devuelve el array.
		return array;
	}

	/*
	 * Obtiene todas las peliculas almacenadas en la base de datos.
	 */
	public ArrayList<Pelicula> obtenerTodas(){

		//Realiza la consulta.
		ArrayList<Pelicula> array = null;
		ResultSet resultado = gestordb.getRst("SELECT DISTINCT p.id, p.titulo, p.fecha, p.director, " +
				"p.duracion,p.valoracion, p.sinopsis, pub.nombre, p.URL FROM Pelicula p, " +
				"Categoria c, Publico pub, Dirigida d, Es e WHERE"+
				" p.id=e.pelicula AND e.categoria=c.nombre AND p.id=d.pelicula AND " +
				"d.publico=pub.nombre");
		CursorPelicula cursor = new CursorPelicula(resultado,gestordb);
		try {
			Thread t = new Thread(cursor);
			t.start();
			t.join();
			array = cursor.getArray();
		} catch(Exception e) {
			// Si hay algun error devuelve null.
			return null;
		}
		// Devuelve el array.
		return array;
	}

	/*
	 * Devuelve true si la pelicula esta en la lista de pendientes del usuario.
	 */
	public boolean esPendiente(int id, String usuario) {
		ResultSet rst = gestordb.getRst("SELECT pelicula FROM Pendiente WHERE pelicula="+id+" AND usuario='"+usuario+"'");
		return obtieneResultado(rst);
	}

	/*
	 * Devuelve true si la pelicula esta en la lista de pendientes del usuario.
	 */
	public boolean esVista(int id, String usuario) {
		ResultSet rst = gestordb.getRst("SELECT pelicula FROM Vista WHERE pelicula="+id+" AND usuario='"+usuario+"'");
		return obtieneResultado(rst);
	}

	/*
	 * Dada una consulta informa si ha obtenido resultado.
	 */
	private boolean obtieneResultado(ResultSet rst) {
		CursorObtieneResultados cursor = new CursorObtieneResultados(rst);
		Thread t = new Thread(cursor);
		t.start();
		try {
			t.join();
		} catch(Exception e) {
			return false;
		}
		return cursor.getEncontrado();
	}

	/*
	 * Introduce una pelicula en la lista de Pendientes del usuario.
	 */
	public void introducirPendiente(int id, String usuario) {
		gestordb.realiza("INSERT INTO Pendiente VALUES ('"+usuario+"',"+id+")");
	}

	/*
	 * Introduce una pelicula en la lista de Vistas del usuario.
	 */
	public void introducirVista(int id, String usuario) {
		gestordb.realiza("INSERT INTO Vista VALUES ('"+usuario+"',"+id+",0)");
	}

	/*
	 * Elimina una pelicula de la lista de Pendientes del usuario.
	 */
	public void eliminarPendiente(int id, String usuario) {
		gestordb.realiza("DELETE FROM Pendiente WHERE usuario='"+usuario+"' AND pelicula="+id);
	}

	/*
	 * Introduce una pelicula de la lista de Vistas del usuario.
	 */
	public void eliminarVista(int id, String usuario) {
		gestordb.realiza("DELETE FROM Vista WHERE usuario='"+usuario+"' AND pelicula="+id);
	}

	public void valorar(int id, String usuario, double valoracion) {
		gestordb.realiza("UPDATE Vista SET valoracion="+valoracion+" WHERE pelicula="+id
				+" AND usuario='"+usuario+"'");
	}

	/*
     * Obtiene la valoracion de un usuario de una pelicula.
     */
	public float obtenerValoracion(int id, String usuario) {
		ResultSet rst =
				gestordb.getRst
						("SELECT valoracion FROM Vista WHERE usuario='"+usuario
								+"' AND pelicula="+id);
		try {
			rst.next();
			return rst.getFloat(1);
		} catch(Exception e) {
			return 0;
		}
	}
}
