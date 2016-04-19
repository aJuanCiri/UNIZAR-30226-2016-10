package trelligen.app.cine.objeto;

/**
 * Encapsula toda la información sobre una película
 */
public class Pelicula {
    private int id;
    private String titulo;
    private String fecha;
    private String director;
    private String sinopsis;
    private int duracion;
    private double valoracion;
    private String categoria;
    private String publico;
    private String imagenURL;

	/*
	* Constructor de un objeto película.
	*/
    public Pelicula(int id, String titulo, String fecha, String director,
                    String sinopsis, int duracion, double valoracion,
                            String categoria, String publico, String imagenURL){
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
	public String getCategoria(){
		return categoria;
	}

    /*
	* Método que devuelve la url de la imagen de la película.
	*/
    public String getURL() { return imagenURL; }
}
