package trelligen.app.cine.pantalla;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Created by Guillermo on 16/04/2016.
 */
public class ListaPeliculas extends Activity{

    private TextView titulo;
    private ImageView imagen;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_principal);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPelicula_1(1);
        cargarInformacionPelicula_2(2);
        cargarInformacionPelicula_3(3);
        cargarInformacionPelicula_4(4);
        cargarInformacionPelicula_5(5);
        cargarInformacionPelicula_6(6);
        cargarInformacionPelicula_7(7);
        cargarInformacionPelicula_8(8);
        cargarInformacionPelicula_9(9);
        cargarInformacionPelicula_10(10);
        cargarInformacionPelicula_11(11);
        cargarInformacionPelicula_12(12);
        cargarInformacionPelicula_13(13);
        cargarInformacionPelicula_14(14);
        cargarInformacionPelicula_15(15);
        cargarInformacionPelicula_16(16);
        cargarInformacionPelicula_17(17);
        cargarInformacionPelicula_18(18);
        cargarInformacionPelicula_19(19);
        cargarInformacionPelicula_20(20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;
    }


    private void cargarInformacionPelicula_1(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo1);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen1);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_2(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo2);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen2);

        Pelicula pelicula = sistema.getPelicula(2);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_3(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo3);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen3);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_4(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo4);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen4);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_5(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo5);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen5);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_6(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo6);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen6);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_7(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo7);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen7);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_8(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo8);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen8);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_9(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo9);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen9);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_10(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo10);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen10);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_11(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo11);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen11);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_12(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo12);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen12);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_13(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo13);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen13);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_14(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo14);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen14);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_15(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo15);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen15);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_16(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo16);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen16);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_17(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo17);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen17);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_18(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo18);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen18);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_19(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo19);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen19);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    private void cargarInformacionPelicula_20(int id_pelicula){
        titulo = (TextView) findViewById(R.id.pelicula_titulo20);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen20);

        Pelicula pelicula = sistema.getPelicula(1);

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
}
