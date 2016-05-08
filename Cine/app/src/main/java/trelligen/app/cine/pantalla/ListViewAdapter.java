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

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    Pelicula[][] peliculas;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<Pelicula> listaRecibida) {
        this.context = context;
        peliculas = new Pelicula[(listaRecibida.size()+1)/2][2];
        int i=0,j=0;
        while(listaRecibida.size()>j){
            peliculas[i][0] = listaRecibida.get(j);
            if(listaRecibida.size()>j+1) {  //Comprueba que haya una segunda pelicula para esta fila
                peliculas[i][1] = listaRecibida.get(j + 1);
            }
            i++; j+=2;
        }
    }

    @Override
    public int getCount() {
        return peliculas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtTitle1,txtTitle2;
        ImageView imgImg1,imgImg2;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle1 = (TextView) itemView.findViewById(R.id.resultado_titulo1);
        imgImg1 = (ImageView) itemView.findViewById(R.id.resultado_imagen1);
        txtTitle2 = (TextView) itemView.findViewById(R.id.resultado_titulo2);
        imgImg2 = (ImageView) itemView.findViewById(R.id.resultado_imagen2);

        // Capture position and set to the TextViews
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