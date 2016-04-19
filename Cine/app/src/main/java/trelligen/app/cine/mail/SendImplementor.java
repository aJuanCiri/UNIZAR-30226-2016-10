package trelligen.app.cine.mail;

import android.app.Activity;

/**
 * Interfaz para el envío de correos.
 */
public interface SendImplementor {

    /*
    * Actualiza la actividad desde la cual se abrira la actividad de envio de notas.
    */
    public void setSourceActivity(Activity source);

    /*
    * Recupera la actividad desde la cual se abrira la actividad de envio de notas.
    * */
    public Activity getSourceActivity();

    /*
    * Permite lanzar la actividad encargada de gestionar el envio de notas
    * */
    public void send(String subject, String body);

}
