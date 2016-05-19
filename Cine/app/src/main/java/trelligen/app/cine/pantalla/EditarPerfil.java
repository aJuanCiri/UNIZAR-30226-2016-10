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
    private String usuario;

    /*
    * Método que carga la información del usuario y guarda los cambios
    * realizados.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_editarperfil);
        sistema = new Sistema(getApplicationContext());
        usuario = getIntent().getExtras().getString("usuario");
        cargarInformacionPerfil(usuario);
        save = (Button)findViewById(R.id.profile_editbutton);
        save.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                sistema.updateUser(mail.getText().toString(),
                        nick.getText().toString(),name.getText().toString(),
                        date.getText().toString());
                if(!pass.getText().toString().equals("")){
                    actualizarPass(sistema,mail.getText().toString(),pass.getText().toString(),
                            newPass1.getText().toString(),newPass2.getText().toString());
                } else{
                    mostrarMensaje("guardando...");
                }
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

        Usuario usuario = sistema.getUserInfo(user_mail);
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