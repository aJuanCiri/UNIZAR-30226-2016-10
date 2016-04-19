package trelligen.app.cine.objeto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Clase que muestra la imagen de una película obteniéndola
 * a partir de su url.
 */
public class MostrarImagen implements Runnable {

    private String url;
    private Bitmap imagen;

    /*
    * Constructor del objeto.
     */
    public MostrarImagen(String url) {
        this.url = url;
        imagen = null;
    }

    /*
    * Método que se ejecutará al crear el thread para obtener la imagen.
     */
    public void run() {
        try {
            URL enlace = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)enlace.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        } catch(MalformedURLException e) {


        } catch(IOException e) {

        }
    }

    /*
    * Método que devuelve la imagen obtenida.
     */
    public Bitmap getImagen() {
        return imagen;
    }
}
