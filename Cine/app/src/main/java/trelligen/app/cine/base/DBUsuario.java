package trelligen.app.cine.base;

import java.sql.ResultSet;

import trelligen.app.cine.objeto.Usuario;

/**
 * Encapsula las consultas y funciones necesarias para la gestión de
 * la base de datos de usuarios.
 */
public class DBUsuario {
    private GestorDB gestordb;  // Gestor que conecta con la base de datos.
    private Usuario usuario;    // Objeto de un usuario.

    /*
    * Constructor del objeto.
     */
    public DBUsuario(GestorDB gestordb){
        this.gestordb = gestordb;
    }

    /*
    * Devuelve true si y sólo si un login es correcto, false en caso contrario.
     */
    public boolean checkLogin(String mail, String password){
        // Consulta a realizar.
        String consulta = "SELECT * FROM USUARIO WHERE email='"+mail+"' AND contrasena='"+password+"'";
        ResultSet resultado = gestordb.getRst(consulta);
        try {
            if (resultado.next()) {
                return true;
            }
        } catch (Exception e){}
        return false;
    }

    /*
    * Devuelve la información de perfil de un usuario.
    */
    public Usuario getInfo(String mail){
        // Consulta a realizar
        String consulta = "SELECT email, contrasena, nick, nombre, nacimiento FROM " +
                "Usuario WHERE email = '"+mail+"'";
        // Obtiene el resultado de la consulta
        ResultSet resultado = gestordb.getRst(consulta);
        try {
            resultado.next();
            // Encapsula la informacion en la clase Usuario.
            usuario = new Usuario(resultado.getString("email"),resultado.getString("contrasena"),
                    resultado.getString("nick"), resultado.getString("nombre"),
                    resultado.getString("nacimiento"));
        } catch(Exception e) {
            // Si ocurre un error devuelve null.
            return null;
        }
        // Devuelve el usuario.
        return usuario;
    }

    /*
     * Asigna un nuevo e-mail al usuario con e-mail=[oldMail].
     */
    public void setMail(String user, String newMail){
        gestordb.realiza("UPDATE Usuario SET email='"+newMail+"' WHERE email='"+user+"'");
	}

    /*
     * Asigna un nuevo nombre al usuario con e-mail=[mail].
     */
    public void setName(String mail, String name){
        gestordb.realiza("UPDATE Usuario SET nombre='"+name+"' WHERE email='"+mail+"'");
    }
	
    /*
     * Asigna una nueva contraseña al usuario con e-mail=[mail].
     */
	public void setPass(String mail, String pass){
        gestordb.realiza("UPDATE Usuario SET contrasena='"+pass+"' WHERE email='"+mail+"'");
	}
	
    /*
     * Asigna un nuevo nick al usuario con e-mail=[mail].
     */
	public void setNick(String mail, String nick){
        gestordb.realiza("UPDATE Usuario SET nick='"+nick+"' WHERE email='"+mail+"'");
	}

    /*
     * Asigna unqa nueva fecha de nacimiento al usuario con e-mail=[mail].
     */
    public void setNacimiento(String mail, String nacimiento){
        gestordb.realiza("UPDATE Usuario SET nacimiento='"+nacimiento+"' WHERE email='"+mail+"'");
    }
	
    /*
    * Elimina un usuario de la base de datos.
    */
	public boolean borrarUsuario(String mail){
        boolean vista = gestordb.realiza("DELETE FROM Vista WHERE email='"+mail+"'");
        boolean pendiente = gestordb.realiza("DELETE FROM Pendiente WHERE email='"+mail+"'");
        boolean usuario = gestordb.realiza("DELETE FROM Usuario WHERE email='"+mail+"'");
        return vista && pendiente && usuario;
	}

    /*
    * Introduce un nuevo usuario en la aplicación.
     */
    public boolean newUser(String mail,String pass,String nick, String nombre,String nacimiento){
        boolean registro = gestordb.realiza("INSERT INTO Usuario VALUES ('"+
                    mail+"', '"+pass+"', '"+nick+"', '"+nombre+"', '"+nacimiento+"')");
        return registro;
    }
}