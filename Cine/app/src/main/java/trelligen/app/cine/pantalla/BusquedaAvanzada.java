package trelligen.app.cine.pantalla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private String publico;
    private String titulo;
    private String fecha;
    private String director;
    private int duracion;



    private Button busqueda;

    private RadioGroup publicoGR;
    ArrayList<String> genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada);
        busqueda = (Button)findViewById(R.id.busqueda);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genero = new ArrayList<String>();
                asignarParametros();
                Intent i = new Intent(BusquedaAvanzada.this, Resultados.class);
                i.putExtra("titulo",titulo);
                i.putExtra("fecha",fecha);
                i.putExtra("director",director);
                i.putExtra("duracion",duracion);
                i.putExtra("valoracion",valoracion);
                i.putExtra("publico",publico);
                i.putExtra("genero",genero);
                startActivity(i);
            }
        });

        Button borrarP = (Button)findViewById(R.id.eliminarP);
        borrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioGroup)findViewById(R.id.publico)).clearCheck();
            }
        });
    }

    /*
     * Asigna los valores a las variables en función de los datos introducidos
     */
    private void asignarParametros() {
        titulo = ((TextView) findViewById(R.id.titulo)).getText().toString();
        if(titulo.equals("")) {
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
        CheckBox accion = (CheckBox)findViewById(R.id.accion);
        CheckBox drama = (CheckBox)findViewById(R.id.drama);
        CheckBox lucha = (CheckBox)findViewById(R.id.lucha);
        CheckBox terror = (CheckBox)findViewById(R.id.terror);
        CheckBox comedia = (CheckBox)findViewById(R.id.comedia);
        CheckBox fantasia = (CheckBox)findViewById(R.id.fantasia);
        CheckBox suspense = (CheckBox)findViewById(R.id.suspense);
        CheckBox musical = (CheckBox)findViewById(R.id.musical);
        CheckBox cficcion = (CheckBox)findViewById(R.id.cficcion);

        if(accion.isChecked()) {
            genero.add("Accion");
        }
        if(drama.isChecked()) {
            genero.add("Drama");
        }
        if(lucha.isChecked()) {
            genero.add("Lucha");
        }
        if(terror.isChecked()) {
            genero.add("Terror");
        }
        if(comedia.isChecked()) {
            genero.add("Comedia");
        }
        if(fantasia.isChecked()) {
            genero.add("Fantasia");
        }
        if(suspense.isChecked()) {
            genero.add("Suspense");
        }
        if(musical.isChecked()) {
            genero.add("Musical");
        }
        if(cficcion.isChecked()) {
            genero.add("C.Ficcion");
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
                break;
            case R.id.juvenil:
                publico = "Juvenil";
                break;
            case R.id.familiar:
                publico = "Familiar";
                break;
            case R.id.adulto:
                publico = "Adulto";
                break;
            default:
                publico = null;
                break;
        }
    }
}
