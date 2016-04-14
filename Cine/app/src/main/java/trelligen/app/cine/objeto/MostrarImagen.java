package trelligen.app.cine.objeto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jorge on 14/04/2016.
 */
public class MostrarImagen implements Runnable {

    private String url;
    private Bitmap imagen;
    public MostrarImagen(String url) {
        this.url = url;
        imagen = null;
    }

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

    public Bitmap getImagen() {
        return imagen;
    }
}
