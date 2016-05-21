package trelligen.app.cine.pantalla;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;

/**
 * Actividad gestiona el login de un usuario.
 */
public class Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button iniciar_sesion;      // Botón de iniciar sesión.
    private TextView no_cuenta; // Texto si no tiene cuenta.
    private EditText mail;  // Texto para el mail.
    private TextView pass;  // Texto para el pass.
    private Sistema sistema;    // Objeto para interactuar con la base.

    /*
    * Método principal que se lanza al iniciar un activity de este tipo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Muestra la pantalla.
        setContentView(R.layout.activity_pantalla_login);

        // Botón para iniciar sesión.
        iniciar_sesion = (Button) findViewById(R.id.ini_sesion);
        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                verificar_sesion();
            }
        });

        // Botón si no tiene cuenta.
        no_cuenta = (TextView) findViewById(R.id.no_cuenta);
        no_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                startActivity(new Intent(Login.this, Registrar.class));
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
    * Método que verifica la sesión de un usuario.
     */
    private void verificar_sesion(){
        // Obtiene el mail.
        mail = (EditText) findViewById(R.id.email);
        // Obtiene el pass.
        pass = (EditText) findViewById(R.id.pass);
        // Crea la instancia del objeto sistema.
        sistema = new Sistema(getApplicationContext());
        sistema.conecta();
        // Comprueba si el login es correcto.
        if(!mail.getText().toString().equals("") && !pass.getText().toString().equals("") &&
                sistema.login(mail.getText().toString(), pass.getText().toString())){
            sistema.desConecta();
            Intent i = new Intent(Login.this, PantallaPrincipal.class);
            i.putExtra("usuario",mail.getText().toString());
            startActivity(i);
        }else{  // Si es incorrecto muestra un mensaje.
            mostrarMensaje("Login fallido!");
        }
        sistema.desConecta();
    }

    /*
    * Método que muestra un mensaje por pantalla.
     */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(Login.this,mensaje,Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(Login.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(Login.this, BusquedaAvanzada.class));
        } else if (id == R.id.login) {

        } else if (id == R.id.registrar) {
            startActivity(new Intent(Login.this, Registrar.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}