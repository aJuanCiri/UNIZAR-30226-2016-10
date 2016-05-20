package trelligen.app.cine.objeto;

import java.util.ArrayList;

/**
 * Encapsula toda la información sobre una película.
 */
public class Pelicula {
    private int id;     // Número identificativo de la película.
    private String titulo;  // Título de la película.
    private String fecha;   // Fecha de la película.
    private String director;    // Director de la película.
    private String sinopsis;    // Sinopsis de la película.
    private int duracion;   // Duración de la película.
    private double valoracion;  // Valoración de la película.
    private ArrayList<String> categoria;    // Categorías de la película.
    private String publico; // Público destino de la película.
    private String imagenURL;   // Carátula de la película.

	/*
	* Constructor de un objeto película.
	*/
    public Pelicula(int id, String titulo, String fecha, String director,
                    String sinopsis, int duracion, double valoracion,
                            ArrayList<String> categoria, String publico, String imagenURL){
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.director = director;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.valoracion = valoracion;
        this.categoria = categoria;
        this.publico = publico;
        this.imagenURL = imagenURL;
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
	* Método que devuelve el director de una película.
	*/
    public String getDirector(){
        return director;
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
	public ArrayList<String> getCategoria(){
		return categoria;
	}

    /*
	* Método que devuelve la url de la imagen de la película.
	*/
    public String getURL() { return imagenURL; }
}
