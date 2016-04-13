package trelligen.app.cine;

/**
 * Created by Guillermo on 08/04/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends Activity {

    private Button registrarse;
    private EditText mail;
    private EditText pass;
    private EditText nick;
    private EditText name;
    private EditText date;
    private CheckBox licencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarpantalla);


        registrarse = (Button)findViewById(R.id.registrarse);
        registrarse.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                verificar_registro();
            }
        });
    }

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

    private void mostrarMensaje(String mensaje){
        Toast.makeText(RegistrarActivity.this,mensaje,Toast.LENGTH_LONG).show();
    }
}