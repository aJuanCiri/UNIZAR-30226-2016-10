package trelligen.app.cine.pantalla;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;
import trelligen.app.cine.objeto.Sistema;

/**
 * Muestra los resultados a partir de un Array de películas enviado.
 */
public class Resultados extends Activity{

    private ListViewAdapter adapter;
    private Sistema sistema;
    private Button anterior, siguiente;
    private ArrayList<Pelicula> listaRecibida;
    private int numResultados;
    private final int RESULTADOS_POR_PAGINA = 2;
    private ListView lista;
    private int paginaActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);

        sistema = new Sistema(getApplicationContext());
        lista = (ListView) findViewById(R.id.listView1);
        anterior = (Button) findViewById(R.id.resultados_anterior);
        siguiente = (Button) findViewById(R.id.resultados_siguiente);
        anterior.setVisibility(View.INVISIBLE); //Al estar en la primera página, no debe mostrarse

        listaRecibida = solicitarPeliculas();

        numResultados = listaRecibida.size();
        //Si hay menos resultados que los que se muestran por página, desaparece el botón siguiente
        if(numResultados<=RESULTADOS_POR_PAGINA)siguiente.setVisibility(View.INVISIBLE);
        ArrayList<Pelicula> resultados_pagina = new ArrayList<Pelicula>(
                listaRecibida.subList(0,RESULTADOS_POR_PAGINA));

        adapter = new ListViewAdapter(this,resultados_pagina);
        lista.setAdapter(adapter);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                siguiente_pagina();
            }
        });

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                anterior_pagina();
            }
        });
    }

    private void siguiente_pagina(){
        paginaActual++;
        int indice_inicio = paginaActual*RESULTADOS_POR_PAGINA;
        int limite = indice_inicio+RESULTADOS_POR_PAGINA;
        if(limite>numResultados){
            limite = listaRecibida.size();
        }
        ArrayList<Pelicula> resultados_pagina = new ArrayList<Pelicula>(
                listaRecibida.subList(indice_inicio,limite));
        adapter = new ListViewAdapter(this,resultados_pagina);
        if(paginaActual==1){
            //Si es la segunda pagina se activa el boton anterior
            anterior.setVisibility(View.VISIBLE);
        }
        if(limite==numResultados){
            //Si es la última página, se elimina el botón siguiente
            siguiente.setVisibility(View.INVISIBLE);
        }
        lista.setAdapter(adapter);
    }

    private void anterior_pagina(){
        paginaActual--;
        int indice_inicio = paginaActual*RESULTADOS_POR_PAGINA;
        int limite = indice_inicio + RESULTADOS_POR_PAGINA;
        ArrayList<Pelicula> resultados_pagina = new ArrayList<Pelicula>(
                listaRecibida.subList(indice_inicio,limite));
        adapter = new ListViewAdapter(this,resultados_pagina);
        if(paginaActual==0){
            //Si es la primera página, se hace invisible el botón anterior
            anterior.setVisibility(View.INVISIBLE);
        }
        if(numResultados>RESULTADOS_POR_PAGINA*paginaActual && siguiente.getVisibility()==View.INVISIBLE){
            //Si no es la última página, y el botón siguiente es invisible, se hace visible
            siguiente.setVisibility(View.VISIBLE);
        }
        lista.setAdapter(adapter);
    }

    /*
        Función provisional (Reemplazar por el Array recibido)
     */
    private ArrayList<Pelicula> solicitarPeliculas(){
        ArrayList<Pelicula> resultadoTest = new ArrayList<Pelicula>();
        resultadoTest.add(new Pelicula(0,"Holmes",null,null,null,0,0,null,null,"http://www.elmulticine.com/imagenes/carteles/Sherlock-Holmes-b.jpg"));
        resultadoTest.add(new Pelicula(1,"Harry Potter",null,null,null,0,0,null,null,"http://www.elmulticine.com/imagenes/carteles/2/harry-reliq-2-pos-b.jpg"));
        resultadoTest.add(new Pelicula(2,"Iron Man",null,null,null,0,0,null,null,"http://i.blogs.es/71ed48/iron-20man-203-20poster-20final/original.jpg"));
        resultadoTest.add(new Pelicula(3,"Intocable",null,null,null,0,0,null,null,"http://www.labutaca.net/imagenes/wp-content/original/2012_03/intocable-cartel-1.jpg"));
        resultadoTest.add(new Pelicula(4,"Shutter Island",null,null,null,0,0,null,null,"http://www.labutaca.net/peliculas/wp-content/uploads/2010/01/shutter-island-cartel.jpg"));
        resultadoTest.add(new Pelicula(5,"Atrápame si puedes",null,null,null,0,0,null,null,"http://es.web.img3.acsta.net/medias/nmedia/18/68/88/61/20071858.jpg"));
        resultadoTest.add(new Pelicula(6,"Torrente 4",null,null,null,0,0,null,null,"http://images2.coveralia.com/dvd/t/Torrente_4_Crisis_Letal-Cartel.jpg"));
        /*ArrayList<Pelicula> resultadoTest = sistema.buscarPeliculas(nombrePeli,fecha, director,
                duracion,categoria,valoracion,publico);*/
        return resultadoTest;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);
        return true;
    }
}