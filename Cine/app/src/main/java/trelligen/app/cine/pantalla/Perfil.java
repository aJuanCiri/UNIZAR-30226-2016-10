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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;
import trelligen.app.cine.objeto.Usuario;

/**
 * Actividad que muestra por pantalla la información de perfil de un usuario
 * con opción a editarla mediante un botón.
 */
public class Perfil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button editbutton;      // Botón para editar el perfil.
    private TextView nick, mail, name, date;    // Información del usuario.
    private Sistema sistema;

    /*
    * Método que se activa para mostrar el perfil del usuario e interactuar
    * con el mismo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_perfil);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPerfil("usuario@gmail.com");
        editbutton = (Button) findViewById(R.id.profile_editbutton);
        editbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(Perfil.this, EditarPerfil.class));
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

    /*
    * Método que carga la información del perfil del usuario.
     */
    private void cargarInformacionPerfil(String user_mail) {
        nick = (TextView) findViewById(R.id.profile_username);
        mail = (TextView) findViewById(R.id.profile_mail);
        name = (TextView) findViewById(R.id.profile_nombre);
        date = (TextView) findViewById(R.id.profile_fnacimiento);
        Usuario usuario = sistema.getUserInfo(user_mail);
        nick.setText(usuario.getNick());
        mail.setText(usuario.getMail());
        name.setText(usuario.getName());
        date.setText(usuario.getNacimiento());

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
            startActivity(new Intent(Perfil.this, Perfil.class));
        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(Perfil.this, Login.class));
        } else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(Perfil.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(Perfil.this, BusquedaAvanzada.class));
        } else if (id == R.id.mis_vistas) {
            startActivity(new Intent(Perfil.this, Vistas.class));
        } else if (id == R.id.mis_pendientes) {
            startActivity(new Intent(Perfil.this, Pendientes.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
