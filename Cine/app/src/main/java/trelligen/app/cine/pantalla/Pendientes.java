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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Clase que gestiona la pantalla de las películas pendientes de un usuario.
 */
public class Pendientes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListViewAdapter adapter;  // ListView para mostrar las películas.
    private Sistema sistema;    // Instancia de la clase sistema.
    private Button anterior, siguiente; // Botones para pasar las páginas.
    private ArrayList<Pelicula> listaRecibida;  // Lista de películas resultado.
    private int numResultados;  // Número de resultados obtenidos.
    private final int RESULTADOS_POR_PAGINA = 4;    // Número de resultados a mostrar por página.
    private ListView lista; // Lista de películas.
    private int paginaActual = 0;   // Número de página actual.
    private String usuario; // Usuario en curso de la sesión.

    /*
    * Método principal que se lanza al iniciar el activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Muestra el layout.
        setContentView(R.layout.activity_pantalla_pendientes);
        // Obtiene el usuario en curso.
        usuario = getIntent().getExtras().getString("usuario");

        sistema = new Sistema(getApplicationContext()); // Obtiene la instancia de la clase sistema.
        lista = (ListView) findViewById(R.id.listView1);    // Lista de las películas a mostrar.
        anterior = (Button) findViewById(R.id.resultados_anterior); // Botón para la anterior página.
        siguiente = (Button) findViewById(R.id.resultados_siguiente);   // Botón para la siguiente página.
        anterior.setVisibility(View.INVISIBLE); // Al estar en la primera página, no debe mostrarse.

        listaRecibida = solicitarPeliculas();   // Solicita las películas de la base de datos.

        numResultados = listaRecibida.size();   // Obtiene el número de resultados obtenidos.

        // Si hay menos resultados que los que se muestran por página, desaparece el botón siguiente.
        ArrayList<Pelicula> resultados_pagina = null;
        if(numResultados<=RESULTADOS_POR_PAGINA){
            siguiente.setVisibility(View.INVISIBLE);
            // Obtiene las películas a mostrar en una página.
            resultados_pagina = new ArrayList<Pelicula>(
                    listaRecibida.subList(0,listaRecibida.size()));
        } else {
            // Obtiene las películas a mostrar en una página.
            resultados_pagina = new ArrayList<Pelicula>(
                    listaRecibida.subList(0,RESULTADOS_POR_PAGINA));
        }

        // Muestra las películas en la pantalla.
        adapter = new ListViewAdapter(this,resultados_pagina,usuario);
        lista.setAdapter(adapter);

        // Gestiona la interacción con el botón de siguiente página.
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                siguiente_pagina();
            }
        });

        // Gestiona la interacción con el botón de anterior página.
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                anterior_pagina();
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
    * Método encargado de gestionar la interacción con la siguiente página de resultados.
     */
    private void siguiente_pagina(){
        paginaActual++; // Actualiza el número de página.
        // Obtiene los índices del listado resultado a mostrar.
        int indice_inicio = paginaActual*RESULTADOS_POR_PAGINA;
        int limite = indice_inicio + RESULTADOS_POR_PAGINA;
        if(limite>numResultados){
            limite = listaRecibida.size();
        }
        // Obtiene las peliculas de la nueva pantalla.
        ArrayList<Pelicula> resultados_pagina = new ArrayList<Pelicula>(
                listaRecibida.subList(indice_inicio,limite));
        // Muestra las películas por pantalla.
        adapter = new ListViewAdapter(this,resultados_pagina,usuario);
        if(paginaActual==1){
            //Si es la segunda página se activa el boton anterior.
            anterior.setVisibility(View.VISIBLE);
        }
        if(limite==numResultados){
            //Si es la última página, se elimina el botón siguiente.
            siguiente.setVisibility(View.INVISIBLE);
        }
        lista.setAdapter(adapter);
    }

    /*
    * Método que se encarga de la interacción con la anterior página de resultados.
     */
    private void anterior_pagina(){
        paginaActual--; // Actualiza el número de página.
        // Obtiene los índices del listado resultado a mostrar.
        int indice_inicio = paginaActual*RESULTADOS_POR_PAGINA;
        int limite = indice_inicio + RESULTADOS_POR_PAGINA;
        // Obtiene las peliculas de la nueva pantalla.
        ArrayList<Pelicula> resultados_pagina = new ArrayList<Pelicula>(
                listaRecibida.subList(indice_inicio,limite));
        // Muestra las películas por pantalla.
        adapter = new ListViewAdapter(this,resultados_pagina,usuario);
        if(paginaActual==0){
            //Si es la primera página, se hace invisible el botón anterior.
            anterior.setVisibility(View.INVISIBLE);
        }
        if(numResultados>RESULTADOS_POR_PAGINA*paginaActual && siguiente.getVisibility()==View.INVISIBLE){
            //Si no es la última página, y el botón siguiente es invisible, se hace visible.
            siguiente.setVisibility(View.VISIBLE);
        }
        lista.setAdapter(adapter);
    }

    /*
     *  Método que obtiene de la base las películas resultado.
     */
    private ArrayList<Pelicula> solicitarPeliculas(){
        // Obtiene el listado de las películas.
        ArrayList<Pelicula> resultadoTest = sistema.obtenerPendientes(usuario);
        return resultadoTest;
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
            Intent i = new Intent(Pendientes.this, PantallaPrincipal.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.bus_avanzada_sesion) {
            Intent i = new Intent(Pendientes.this, BusquedaAvanzada.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_vistas) {
            Intent i = new Intent(Pendientes.this, Vistas.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_pendientes) {

        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(Pendientes.this, PantallaPrincipal.class));
        } else if (id == R.id.perfil) {
            Intent i = new Intent(Pendientes.this, Perfil.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}