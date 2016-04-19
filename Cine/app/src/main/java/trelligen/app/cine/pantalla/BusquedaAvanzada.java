package trelligen.app.cine.pantalla;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    int pulsadoId=-1;

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
        Button borrarG = (Button)findViewById(R.id.eliminarG);
        borrarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioGroup)findViewById(R.id.genero)).clearCheck();
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
        generoGR = (RadioGroup)findViewById(R.id.genero);
        int id = generoGR.getCheckedRadioButtonId();
       /* if(id!=-1) {
            genero = ((RadioButton) findViewById(id))
                    .getText().toString();
        } else {
            genero = null;
        }*/
        switch(id) {
            case R.id.accion:
                genero = "Accion";
                break;
            case R.id.drama:
                genero = "Drama";
                break;
            case R.id.lucha:
                genero = "Lucha";
                break;
            case R.id.terror:
                genero = "Terror";
                break;
            case R.id.comedia:
                genero = "Comedia";
                break;
            case R.id.fantasia:
                genero = "Fantasia";
                break;
            case R.id.suspense:
                genero = "Suspense";
                break;
            case R.id.musical:
                genero = "Musical";
                break;
            case R.id.cficcion:
                genero = "C.Ficcion";
                break;
            default:
                genero = null;
                break;
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
