package trelligen.app.cine;

/**
 * Encapsula las consultas y funciones necesarias para la gestión de la base de datos de usuarios
 */
public class DBUsuario {
    private GestorDB gestordb;

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

    public Usuario getInfo(String mail){
        return null;
    }

    public void setMail(String oldMail, String newMail){
	
	}
	
	public void setPass(String mail, String pass){
	
	}
	
	public void setNick(String mail, String nick){
	
	}
	
	public boolean borrarUsuario(String mail){
	    return false;
	}
}
