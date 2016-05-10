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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;

/**
 * Actividad que mgestiona el registro de un nuevo usuario.
 */
public class Registrar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button registrarse;     // Botón para registrarse.
    // Campos del registro del usuario.
    private EditText mail, pass, nick, name, date;
    private CheckBox licencia;

    /*
    * Método que se activa al registrar un usuario y que controla las interacciones
    * con el mismo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registrar);

        registrarse = (Button)findViewById(R.id.registrarse);
        registrarse.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                verificar_registro();
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
    * Método que verifica el correcto registro del usuario.
     */
    private void verificar_registro(){
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        nick = (EditText) findViewById(R.id.nick);
        name = (EditText) findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        licencia = (CheckBox) findViewById(R.id.checkBox_condi);
        Sistema sistema = new Sistema(getApplicationContext());
        if(mail.getText().toString().equals("") || pass.getText().toString().equals("") ||
                nick.getText().toString().equals("") || name.getText().toString().equals("") ||
                date.getText().toString().equals("")){
            mostrarMensaje("Rellene todos los campos!");
        }
        else if(!licencia.isChecked()){
            mostrarMensaje("Acepte las condiciones de uso!");
        }
        else if (sistema.newUser(mail.getText().toString(), pass.getText().toString(),
                nick.getText().toString(), name.getText().toString(),
                date.getText().toString())){
            mostrarMensaje("Registro correcto!");
        }else{
            mostrarMensaje("Ya existe un usuario con ese correo!");
        }
    }

    /*
    * Método que muestra un mensaje por pantalla.
     */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(Registrar.this,mensaje,Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(Registrar.this, Perfil.class));
        } else if (id == R.id.cerrar_sesion) {
            startActivity(new Intent(Registrar.this, Login.class));
        } else if (id == R.id.pantalla_principal) {
            startActivity(new Intent(Registrar.this, PantallaPrincipal.class));
        } else if (id == R.id.bus_avanzada) {
            startActivity(new Intent(Registrar.this, BusquedaAvanzada.class));
        } else if (id == R.id.mis_vistas) {
            startActivity(new Intent(Registrar.this, Vistas.class));
        } else if (id == R.id.mis_pendientes) {
            startActivity(new Intent(Registrar.this, Pendientes.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
