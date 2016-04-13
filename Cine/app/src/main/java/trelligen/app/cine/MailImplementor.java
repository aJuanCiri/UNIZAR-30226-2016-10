package trelligen.app.cine;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Javier on 13/04/16.
 */
public class MailImplementor implements SendImplementor{

    /** actividad desde la cual se abrira la actividad de gestión de correo */
    private Activity sourceActivity;

    /** Constructor
     * @param source actividad desde la cual se abrira la actividad de gestion de correo
     */
    public MailImplementor(Activity source){
        setSourceActivity(source);
    }

    /**  Actualiza la actividad desde la cual se abrira la actividad de gestion de correo */
    public void setSourceActivity(Activity source) {
        sourceActivity = source;
    }

    /**  Recupera la actividad desde la cual se abrira la actividad de gestion de correo */
    public Activity getSourceActivity(){
        return sourceActivity;
    }

    /**
     * Implementacion del metodo send utilizando la aplicacion de gestion de correo de Android
     * Solo se copia el asunto y el cuerpo
     * @param subject asunto
     * @param body cuerpo del mensaje
     */
    public void send (String mail, String newPass) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mail});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nueva contraseña");
        emailIntent.setType("plain/text");
        String body = "Su nueva contraseña es " + newPass;
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        getSourceActivity().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
