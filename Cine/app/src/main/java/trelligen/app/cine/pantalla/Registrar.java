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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;

/**
 * Clase que gestiona el registro de un nuevo usuario.
 */
public class Registrar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button registrarse;     // Botón para registrarse.
    // Campos del registro del usuario.
    private EditText mail, pass, nick, name, date;

    /*
    * Método que se activa al registrar un usuario y que controla las interacciones
    * con el mismo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Muestra la pantalla.
        setContentView(R.layout.activity_pantalla_registrar);

        // Botón de registrarse.
        registrarse = (Button)findViewById(R.id.registrarse);
        registrarse.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                verificar_registro();
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
    * Método que verifica el correcto registro del usuario.
     */
    private void verificar_registro(){
        mail = (EditText) findViewById(R.id.email); // Obtiene el email.
        pass = (EditText) findViewById(R.id.password);  // Obtiene el pass.
        nick = (EditText) findViewById(R.id.nick);  // obtiene el nick.
        name = (EditText) findViewById(R.id.name);  // Obtiene el nombre.
        date = (EditText) findViewById(R.id.date);  // Obtiene la fecha.
        // Crea una instancia para interactuar con la base.
        Sistema sistema = new Sistema(getApplicationContext());
        sistema.conecta();
        // Comprueba si ha rellenado todos los campos.
        if(mail.getText().toString().equals("") || pass.getText().toString().equals("") ||
                nick.getText().toString().equals("") || name.getText().toString().equals("") ||
                date.getText().toString().equals("")){
            mostrarMensaje("Rellene todos los campos!");
        }else if (sistema.newUser(mail.getText().toString(), pass.getText().toString(),
                nick.getText().toString(), name.getText().toString(),
                date.getText().toString())){
                // Si el login es correcto, vamos a la pantalla principal.
            Intent i = new Intent(Registrar.this, PantallaPrincipal.class);
            i.putExtra("usuario",mail.getText().toString());
            sistema.desConecta();
            startActivity(i);
        }else{
            // Si ya hay un usuario registrado, se muestra el mensaje.
            mostrarMensaje("Ya existe un usuario con ese correo!");
        }
        sistema.conecta();
    }

    /*
    * Método que muestra un mensaje por pantalla.
     */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(Registrar.this,mensaje,Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(Registrar.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(Registrar.this, BusquedaAvanzada.class));
        } else if (id == R.id.login) {
            startActivity(new Intent(Registrar.this, Login.class));
        } else if (id == R.id.registrar) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
