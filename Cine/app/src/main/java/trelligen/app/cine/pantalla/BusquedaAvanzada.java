package trelligen.app.cine.pantalla;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Created by Jorge on 15/04/2016.
 */
public class BusquedaAvanzada extends Activity {

    private double valoracion;
    private String genero;
    private String publico;
    private String titulo;
    private String fecha;
    private String director;
    private int duracion;



    private Button busqueda;
    private Sistema sistema;

    private RadioGroup generoGR;
    private RadioGroup publicoGR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada);
        sistema = new Sistema(getApplicationContext());

        busqueda = (Button)findViewById(R.id.busqueda);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarParametros();
                ArrayList<Pelicula> array=sistema.buscarPeliculas(titulo,fecha,director,duracion,genero,valoracion,publico);
                TextView text = (TextView)findViewById(R.id.result);
                titulo="";
                for(int i=0;i<array.size();i++) {
                    titulo = titulo+"\n"+array.get(i).getTitulo();
                }
                text.setText(titulo);
            }
        });

    }

    /*
     * Asigna los valores a las variables en función de los datos introducidos
     */
    private void asignarParametros() {
        titulo = ((TextView) findViewById(R.id.titulo)).getText().toString();
        if(titulo.equals("") || titulo.equals("Titulo")) {
            titulo = null;
        }
        director = ((TextView) findViewById(R.id.director)).getText().toString();
        if(director.equals("")) {
            director = null;
        }
        fecha = ((TextView) findViewById(R.id.fecha)).getText().toString();
        if(fecha.equals("")) {
            fecha = null;
        }
        String duracionTexto = ((TextView) findViewById(R.id.duracion)).getText().toString();
        if(duracionTexto.equals("")) {
            duracion = -1;
        } else {
            duracion = Integer.parseInt(duracionTexto);
        }
        obtenerGenero();
        obtenerPublico();
        valoracion = ((RatingBar)findViewById(R.id.valoracion)).getRating();
    }

    /*
     * Obtiene el genero introducido
     */
    private void obtenerGenero() {
        generoGR = (RadioGroup)findViewById(R.id.genero);
        int id = generoGR.getCheckedRadioButtonId();
        switch(id) {
            case R.id.accion:
                genero = "Accion";
            case R.id.drama:
                genero = "Drama";
            case R.id.lucha:
                genero = "Lucha";
            case R.id.terror:
                genero = "Terror";
            case R.id.comedia:
                genero = "Comedia";
            case R.id.fantasia:
                genero = "Fantasia";
            case R.id.suspense:
                genero = "Suspense";
            case R.id.musical:
                genero = "Musical";
            case R.id.cficcion:
                genero = "C.Ficcion";
            default:
                genero = null;
        }
    }

    /*
     * Obtiene el publico introducido
     */
    private void obtenerPublico() {
        publicoGR = (RadioGroup)findViewById(R.id.publico);
        int id = publicoGR.getCheckedRadioButtonId();
        switch(id) {
            case R.id.infantil:
                publico = "Infantil";
            case R.id.juvenil:
                publico = "Juvenil";
            case R.id.familiar:
                publico = "Familiar";
            case R.id.adulto:
                publico = "Adulto";
            default:
                publico = null;
        }
    }
}
