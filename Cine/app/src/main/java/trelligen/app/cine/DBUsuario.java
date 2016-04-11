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
        //String consulta = "SELECT * FROM USUARIO WHERE mail='...' AND password='...';"
        //ResultadoConsulta resultado = gestordb.hacerConsulta(consulta);
        //(ResultadosConsulta es una hipotética clase que gestiona los resultados de las consultas sql)
        //if(resultado.getCantidad()==1) { return true; }
        return false;
    }

    /**
    * Devuelve la información de perfil de un usuario.
    */
    public Usuario getInfo(String mail){
        //Consulta a realizar
        String consulta = "SELECT mail, password, nick, nombre, nacimiento FROM " +
                "Usuario WHERE mail = "+mail;
        //Obtiene el resultado de la consulta
        ResultSet resultado = gestordb.getRst(consulta);
        try {
            resultado.next();
            //Encapsula la informacion en la clase Usuario.
            usuario = new Usuario(resultado.getString("mail"),resultado.getString("password"),
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
        gestordb.realiza("UPDATE Usuario SET mail="+newMail+" WHERE mail="+oldMail);
	}
	
    /**
     * Asigna una nueva contraseña al usuario con e-mail=[mail].
     */
	public void setPass(String mail, String pass){
        gestordb.realiza("UPDATE Usuario SET password="+pass+" WHERE mail="+mail);
	}
	
    /**
     * Asigna un nuevo nick al usuario con e-mail=[mail].
     */
	public void setNick(String mail, String nick){
        gestordb.realiza("UPDATE Usuario SET nick="+nick+" WHERE e-mail="+mail);
	}
	
    /**
    * Elimina un usuario de la base de datos.
    */
	public boolean borrarUsuario(String mail){
        boolean vista = gestordb.realiza("DELETE FROM Vista WHERE mail="+mail);
        boolean pendiente = gestordb.realiza("DELETE FROM Pendiente WHERE mail="+mail);
        boolean usuario = gestordb.realiza("DELETE FROM Usuario WHERE mail="+mail);
        return vista && pendiente && usuario;
	}

    /*
    * Introduce un nuevo usuario en la aplicación.
     */
    public boolean newUser(String mail,String pass,String nick,String nacimiento){
        // Método por hacer.
        return false;
    }
}