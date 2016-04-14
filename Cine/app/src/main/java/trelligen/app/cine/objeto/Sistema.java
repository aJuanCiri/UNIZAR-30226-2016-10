package trelligen.app.cine.objeto;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.security.SecureRandom;
import java.util.ArrayList;

import trelligen.app.cine.base.GestorDB;
import trelligen.app.cine.base.DBPelicula;
import trelligen.app.cine.base.DBUsuario;
import trelligen.app.cine.mail.MailImplementor;

/**
 * La clase encapsula una serie de funciones, que realizan operaciones de lectura y escritura
 * a la parte interna del sistema, trata información de los usuarios, películas, colecciones,...
 */
public class Sistema {
    private DBPelicula dbpelicula;
    private DBUsuario dbusuario;
    private Usuario usuario;
    private GestorDB gestordb;

	/*
	* Constructor de un objeto sistema.
	*/
    public Sistema(Context context){
        gestordb = new GestorDB(context);
        dbpelicula = new DBPelicula(gestordb);
        dbusuario = new DBUsuario(gestordb);
    }
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
                                               String categoria,double valoracion, String publico){
        return dbpelicula.buscarPeliculas(titulo,fecha,director,duracion,valoracion,categoria,publico);
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
    * Método que envía un correo con la nueva contraseña al usuario.
    */
    public void enviarCorreo(Activity x, String mail){
        MailImplementor envioMail = new MailImplementor(x);
        String newPass = nuevaPass(mail);
        envioMail.send(mail,newPass);
    }

    /*
    Devuelve la información asociada a un usuario
     */
    public Usuario getUserInfo(String mail){
        return dbusuario.getInfo(mail);
    }

    /*
    * Actualiza la información de un usuario
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
    Actualiza la información de un usuario
     */
    public boolean updatePass(String mail, String pass, String newPass1, String newPass2){
        Usuario user = dbusuario.getInfo(mail);
        int hash = newPass1.hashCode();
        String nuevaPass = Integer.toString(hash);
        hash = pass.hashCode();
        String viejaPass = Integer.toString(hash);
        if(newPass1.equals(newPass2) && user.getPass().equals(viejaPass)){
            dbusuario.setPass(mail,nuevaPass);
            return true;
        } else{
            return false;
        }
    }

    /*
    * Método que genera de manera aleatoria una nueva contraseña.
    */
    private String nuevaPass(String mail){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( 12 );
        for( int i = 0; i < 12; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        String newPass = sb.toString();
        dbusuario.setPass(mail,newPass);
        return newPass;
    }
}
