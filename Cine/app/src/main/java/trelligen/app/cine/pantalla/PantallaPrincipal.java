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
    TextView test_text;
    private Sistema sistema;
    private TextView titulo;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
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
        //Creación de las instancias a las clases internas:

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
        }
        else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(PantallaPrincipal.this, Perfil.class));
        }
        else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(PantallaPrincipal.this, PantallaPrincipal.class));
        }
        else if (id == R.id.mejor_valoradas) {

        } else if (id == R.id.ult_busquedas) {

        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(PantallaPrincipal.this, BusquedaAvanzada.class));
        } else if (id == R.id.categorias) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
