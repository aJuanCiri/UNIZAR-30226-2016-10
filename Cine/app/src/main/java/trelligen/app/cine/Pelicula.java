package trelligen.app.cine;

/**
 * Encapsula toda la información sobre una película
 */
public class Pelicula {
    private int id;
    private String titulo;
    private String fecha;
    private String sinopsis;
    private int duración;
    private double valoración;
    private String categoria;
    private String publico;

	/*
	* Constructor de un objeto película.
	*/
    public Pelicula(){

    }

	/*
	* Método que devuelve el título de una película.
	*/
    public String getTitulo(){
        return titulo;
    }

	/*
	* Método que devuelve la fecha de estreno de una película.
	*/
    public String getFecha(){
        return fecha;
    }
	
	/*
	* Método que devuelve la sinopsis de una película.
	*/
	public String getSinopsis(){
        return sinopsis;
    }
	
	/*
	* Método que devuelve la duración de una película.
	*/
	public int getDuracion(){
        return duracion;
    }
	
	/*
	* Método que devuelve la valoración de una película.
	*/
	public double getValoracion(){
        return valoracion;
    }
	
	/*
	* Método que devuelve el número identificativo de una película.
	*/
	public int getId(){
		return id;
	}
	
	/*
	* Método que devuelve la categoría de una película.
	*/
	public String getCategoria(){
		return categoria;
	}
}
