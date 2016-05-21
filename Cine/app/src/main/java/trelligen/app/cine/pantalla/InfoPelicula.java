package trelligen.app.cine.pantalla;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
 * Actividad que muestra por pantalla la información asociada a una película.
 */
public class InfoPelicula extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button editbutton;  // Botón para editar la información.
    /*
    * Textos para mostrar la información de una película.
     */
    private TextView titulo, fecha, duracion, director, sinopsis;
    private RatingBar valoracion;   // Valoración de la película.
    private ImageView imagen;   // Imagen de la película.
    private Sistema sistema;    // Objeto para gestionar la interacción.

    /*
    * Método que muestra la información de la película por pantalla y responde
    * a las interacciones con el usuario.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Muestra la pantalla.
        setContentView(R.layout.activity_pantalla_pelicula);
        // Crea la instancia del objeto Sistema.
        sistema = new Sistema(getApplicationContext());
        // Carga la información de la película.
        cargarInformacionPelicula(getIntent().getExtras().getString("pelicula"));

        // Muestra los distintos menús.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*
    * Método que carga la información de la película por pantalla.
     */
    private void cargarInformacionPelicula(String title){
        titulo = (TextView) findViewById(R.id.pelicula_titulo);
        fecha = (TextView) findViewById(R.id.pelicula_fecha);
        duracion = (TextView) findViewById(R.id.pelicula_duracion);
        director = (TextView) findViewById(R.id.pelicula_director);
        sinopsis = (TextView) findViewById(R.id.pelicula_sinopsis);
        valoracion = (RatingBar) findViewById(R.id.pelicula_valoracion);
        imagen = (ImageView) findViewById(R.id.pelicula_imagen);

        // Obtiene la película.
        Pelicula pelicula = sistema.getPelicula(getIntent().getExtras().getInt("pelicula"));

        // Muestra la información de la película.
        titulo.setText(pelicula.getTitulo());
        fecha.setText(pelicula.getFecha());
        duracion.setText("Duración " + pelicula.getDuracion() + " min");
        director.setText(pelicula.getDirector());
        sinopsis.setText(pelicula.getSinopsis());
        valoracion.setRating((float)pelicula.getValoracion());
        valoracion.setIsIndicator(true);
        // Muestra la imagen de la película.
        mostrarImagen(pelicula.getURL());
    }

    /*
    * Método que muestra la imagen de una película.
     */
    private void mostrarImagen(String url){
        // Obtiene la imagen.
        MostrarImagen mostrar = new MostrarImagen(url);
        Thread t = new Thread(mostrar);
        t.start();
        try {
            t.join();
        }catch(Exception e) {

        }
        imagen.setImageBitmap(mostrar.getImagen());
    }

    /*
    * Método que gestiona el menú.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
    * Método que muestra las opciones del menú desplegable y obtiene
    * la seleccionada.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pantalla_principal) {
            startActivity(new Intent(InfoPelicula.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(InfoPelicula.this, BusquedaAvanzada.class));
        } else if (id == R.id.login) {
            startActivity(new Intent(InfoPelicula.this, Login.class));
        } else if (id == R.id.registrar) {
            startActivity(new Intent(InfoPelicula.this, Registrar.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
