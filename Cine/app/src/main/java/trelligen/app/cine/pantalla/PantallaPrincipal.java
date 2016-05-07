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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.content.Context;

import java.util.ArrayList;

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
    private Sistema sistema;
    private TextView titulo;
    private ImageView imagen;
    ArrayList<Integer> pelis = new ArrayList<Integer>();
    ArrayList<Integer> layaout = new ArrayList<Integer>();

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

        titulo = (TextView) findViewById(R.id.pelicula_titulo1);
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                infoPelicula(R.id.pelicula_titulo1);
            }
        });

        titulo = (TextView) findViewById(R.id.pelicula_titulo2);
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                infoPelicula(R.id.pelicula_titulo2);
            }
        });

        titulo = (TextView) findViewById(R.id.pelicula_titulo3);
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                infoPelicula(R.id.pelicula_titulo3);
            }
        });

        titulo = (TextView) findViewById(R.id.pelicula_titulo4);
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                infoPelicula(R.id.pelicula_titulo4);
            }
        });

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
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                Intent i = new Intent(PantallaPrincipal.this, Resultados.class);
                i.putExtra("titulo",query);
                /*i.putExtra("fecha",null);
                i.putExtra("director",null);
                i.putExtra("duracion",duracion);
                i.putExtra("valoracion",valoracion);
                i.putExtra("publico",publico);
                i.putExtra("genero",genero);*/
                startActivity(i);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
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
        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(PantallaPrincipal.this, Login.class));
        } else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(PantallaPrincipal.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(PantallaPrincipal.this, BusquedaAvanzada.class));
        } else if (id == R.id.mis_vistas) {

        } else if (id == R.id.mis_pendientes) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    * Método que carga la información de todas las películas por pantalla.
     */
    private void cargarPeliculas(){
        cargarInformacionPelicula(R.id.pelicula_titulo1,R.id.pelicula_imagen1,1,0);
        layaout.add(0,new Integer(R.id.pelicula_titulo1));
        cargarInformacionPelicula(R.id.pelicula_titulo2,R.id.pelicula_imagen2,1,1);
        layaout.add(1,new Integer(R.id.pelicula_titulo2));
        cargarInformacionPelicula(R.id.pelicula_titulo3,R.id.pelicula_imagen3,1,2);
        layaout.add(2,new Integer(R.id.pelicula_titulo3));
        cargarInformacionPelicula(R.id.pelicula_titulo4,R.id.pelicula_imagen4,1,3);
        layaout.add(3,new Integer(R.id.pelicula_titulo4));
    }

    /*
    * Método que muestra la información de la película solicitada por pantalla.
     */
    private void cargarInformacionPelicula(int id_titulo,int id_imagen,int peli,int indice){
        titulo = (TextView) findViewById(id_titulo);
        imagen = (ImageView) findViewById(id_imagen);

        Pelicula pelicula = sistema.getPelicula(peli);
        pelis.add(indice,pelicula.getId());

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

    /*
    * Obtiene la película seleccionada y muestra una pantalla con su
    * información.
     */
    private void infoPelicula(int id_titulo){
        Intent i = new Intent(PantallaPrincipal.this, InfoPelicula.class);
        int indice = layaout.indexOf(new Integer(id_titulo));
        i.putExtra("pelicula",pelis.get(indice));
        startActivity(i);
    }
}
