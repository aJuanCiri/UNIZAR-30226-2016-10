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
    private Button iniciar_sesion;
    private TextView no_pass;
    private TextView no_cuenta;
    private EditText mail;
    private TextView pass;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);

        iniciar_sesion = (Button) findViewById(R.id.ini_sesion);
        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                verificar_sesion();
            }
        });

        no_pass = (TextView) findViewById(R.id.no_pass);
        no_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                recuperar_pass();
            }
        });

        no_cuenta = (TextView) findViewById(R.id.no_cuenta);
        no_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                startActivity(new Intent(Login.this, Registrar.class));
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

    private void verificar_sesion(){
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        sistema = new Sistema(getApplicationContext());
        if(!mail.getText().toString().equals("") && !pass.getText().toString().equals("") &&
                sistema.login(mail.getText().toString(), pass.getText().toString())){
            mostrarMensaje("Login correcto!");
        }else{
            mostrarMensaje("Login fallido!");
        }
    }

    /*
    * Método para recuperar la contraseña del usuario.
     */
    private void recuperar_pass(){
        mail = (EditText) findViewById(R.id.email);
        if(!mail.getText().toString().equals("")){
            sistema.enviarCorreo(this,mail.getText().toString());
        }else{
            mostrarMensaje("Introduce tu e-mail para recuperarla");
        }
    }

    /*
    * Método que muestra un mensaje por pantalla.
     */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(Login.this,mensaje,Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(Login.this, Perfil.class));
        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(Login.this, Login.class));
        } else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(Login.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(Login.this, BusquedaAvanzada.class));
        } else if (id == R.id.mis_vistas) {
            startActivity(new Intent(Login.this, Vistas.class));
        } else if (id == R.id.mis_pendientes) {
            startActivity(new Intent(Login.this, Pendientes.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}