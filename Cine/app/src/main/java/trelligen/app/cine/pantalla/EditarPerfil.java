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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;
import trelligen.app.cine.objeto.Usuario;

/**
 * Actividad que muestra por pantalla la información de perfil de un usuario
 * con opción a editarla mediante un botón.
 */
public class EditarPerfil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button save;    // Botón para guardar los datos modificados.
    private TextView nick, mail, name, date;    // Textos a modificar.
    private EditText pass, newPass1, newPass2;  // Textos a modificar.
    private Sistema sistema;    // Objeto sistema para controlar la interacción.
    private String usuario; // Usuario en curso.

    /*
    * Método que carga la información del usuario y guarda los cambios
    * realizados.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Muestra la pantalla.
        setContentView(R.layout.activity_pantalla_editarperfil);
        // Crea una instancia del objeto sistema.
        sistema = new Sistema(getApplicationContext());
        // Obtiene el usuario en curso.
        usuario = getIntent().getExtras().getString("usuario");
        cargarInformacionPerfil(usuario);   // Carga la info del usuario.

        // Botón para guardar la información de un usuario.
        save = (Button)findViewById(R.id.profile_editbutton);
        save.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                // Actualiza la información del usuario.
                sistema.updateUser(mail.getText().toString(),
                        nick.getText().toString(),name.getText().toString(),
                        date.getText().toString());
                if(!pass.getText().toString().equals("")){
                    // Actualiza la contraseña del usuario.
                    actualizarPass(sistema,mail.getText().toString(),pass.getText().toString(),
                            newPass1.getText().toString(),newPass2.getText().toString());
                } else{
                    mostrarMensaje("guardando...");
                }
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
    * Método para mostrar la información del usuario por pantalla.
     */
    private void cargarInformacionPerfil(String user_mail){
        nick = (TextView) findViewById(R.id.profile_username);
        mail = (TextView) findViewById(R.id.profile_mail);
        name = (TextView) findViewById(R.id.profile_nombre);
        date = (TextView) findViewById(R.id.profile_fnacimiento);
        pass = (EditText) findViewById(R.id.profile_pass);
        newPass1 = (EditText) findViewById(R.id.profile_newPass1);
        newPass2 = (EditText) findViewById(R.id.profile_newPass2);

        // Obtiene el usuario.
        Usuario usuario = sistema.getUserInfo(user_mail);

        // Muestra la información del usuario.
        nick.setText(usuario.getNick());
        mail.setText(usuario.getMail());
        name.setText(usuario.getName());
        date.setText(usuario.getNacimiento());
    }

    /*
    * Método que actualiza la contraseña del usuario.
     */
    private void actualizarPass(Sistema sis, String mail, String pass,
                                        String newPass1, String newPass2){
        if(!newPass1.equals("") && sis.updatePass(mail,pass,newPass1,newPass2)){
            mostrarMensaje("guardando...");
        } else{
            mostrarMensaje("Las contraseñas no coinciden.");
        }
    }

    /*
    * Método que muestra un mensaje por pantalla.
     */
    private void mostrarMensaje(String mensaje){
        Toast.makeText(EditarPerfil.this,mensaje,Toast.LENGTH_LONG).show();
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

        if (id == R.id.pantalla_principal_sesion) {
            Intent i = new Intent(EditarPerfil.this, PantallaPrincipal.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.bus_avanzada_sesion) {
            Intent i = new Intent(EditarPerfil.this, BusquedaAvanzada.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_vistas) {
            Intent i = new Intent(EditarPerfil.this, Vistas.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.mis_pendientes) {
            Intent i = new Intent(EditarPerfil.this, Pendientes.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        } else if (id == R.id.cerrar_sesion) {

        } else if (id == R.id.perfil) {
            Intent i = new Intent(EditarPerfil.this, Perfil.class);
            i.putExtra("usuario",usuario);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}