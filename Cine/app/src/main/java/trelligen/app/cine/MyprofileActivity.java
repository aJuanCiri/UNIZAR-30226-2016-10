package trelligen.app.cine;

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
import android.widget.Toast;

public class MyprofileActivity extends Activity {

    private Button editbutton;
    private TextView nick, mail, name, date;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPerfil("usuario@gmail.com");
        editbutton = (Button)findViewById(R.id.profile_editbutton);
        editbutton.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(MyprofileActivity.this, EditprofileActivity.class));
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