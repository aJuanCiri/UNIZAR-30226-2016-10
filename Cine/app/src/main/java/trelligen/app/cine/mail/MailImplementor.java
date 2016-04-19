package trelligen.app.cine.mail;

import android.app.Activity;
import android.content.Intent;

import trelligen.app.cine.mail.SendImplementor;

/**
 * Clase que se encarga de enviar un email al soporte de la aplicación.
 */
public class MailImplementor implements SendImplementor {

    // Actividad desde la cual se abrirá la actividad de gestión de correo.
    private Activity sourceActivity;

    /*
    * Constructor del objeto.
     */
    public MailImplementor(Activity source){
        setSourceActivity(source);
    }

    /*
    * Actualiza la actividad desde la cual se abrirá
    * la actividad de gestión de correo.
    */
    public void setSourceActivity(Activity source) {
        sourceActivity = source;
    }

    /*
    * Recupera la actividad desde la cual se abrirá
    * la actividad de gestión de correo
    */
    public Activity getSourceActivity(){
        return sourceActivity;
    }

    /*
     * Implementacion del metodo send utilizando la aplicacion de gestion de correo de Android
     */
    public void send (String mail, String newPass) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mail});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nueva contraseña");
        emailIntent.setType("plain/text");
        String body = "Desearía que mi nueva contraseña fuera: ";
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        getSourceActivity().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
