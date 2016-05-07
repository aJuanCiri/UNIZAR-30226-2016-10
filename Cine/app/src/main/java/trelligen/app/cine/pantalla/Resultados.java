package trelligen.app.cine.pantalla;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Muestra los resultados a partir de un Array de películas enviado.
 */
public class Resultados extends Activity{

    private TextView titulo;
    private ImageView imagen;
    private Sistema sistema;

    private String nombrePeli, fecha, director,publico;
    private int duracion;
    private double valoracion;
    private ArrayList<String> categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);
        sistema = new Sistema(getApplicationContext());
        obtenerDatos();
        ArrayList<Pelicula> listaRecibida = solicitarPeliculas();
        for(int i=0; i<listaRecibida.size(); i++){
            cargarInformacionPeliculaEnX(listaRecibida.get(i), i);
        }
    }

    /*
        Función provisional (Reemplazar por el Array recibido)
     */
    private ArrayList<Pelicula> solicitarPeliculas(){
        ArrayList<Pelicula> resultadoTest = sistema.buscarPeliculas(nombrePeli,fecha, director,
                duracion,categoria,valoracion,publico);
        return resultadoTest;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;
    }


    private void cargarInformacionPeliculaEnX(Pelicula pelicula, int posicion){
        titulo = (TextView) findViewById(R.id.pelicula_titulo1 + posicion);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen1 + posicion);
        titulo.setText(pelicula.getTitulo());
        mostrarImagen(pelicula.getURL());
    }

    private void mostrarImagen(String url){
        MostrarImagen mostrar = new MostrarImagen(url);
        Thread t = new Thread(mostrar);
        t.start();
        try {
            t.join();
        }catch(Exception e) {

        }
        imagen.setImageBitmap(mostrar.getImagen());
    }

    private void obtenerDatos() {
        nombrePeli = getIntent().getExtras().getString("titulo");
        fecha = getIntent().getExtras().getString("fecha");
        director = getIntent().getExtras().getString("director");
        publico = getIntent().getExtras().getString("publico");
        duracion = getIntent().getExtras().getInt("duracion");
        valoracion = getIntent().getExtras().getDouble("valoracion");
        categoria = getIntent().getExtras().getStringArrayList("genero");
    }
}