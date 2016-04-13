package trelligen.app.cine;

import java.sql.ResultSet;

/**
 * Encapsula las consultas y funciones necesarias para la gestión de la base de datos de usuarios
 */
public class DBUsuario {
    private GestorDB gestordb;
    private Usuario usuario;

    public DBUsuario(GestorDB gestordb){
        this.gestordb = gestordb;
    }

    public boolean checkLogin(String mail, String password){
        //pass = Hash de pass
        String consulta = "SELECT * FROM USUARIO WHERE email='"+mail+"' AND contrasena='"+password+"'";
        ResultSet resultado = gestordb.getRst(consulta);
        //(ResultadosConsulta es una hipotética clase que gestiona los resultados de las consultas sql)
        try {
            if (resultado.next()) {
                return true;
            }
        } catch (Exception e){

        }
        return false;
    }

    /**
    * Devuelve la información de perfil de un usuario.
    */
    public Usuario getInfo(String mail){
        //Consulta a realizar
        String consulta = "SELECT email, contrasena, nick, nombre, nacimiento FROM " +
                "Usuario WHERE email = '"+mail+"'";
        //Obtiene el resultado de la consulta
        ResultSet resultado = gestordb.getRst(consulta);
        try {
            resultado.next();
            //Encapsula la informacion en la clase Usuario.
            usuario = new Usuario(resultado.getString("email"),resultado.getString("contrasena"),
                    resultado.getString("nick"), resultado.getString("nombre"),
                    resultado.getString("nacimiento"));
        } catch(Exception e) {
            //Si ocurre un error devuelve null.
            return null;
        }
        //Devuelve el usuario.
        return usuario;
    }

    /**
     * Asigna un nuevo e-mail al usuario con e-mail=[oldMail].
     */
    public void setMail(String oldMail, String newMail){
        gestordb.realiza("UPDATE Usuario SET email='"+newMail+"' WHERE email='"+oldMail+"'");
	}
	
    /**
     * Asigna una nueva contraseña al usuario con e-mail=[mail].
     */
	public void setPass(String mail, String pass){
        //pass = Hash de pass
        gestordb.realiza("UPDATE Usuario SET contrasena='"+pass+"' WHERE email='"+mail+"'");
	}
	
    /**
     * Asigna un nuevo nick al usuario con e-mail=[mail].
     */
	public void setNick(String mail, String nick){
        gestordb.realiza("UPDATE Usuario SET nick='"+nick+"' WHERE email='"+mail+"'");
	}
	
    /**
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
        //pass = Hash de pass
        boolean registro = gestordb.realiza("INSERT INTO Usuario VALUES ('"+mail+"', '"+pass+"', '"+
                nick+"', '"+nombre+"', '"+nacimiento+"')");
        return registro;
    }
}