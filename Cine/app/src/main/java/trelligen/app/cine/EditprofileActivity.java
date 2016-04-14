package trelligen.app.cine;

/**
 * Actividad que muestra por pantalla la información de perfil de un usuario
 * con opción a editarla mediante un botón.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditprofileActivity extends Activity {

    private Button save;
    private TextView nick, mail, name, date;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_perfil);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPerfil("usuario@gmail.com");
        save = (Button)findViewById(R.id.profile_editbutton);
        save.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                sistema.updateUser(mail.toString(),nick.toString(),name.toString(),date.toString());
                mostrarMensaje("guardando...");
            }
        });
    }

    private void cargarInformacionPerfil(String user_mail){
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

    private void mostrarMensaje(String mensaje){
        Toast.makeText(EditprofileActivity.this,mensaje,Toast.LENGTH_LONG).show();
    }
}