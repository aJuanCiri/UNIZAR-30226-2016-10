package trelligen.app.cine.pantalla;

/**
 * Actividad que muestra por pantalla la información asociada a una película.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

public class InfoPelicula extends Activity {

    private Button editbutton;
    private TextView titulo, fecha, duracion, director, sinopsis;
    private RatingBar valoracion;
    private ImageView imagen;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPelicula(1);
    }

    private void cargarInformacionPelicula(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo);
        fecha = (TextView) findViewById(R.id.pelicula_fecha);
        duracion = (TextView) findViewById(R.id.pelicula_duracion);
        director = (TextView) findViewById(R.id.pelicula_director);
        sinopsis = (TextView) findViewById(R.id.pelicula_sinopsis);
        valoracion = (RatingBar) findViewById(R.id.pelicula_valoracion);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen);

        Pelicula pelicula = sistema.getPelicula(id_pelicula);

        titulo.setText(pelicula.getTitulo());
        fecha.setText(pelicula.getFecha());
        duracion.setText("Duración " + pelicula.getDuracion() + " min");
        director.setText(pelicula.getDirector());
        sinopsis.setText(pelicula.getSinopsis());
        valoracion.setNumStars((int) Math.round(pelicula.getValoracion()));

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
}
