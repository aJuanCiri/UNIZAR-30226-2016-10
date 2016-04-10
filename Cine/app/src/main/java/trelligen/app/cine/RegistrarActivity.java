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
import android.widget.TextView;
import android.widget.EditText;

public class RegistrarActivity extends Activity {

    private Button boton;
    private EditText edit;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarpantalla);


        boton = (Button)findViewById(R.id.registrarse);


        boton.setOnClickListener( new OnClickListener() {
            public void onClick(View view){
                String mensaje = edit.getText().toString();
                text.setText(String.valueOf(mensaje.length()));
            }
        });
    }
}