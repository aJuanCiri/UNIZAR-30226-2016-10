package trelligen.app.cine;

/**
 * Encapsula las consultas y funciones necesarias para la gestión de la base de datos de películas
 */
public class DBPelicula {
    private GestorDB gestordb;
    private Pelicula pelicula;

    public DBPelicula(){
        gestordb = new GestorDB();
    }
	
    public Pelicula getInformacion(int id){
        //String consulta = "SELECT ...";
        //ResultadosConsulta resultado = gestordb.hacerConsulta(consulta);
        //(ResultadosConsulta es una hipotética clase que gestiona los resultados de las consultas sql)
        //pelicula = new Pelicula(...);
        //return pelicula;
        return null;
    }

    public boolean setTitulo(int id, String titulo){
		
	}
	
	public boolean setFecha(int id, String fecha){
	
	} 
	
	public boolean setSinopsis(int id, String titulo){
	
	}

	public boolean setDuracion(int id, int duracion){
	
	} 
	
	public boolean setCategoria(int id, String categoria){
	
	} 
	
	public boolean nuevaPelicula(int id, String titulo, String fecha, String sinopsis, int duracion,
												double valoracion, String categoria, String publico){
	
	} 
	
	public ArrayList<Pelicula> buscarPeliculas(String titulo, String fecha, String sinopsis, int duracion,
											double valoracion, String categoria, String publico){
	
	}
	
	public boolean eliminarPelicula(int id){
	
	}
	
	public ArrayList<Pelicula> obtenerTodas(){
	
	}
}
