package trelligen.app.cine.objeto;

import android.app.Activity;
import android.content.Context;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import trelligen.app.cine.base.GestorDB;
import trelligen.app.cine.base.DBPelicula;
import trelligen.app.cine.base.DBUsuario;

/**
 * La clase encapsula una serie de funciones, que realizan operaciones
 * de lectura y escritura a la parte interna del sistema, trata información
 * de los usuarios, películas, colecciones,...
 */
public class Sistema {
    private DBPelicula dbpelicula;  // Objeto para gestionar películas.
    private DBUsuario dbusuario;    // Objeto para gestionar usuarios.
    private Usuario usuario;    // Objeto de un usuario.
    private GestorDB gestordb;  // Gestor de la base de datos.

	/*
	* Constructor de un objeto sistema.
	*/
    public Sistema(Context context){
        gestordb = new GestorDB(context);
        dbpelicula = new DBPelicula(gestordb);
        dbusuario = new DBUsuario(gestordb);
    }

    /*
    * Método que gestiona el login de un usuario.
     */
    public boolean login (String mail, String password){
        int hashPass = password.hashCode();
        boolean loginOK =  dbusuario.checkLogin(mail,Integer.toString(hashPass));
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
   public ArrayList<Pelicula> buscarPeliculas(String titulo, String fecha,
												String director,int duracion,
                                               ArrayList<String> categoria,double valoracion,
                                              String publico){
        return dbpelicula.buscarPeliculas(titulo,fecha,director,
                            duracion,valoracion,categoria,publico);
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
    public boolean nuevaPelicula(int id, String titulo, String fecha,
									String director, String sinopsis,
                                 int duracion, double valoracion, String categoria, String publico){
        return dbpelicula.nuevaPelicula(id,titulo,fecha, director,sinopsis,duracion,valoracion,
                categoria, publico);
    }

	/*
	* Método que crea un nuevo usuario.
	*/
    public boolean newUser(String mail, String pass, String nick,
                                        String nombre, String nacimiento){
        int hashPass = pass.hashCode();
        return dbusuario.newUser(mail,Integer.toString(hashPass),nick,nombre,nacimiento);
    }

    /*
    * Devuelve la información asociada a un usuario.
     */
    public Usuario getUserInfo(String mail){
        return dbusuario.getInfo(mail);
    }

    /*
    * Actualiza la información de un usuario.
     */
    public void updateUser(String mail, String nick, String name, String fnacimiento){
        if(!nick.equals("")){
            dbusuario.setNick(mail,nick);
        }
        if(!name.equals("")){
            dbusuario.setName(mail,name);
        }
        if(!fnacimiento.equals("")){
            dbusuario.setNacimiento(mail, fnacimiento);
        }
    }

    /*
    * Método que actualiza la contraseña de un usuario.
     */
    public boolean updatePass(String mail, String pass, String newPass1, String newPass2){
        Usuario user = dbusuario.getInfo(mail);
        String viejaPass = Integer.toString(pass.hashCode());
        if(newPass1.equals(newPass2) && user.getPass().equals(viejaPass)){
            int hash = newPass1.hashCode();
            String nuevaPass = Integer.toString(hash);
            dbusuario.setPass(mail,nuevaPass);
            return true;
        } else{
            return false;
        }
    }

    /*
    * Método que obtiene las películas vistas por un usuario.
     */
    public ArrayList<Pelicula> obtenerVistas(String usuario){
        return dbpelicula.obtenerVistas(usuario);
    }

    /*
    * Método que obtiene las películas pendientes de un usuario.
     */
    public ArrayList<Pelicula> obtenerPendientes(String usuario){
        return dbpelicula.obtenerPendientes(usuario);
    }

    /*
     * Comprueba si una pelicula se encuentra en la lista de pendientes del usuario.
     */
    public boolean esPendiente(int id, String usuario) {
        return dbpelicula.esPendiente(id,usuario);
    }

    /*
     * Comprueba si una pelicula se encuentra en la lista de vistas del usuario.
     */
    public boolean esVista(int id, String usuario) {
        return dbpelicula.esVista(id,usuario);
    }

    /*
     * Introduce una pelicula en la lista de Pendientes del usuario.
     */
    public void introducirPendiente(int id, String usuario) {
        dbpelicula.introducirPendiente(id,usuario);
    }

    /*
     * Introduce una pelicula en la lista de Vistas del usuario.
     */
    public void introducirVista(int id, String usuario) {
        dbpelicula.introducirVista(id,usuario);
    }

    /*
     * Introduce una pelicula en la lista de Pendientes del usuario.
     */
    public void eliminarPendiente(int id, String usuario) {
        dbpelicula.eliminarPendiente(id,usuario);
    }

    /*
     * Introduce una pelicula en la lista de Vistas del usuario.
     */
    public void eliminarVista(int id, String usuario) {
        dbpelicula.eliminarVista(id,usuario);
    }

    /*
     * Valorar una pelicula vista por el usuario.
     */
    public void valorar(int id, String usuario, double valoracion) {
        dbpelicula.valorar(id,usuario,valoracion);
    }

    /*
     * Obtiene la valoracion de un usuar
     */
    public float obtenerValoracion(int id, String usuario) {
        return dbpelicula.obtenerValoracion(id,usuario);
    }
}
