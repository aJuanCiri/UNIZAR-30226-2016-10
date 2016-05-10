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
        setContentView(R.layout.activity_pantalla_pelicula);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPelicula(getIntent().getExtras().getString("pelicula"));

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

        Pelicula pelicula = sistema.getPelicula(getIntent().getExtras().getInt("pelicula"));

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    /*
    * Método que muestra las opciones del menú desplegable y obtiene
    * la seleccionada.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            startActivity(new Intent(InfoPelicula.this, Perfil.class));
        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(InfoPelicula.this, Login.class));
        } else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(InfoPelicula.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(InfoPelicula.this, BusquedaAvanzada.class));
        } else if (id == R.id.mis_vistas) {
            startActivity(new Intent(InfoPelicula.this, Vistas.class));
        } else if (id == R.id.mis_pendientes) {
            startActivity(new Intent(InfoPelicula.this, Pendientes.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
