package trelligen.app.cine;

import java.util.ArrayList;

/**
 * La clase encapsula una serie de funciones, que realizan operaciones de lectura y escritura
 * a la parte interna del sistema, trata información de los usuarios, películas, colecciones,...
 */
public class Sistema {
    private DBUsuario dbusuario;
    private DBPelicula dbpelicula;
    private Usuario usuario;

	/*
	* Constructor de un objeto sistema.
	*/
    public Sistema(){
        dbusuario = new DBUsuario();
        dbpelicula = new DBPelicula();
    }
    public boolean login (String mail, String password){
        boolean loginOK =  dbusuario.checkLogin(mail,password);
        if (loginOK){
            // iniciar la sesión
            usuario = dbusuario.getInfo(mail); //Cargar en memoria la información del usuario
        }
        return loginOK;
    }

	/*
	* Método que borra una película.
	*/
    public boolean borrarPelicula(int id){
        return dbpelicula.eliminarPelicula(id);
    }

	/*
	* Método que devuelve una lista todas las películas existentes.
	*/
    public ArrayList<Pelicula> listarPeliculas(){
        return dbpelicula.obtenerTodas();
    }

	/*
	* Método que devuelve una lista con las películas que coinciden
	* con unos ciertos parámetros.
	*/
    public ArrayList<Pelicula> buscarPeliculas(String titulo, int fecha,
												String categoria, String publico, int valoracion){
        return dbpelicula.buscarPeliculas(titulo,fecha,categoria,publico,valoracion);
    }

	/*
	* Método que elimina un usuario.
	*/
    public boolean eliminarUsuario(String mail){
        return dbusuario.borrarUsuario(mail);
    }

	/*
	* Método que cierra la sesión que esté abierta actualmente.
	*/
    public boolean cerrarSesion(){
        return false;
    }

	/*
	* Método que devuelve la película dado un cierto número identificativo.
	*/
    public Pelicula getPelicula(int id){
        return dbpelicula.getInformacion(id);
    }

	/*
	* Método que crea una nueva película.
	*/
    public boolean nuevaPelicula(String titulo, String fecha,
									String sinopsis, String duracion, double valoracion){
        return dbpelicula.nuevaPelicula(titulo,fecha,sinopsis,duracion,valoracion);
    }

	/*
	* Método que crea un nuevo usuario.
	*/
    public boolean newUser(String mail, String pass, String nick, String nacimiento){
        return dbusuario.newUser(mail,pass,nick,nacimiento);
    }
}