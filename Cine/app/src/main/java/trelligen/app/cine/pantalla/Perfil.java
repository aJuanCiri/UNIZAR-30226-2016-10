package trelligen.app.cine.pantalla;

/**
 * Actividad que muestra por pantalla la información de perfil de un usuario
 * con opción a editarla mediante un botón.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;
import trelligen.app.cine.objeto.Usuario;

public class Perfil extends Activity {

    private Button editbutton;
    private TextView nick, mail, name, date;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPerfil("usuario@gmail.com");
        editbutton = (Button)findViewById(R.id.profile_editbutton);
        editbutton.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(Perfil.this, EditarPerfil.class));
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
}
