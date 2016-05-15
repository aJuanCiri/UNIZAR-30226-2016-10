package trelligen.app.cine.objeto;

import android.util.Log;

import java.sql.ResultSet;

/**
 * Created by Jorge on 15/05/2016.
 */
public class CursorObtieneResultados implements Runnable {

    private boolean encontrado = false;
    private ResultSet rst;

    public CursorObtieneResultados(ResultSet rst) {
        this.rst = rst;
    }

    public boolean getEncontrado() {
        return encontrado;
    }

    public void run() {
        try {
            encontrado = rst.next();
            rst.close();
        } catch (Exception e){
            Log.d("EXCEPCION CURSOR OBTIENE",e.toString());
        }
    }
}
