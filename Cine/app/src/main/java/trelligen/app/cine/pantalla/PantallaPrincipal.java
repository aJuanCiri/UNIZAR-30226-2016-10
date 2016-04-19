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
import android.widget.ImageView;
import android.widget.TextView;
import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Actividad que muestra la pantalla principal de la aplicación
 * con las últimas películas.
 */
public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView test_text;
    private Sistema sistema;
    private TextView titulo;
    private ImageView imagen;

    /*
    * Mñetodo que se activa al abrir la aplicación para mostrar la pantalla
    * principal e interactuar con el usuario.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        sistema = new Sistema(getApplicationContext());
        cargarPeliculas();

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
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
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
            startActivity(new Intent(PantallaPrincipal.this, Perfil.class));
        }
        else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(PantallaPrincipal.this, Login.class));
        }
        else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(PantallaPrincipal.this, PantallaPrincipal.class));
        }
        else if (id == R.id.mejor_valoradas) {
            startActivity(new Intent(PantallaPrincipal.this, EditarPerfil.class));
        }
        else if (id == R.id.ult_busquedas) {
            startActivity(new Intent(PantallaPrincipal.this,InfoPelicula.class));
        }
        else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(PantallaPrincipal.this,Registrar.class));
        }
        else if (id == R.id.categorias) {

        }
        else if (id == R.id.mi_coleccion) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    * Método que carga la información de todas las películas por pantalla.
     */
    private void cargarPeliculas(){
        cargarInformacionPelicula(R.id.pelicula_titulo1,R.id.pelicula_imagen1,1);
        cargarInformacionPelicula(R.id.pelicula_titulo2,R.id.pelicula_imagen2,1);
        cargarInformacionPelicula(R.id.pelicula_titulo3,R.id.pelicula_imagen3,1);
        cargarInformacionPelicula(R.id.pelicula_titulo4,R.id.pelicula_imagen4,1);
        cargarInformacionPelicula(R.id.pelicula_titulo5,R.id.pelicula_imagen5,1);
        cargarInformacionPelicula(R.id.pelicula_titulo6,R.id.pelicula_imagen6,1);
        cargarInformacionPelicula(R.id.pelicula_titulo7,R.id.pelicula_imagen7,1);
        cargarInformacionPelicula(R.id.pelicula_titulo8,R.id.pelicula_imagen8,1);
        cargarInformacionPelicula(R.id.pelicula_titulo9,R.id.pelicula_imagen9,1);
        cargarInformacionPelicula(R.id.pelicula_titulo10,R.id.pelicula_imagen10,1);
        cargarInformacionPelicula(R.id.pelicula_titulo11,R.id.pelicula_imagen11,1);
        cargarInformacionPelicula(R.id.pelicula_titulo12,R.id.pelicula_imagen12,1);
        cargarInformacionPelicula(R.id.pelicula_titulo13,R.id.pelicula_imagen13,1);
        cargarInformacionPelicula(R.id.pelicula_titulo14,R.id.pelicula_imagen14,1);
        cargarInformacionPelicula(R.id.pelicula_titulo15,R.id.pelicula_imagen15,1);
        cargarInformacionPelicula(R.id.pelicula_titulo16,R.id.pelicula_imagen16,1);
        cargarInformacionPelicula(R.id.pelicula_titulo17,R.id.pelicula_imagen17,1);
        cargarInformacionPelicula(R.id.pelicula_titulo18,R.id.pelicula_imagen18,1);
        cargarInformacionPelicula(R.id.pelicula_titulo19,R.id.pelicula_imagen19,1);
        cargarInformacionPelicula(R.id.pelicula_titulo20,R.id.pelicula_imagen20,1);
    }

    /*
    * Método que muestra la información de la película solicitada por pantalla.
     */
    private void cargarInformacionPelicula(int id_titulo,int id_imagen,int peli){
        titulo = (TextView) findViewById(id_titulo);
        imagen = (ImageView) findViewById(id_imagen);

        Pelicula pelicula = sistema.getPelicula(1);

        titulo.setText(pelicula.getTitulo());

        mostrarImagen(pelicula.getURL());
    }

    /*
    * Método que muestra la imagen de la película por pantalla.
     */
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
