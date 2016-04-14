package trelligen.app.cine.pantalla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

public class PantallaPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button iniciar_sesion;
    Button test_bd;
    Button test_imagen;
    TextView test_text;
    Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        //Creación de las instancias a las clases internas:

        //Código de prueba para botón de inicio de sesión
        iniciar_sesion = (Button) findViewById(R.id.button);
        iniciar_sesion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vw) {
                startActivity(new Intent(PantallaPrincipal.this, Login.class));
            }
        });
        //Código de prueba para la bd
        test_bd = (Button) findViewById(R.id.test_bd);
        test_bd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vw){
                test_bd();
            }
        });

        test_imagen = (Button) findViewById(R.id.test_imagen);
        test_imagen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View vw){
                test_imagen();
            }
        });
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

    //Prueba la BD
    public void test_bd(){
        Sistema sistema = new Sistema(getApplicationContext());
        //PARTE INTERNA
        ArrayList<Pelicula> pelicula = sistema.listarPeliculas();
        //PARTE INTERFAZ
        test_text = (TextView) findViewById(R.id.test_text);
        String titulos="";
        for(int i=0;i<pelicula.size();i++) {
            titulos = titulos+"\n"+pelicula.get(i).getTitulo();
        }
        test_text.setText(titulos);
    }

    //Muestra una imagen
    public void test_imagen() {
        Sistema sistema = new Sistema(getApplicationContext());
        MostrarImagen mostrar = new MostrarImagen(sistema.getPelicula(1).getURL());
        Thread t = new Thread(mostrar);
        t.start();
        try {
            t.join();
        }catch(Exception e) {

        }
        ImageView prueba = (ImageView) findViewById(R.id.imagenPrueba);
        prueba.setImageBitmap(mostrar.getImagen());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            startActivity(new Intent(PantallaPrincipal.this, Perfil.class));
        } else if (id == R.id.mi_coleccion) {

        } else if (id == R.id.mejor_valoradas) {

        } else if (id == R.id.ult_busquedas) {

        } else if (id == R.id.bus_avanzada) {

        } else if (id == R.id.categorias) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
