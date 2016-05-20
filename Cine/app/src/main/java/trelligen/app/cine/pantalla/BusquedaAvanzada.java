package trelligen.app.cine.pantalla;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Clase que gestiona la pantalla de búsqueda avanzada de películas.
 */
public class BusquedaAvanzada extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private double valoracion;  // Valoración de una película.
    private String publico; // Público destino de un película.
    private String titulo;  // Título de una película.
    private String fecha;   // Fecha de estreno de una película.
    private String director;    // Director de una película.
    private int duracion;   // Duración de una película.
    private String usuario; // Usuario en curso.

    private Button busqueda;    // Botón para comenzar la búsqueda.
    private RadioGroup publicoGR;   // Botón para el público destino.
    ArrayList<String> genero;   // Género de la película.

    /*
    * Método principal que se ejecuta al lanzar una actividad de este tipo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        // Comprueba si hay un usuario en curso.
        if(extras == null || extras.getString("usuario")==null){
            // Muestra la pantalla.
            setContentView(R.layout.activity_busqueda_avanzada);
        } else{
            // Muestra la patantalla.
            usuario = extras.getString("usuario");
            setContentView(R.layout.activity_busqueda_avanzada_sesion);
        }

        // Botón para la búsqueda.
        busqueda = (Button)findViewById(R.id.busqueda);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genero = new ArrayList<String>();
                asignarParametros();
                Intent i = new Intent(BusquedaAvanzada.this, Resultados.class);
                // Pasa los parámetros.
                i.putExtra("titulo",titulo);
                i.putExtra("fecha",fecha);
                i.putExtra("director",director);
                i.putExtra("duracion",duracion);
                i.putExtra("valoracion",valoracion);
                i.putExtra("publico",publico);
                i.putExtra("genero",genero);
                i.putExtra("usuario",usuario);
                startActivity(i);
            }
        });

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
     * Método que ssigna los valores a las variables en función
     * de los datos introducidos.
     */
    private void asignarParametros() {
        titulo = ((TextView) findViewById(R.id.titulo)).getText().toString();
        if(titulo.equals("")) {
            titulo = null;
        }
        director = ((TextView) findViewById(R.id.director)).getText().toString();
        if(director.equals("")) {
            director = null;
        }
        fecha = ((TextView) findViewById(R.id.fecha)).getText().toString();
        if(fecha.equals("")) {
            fecha = null;
        }
        String duracionTexto = ((TextView) findViewById(R.id.duracion)).getText().toString();
        if(duracionTexto.equals("")) {
            duracion = -1;
        } else {
            duracion = Integer.parseInt(duracionTexto);
        }
        obtenerGenero();
        obtenerPublico();
        valoracion = ((RatingBar)findViewById(R.id.valoracion)).getRating();
    }

    /*
     * Obtiene el genero introducido
     */
    private void obtenerGenero() {
        CheckBox accion = (CheckBox)findViewById(R.id.accion);
        CheckBox drama = (CheckBox)findViewById(R.id.drama);
        CheckBox lucha = (CheckBox)findViewById(R.id.lucha);
        CheckBox terror = (CheckBox)findViewById(R.id.terror);
        CheckBox comedia = (CheckBox)findViewById(R.id.comedia);
        CheckBox fantasia = (CheckBox)findViewById(R.id.fantasia);
        CheckBox suspense = (CheckBox)findViewById(R.id.suspense);
        CheckBox musical = (CheckBox)findViewById(R.id.musical);
        CheckBox cficcion = (CheckBox)findViewById(R.id.cficcion);

        if(accion.isChecked()) {
            genero.add("Accion");
        }
        if(drama.isChecked()) {
            genero.add("Drama");
        }
        if(lucha.isChecked()) {
            genero.add("Lucha");
        }
        if(terror.isChecked()) {
            genero.add("Terror");
        }
        if(comedia.isChecked()) {
            genero.add("Comedia");
        }
        if(fantasia.isChecked()) {
            genero.add("Fantasia");
        }
        if(suspense.isChecked()) {
            genero.add("Suspense");
        }
        if(musical.isChecked()) {
            genero.add("Musical");
        }
        if(cficcion.isChecked()) {
            genero.add("C.Ficcion");
        }
    }

    /*
     * Obtiene el público destino seleccionado.
     */
    private void obtenerPublico() {
        publicoGR = (RadioGroup)findViewById(R.id.publico);
        int id = publicoGR.getCheckedRadioButtonId();
        switch(id) {
            case R.id.infantil:
                publico = "Infantil";
                break;
            case R.id.juvenil:
                publico = "Juvenil";
                break;
            case R.id.familiar:
                publico = "Familiar";
                break;
            case R.id.adulto:
                publico = "Adulto";
                break;
            default:
                publico = null;
                break;
        }
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
            startActivity(new Intent(BusquedaAvanzada.this, PantallaPrincipal.class));
        } else if (id == R.id.login) {
            startActivity(new Intent(BusquedaAvanzada.this, Login.class));
        } else if (id == R.id.bus_avanzada) {

        } else if (id == R.id.pantalla_principal_sesion) {
            Intent i = new Intent(BusquedaAvanzada.this, PantallaPrincipal.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.registrar) {
            startActivity(new Intent(BusquedaAvanzada.this, Registrar.class));
        } else if (id == R.id.perfil) {
            Intent i = new Intent(BusquedaAvanzada.this, Perfil.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_vistas) {
            Intent i = new Intent(BusquedaAvanzada.this, Vistas.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_pendientes) {
            Intent i = new Intent(BusquedaAvanzada.this, Pendientes.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.bus_avanzada_sesion) {

        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(BusquedaAvanzada.this, PantallaPrincipal.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
