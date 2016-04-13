package trelligen.app.cine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button iniciar_sesion;
    TextView no_pass;
    TextView no_cuenta;
    EditText mail;
    TextView pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpantalla);
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
                startActivity(new Intent(LoginActivity.this, RegistrarActivity.class));
            }
        });
    }

    private void verificar_sesion(){
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        Sistema sistema = new Sistema(getApplicationContext());
        if(!mail.getText().toString().equals("") && !pass.getText().toString().equals("") &&
                sistema.login(mail.getText().toString(), pass.getText().toString())){
            mostrarMensaje("Login correcto!");
        }else{
            mostrarMensaje("Login fallido!");
        }
    }

    private void recuperar_pass(){
        mail = (EditText) findViewById(R.id.email);
        Log.d("Prueba", "recuperar pass");
        if(!mail.getText().toString().equals("")){
            // Código que haga lo siguiente: (Implementar en clase sistema y bases de datos, no aqui)
            // 1) Comprobar que existe el mail
            // 2) Enviar un e-mail con la contraseña a ese correo
        }else{
            mostrarMensaje("Introduce tu e-mail para recuperarla");
        }
    }

    private void mostrarMensaje(String mensaje){
        Toast.makeText(LoginActivity.this,mensaje,Toast.LENGTH_LONG).show();
    }
}
