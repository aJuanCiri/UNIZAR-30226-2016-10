package trelligen.app.cine.pantalla;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import trelligen.app.cine.R;
import trelligen.app.cine.objeto.MostrarImagen;
import trelligen.app.cine.objeto.Pelicula;

/**
 * Clase que muestra una serie de películas por pantalla.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    Pelicula[][] peliculas; // Array de películas.
    LayoutInflater inflater;

    /*
    * Método que obtiene las películas de cada fila.
     */
    public ListViewAdapter(Context context, ArrayList<Pelicula> listaRecibida) {
        this.context = context;
        peliculas = new Pelicula[(listaRecibida.size()+1)/2][2];
        int i=0,j=0;    // Índices para recorrer el array.
        while(listaRecibida.size()>j){  // Recorre la lista de películas y las obtiene.
            peliculas[i][0] = listaRecibida.get(j);
            if(listaRecibida.size()>j+1) {  //Comprueba que haya una segunda pelicula para esta fila.
                peliculas[i][1] = listaRecibida.get(j + 1);
            }
            i++; j+=2;
        }
    }

    /*
    * Método que devuelve el número de filas de películas.
     */
    @Override
    public int getCount() {
        return peliculas.length;
    }

    /*
    * Método que devuelve la película de una posición.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /*
    * Método que obtiene el índice del elemento de una posición dada.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
    * Método que muestra las películas por pantalla, indicando dos por fila.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        // Variables para las dos películas de cada fila.
        TextView txtTitle1,txtTitle2;
        ImageView imgImg1,imgImg2;

        // Prepara la pantalla para mostrar las películas.
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Número de películas a mostrar.
        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        // Obtiene los elementos de la pantalla.
        txtTitle1 = (TextView) itemView.findViewById(R.id.resultado_titulo1);
        imgImg1 = (ImageView) itemView.findViewById(R.id.resultado_imagen1);
        txtTitle2 = (TextView) itemView.findViewById(R.id.resultado_titulo2);
        imgImg2 = (ImageView) itemView.findViewById(R.id.resultado_imagen2);

        // Comprueba si existen películas para esa posición y las muestra u oculta.
        if(peliculas[position][0]==null){
            imgImg1.setVisibility(View.INVISIBLE);
            txtTitle1.setVisibility(View.INVISIBLE);
        }else {
            txtTitle1.setText(peliculas[position][0].getTitulo());
            imgImg1.setImageBitmap(mostrarImagen(peliculas[position][0].getURL()));
        }
        if(peliculas[position][1]==null) {
            imgImg2.setVisibility(View.INVISIBLE);
            txtTitle2.setVisibility(View.INVISIBLE);
        }else {
            txtTitle2.setText(peliculas[position][1].getTitulo());
            imgImg2.setImageBitmap(mostrarImagen(peliculas[position][1].getURL()));
        }
        return itemView;
    }

    /*
    * Método que muestra las imágenes por pantalla.
     */
    private Bitmap mostrarImagen(String url){
        ImageView imagen;
        MostrarImagen mostrar = new MostrarImagen(url);
        Thread t = new Thread(mostrar);
        t.start();
        try {
            t.join();
        }catch(Exception e) {

        }
       return  mostrar.getImagen();
    }
}