package trelligen.app.cine.objeto;

import android.util.Log;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.transform.Result;

/**
 * Clase que encapsula una serie de información vista como un cursor.
 */
public class Cursor implements Runnable {
    private ResultSet rst;
    private boolean hasNext=false;
    public Cursor(ResultSet rst) {
        this.rst = rst;
    }

    public ResultSet getRst() {
        return rst;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public void run() {
        try {
            hasNext=rst.next();
        } catch (SQLException e) {
            Log.d("Resultset",e.toString());
        }
    }
}
