package trelligen.app.cine.pantalla;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class InfoPeliculaColeccion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button editbutton;  // Botón para editar la información.

    private Button vistas, pendientes, valorar;  // Botones para añadir o eliminar de las colecciones.

    RatingBar estrellas;        // Valoracion de la pelicula.

    private int id;     // Identificador de la pelicula.

    /*
    * Textos para mostrar la información de una película.
     */
    private TextView titulo, fecha, duracion, director, sinopsis;
    private RatingBar valoracion;   // Valoración de la película.
    private ImageView imagen;   // Imagen de la película.
    private Sistema sistema;    // Objeto para gestionar la interacción.
    private String usuario;
    /*
    * Método que muestra la información de la película por pantalla y responde
    * a las interacciones con el usuario.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Muestra la pantalla.
        setContentView(R.layout.activity_pantalla_infocoleccion);
        // Crea la instancia del objeto sistema.
        sistema = new Sistema(getApplicationContext());
        // Carga la información de una película.
        cargarInformacionPelicula(getIntent().getExtras().getString("pelicula"));
        usuario = getIntent().getExtras().getString("usuario");
        Log.d("USUARIO",usuario);
        vistas = (Button)findViewById(R.id.botonVistas);
        pendientes = (Button)findViewById(R.id.botonPendientes);
        valorar = (Button)findViewById(R.id.valorar);
        estrellas = ((RatingBar)findViewById(R.id.pelicula_valoracion));

        if(sistema.esVista(id,usuario)) {
            pendientes.setVisibility(View.INVISIBLE);
            pendientes.setEnabled(false);
            eliminarVista();
            activarValoracion();
            float val = (sistema.obtenerValoracion(id,usuario));
            Log.d("VAL",String.valueOf(val));
            estrellas.setRating(val);
        } else {
            if(sistema.esPendiente(id,usuario)) {
                eliminarPendiente();
            } else {
                anadirPendiente();
            }
            anadirVista();
            desactivarValoracion();
        }
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
        imagen = (ImageView) findViewById(R.id.pelicula_imagen);

        // Obtiene la película.
        Pelicula pelicula = sistema.getPelicula(getIntent().getExtras().getInt("pelicula"));

        // Muestra la información de la película.
        id = pelicula.getId();
        titulo.setText(pelicula.getTitulo());
        fecha.setText(pelicula.getFecha());
        duracion.setText("Duración " + pelicula.getDuracion() + " min");
        director.setText(pelicula.getDirector());
        sinopsis.setText(pelicula.getSinopsis());
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

        if (id == R.id.pantalla_principal_sesion) {
            Intent i = new Intent(InfoPeliculaColeccion.this, PantallaPrincipal.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.bus_avanzada_sesion) {
            Intent i = new Intent(InfoPeliculaColeccion.this, BusquedaAvanzada.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_vistas) {
            Intent i = new Intent(InfoPeliculaColeccion.this, Vistas.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_pendientes) {
            Intent i = new Intent(InfoPeliculaColeccion.this, Pendientes.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(InfoPeliculaColeccion.this, PantallaPrincipal.class));
        } else if (id == R.id.perfil) {
            Intent i = new Intent(InfoPeliculaColeccion.this, Perfil.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
     * Añade una pelicula a la lista de pendientes del usuario.
     */
    private void anadirPendiente() {
        pendientes.setText("Añadir a Pendientes");
        pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sistema.introducirPendiente(id,usuario);
                eliminarPendiente();
            }
        });
    }

    /*
     * Eliminar una pelicula de la lista de pendientes del usuario.
     */
    private void eliminarPendiente() {
        pendientes.setText("Eliminar de Pendientes");
        pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sistema.eliminarPendiente(id,usuario);
                anadirPendiente();
            }
        });
    }

    /*
     * Añade una pelicula a la lista de pendientes del usuario.
     */
    private void eliminarVista() {
        vistas.setText("Eliminar de Vistas");
        vistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sistema.eliminarVista(id,usuario);
                desactivarValoracion();
                pendientes.setVisibility(View.VISIBLE);
                pendientes.setEnabled(true);
                anadirPendiente();
                anadirVista();
            }
        });
    }

    /*
     * Eliminar una pelicula de la lista de pendientes del usuario.
     */
    private void anadirVista() {
        vistas.setText("Añadir a Vistas");
        vistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sistema.introducirVista(id,usuario);
                activarValoracion();
                if(sistema.esPendiente(id,usuario)) {
                    sistema.eliminarPendiente(id,usuario);
                }
                pendientes.setVisibility(View.INVISIBLE);
                pendientes.setEnabled(false);
                eliminarVista();
            }
        });
    }

    /*
     * Activa la opcion de valorar una pelicula.
     */
    private void activarValoracion() {
        valorar.setVisibility(View.VISIBLE);
        valorar.setEnabled(true);
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valoracion = estrellas.getRating();
                sistema.valorar(id,usuario,valoracion);
            }
        });
    }

    /*
     * Desactiva la opcion de valorar una pelicula.
     */
    private void desactivarValoracion() {
        valorar.setVisibility(View.INVISIBLE);
        valorar.setEnabled(false);
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }
}
