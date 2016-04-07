package trelligen.app.cine;

/**
 * Encapsula toda la información sobre un usuario
 */
public class Usuario {
    private String email;
    private String nick;
    private String fecha_nacimiento;

	/*
	* Constructor de un objeto usuario.
	*/
    public Usuario(){

    }

	/*
	* Método que devuelve el email de un usuario.
	*/
    public String getMail(){
        return email;
    }

	/*
	* Método que devuelve el nick de un usuario.
	*/
	public String getNick(){
		return nick;
	}
	
	/*
	* Método que devuelve la fecha de nacimiento de un usuario.
	*/
	public String getNacimiento(){
		return fecha_nacimiento;
	}
	
	/*
	* Método que cambia el email de un usuario.
	*/
	public void setMail(String email){
		this.email=email;
	}
	
	/*
	* Método que cambia el nick de un usuario.
	*/
	public void setNick(String nick){
		this.nick=nick;
	}
	
	/*
	* Método que cambia la fecha de nacimiento de un usuario.
	*/
	public void setNacimiento(String fecha){
		fecha_nacimiento=fecha;
	}
}
