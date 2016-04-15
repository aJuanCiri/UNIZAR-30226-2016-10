package trelligen.app.cine.pantalla;

/**
 * Actividad que muestra por pantalla la información de perfil de un usuario
 * con opción a editarla mediante un botón.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Sistema;
import trelligen.app.cine.objeto.Usuario;

public class EditarPerfil extends Activity {

    private Button save;
    private TextView nick, mail, name, date;
    private EditText pass, newPass1, newPass2;
    private Sistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_editar);
        sistema = new Sistema(getApplicationContext());
        cargarInformacionPerfil("usuario@gmail.com");
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
    }

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

    private void actualizarPass(Sistema sis, String mail, String pass,
                                        String newPass1, String newPass2){
        if(!newPass1.equals("") && sis.updatePass(mail,pass,newPass1,newPass2)){
            mostrarMensaje("guardando...");
        } else{
            mostrarMensaje("Las contraseñas no coinciden.");
        }
    }

    private void mostrarMensaje(String mensaje){
        Toast.makeText(EditarPerfil.this,mensaje,Toast.LENGTH_LONG).show();
    }
}