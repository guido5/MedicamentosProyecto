package semd.com.escom.guido.medicamentosproyecto;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class Notificacion {

    public static final int NEW_MEDICAMENTO_ADD = 1;
    public static final int NEW_MEDICAMENTO_TIME_END = 2;
    public static final int MEDICAMENTO_MODIFY = 3;
    private final String NEW_MEDICAMENTO_ADD_TITLE = "Nuevo medicamento agregado ";
    private final String NEW_MEDICAMENTO_ADD_TEXT = "Se agrego un nuevo medicamento y el tiempo para tu siguiente dosis ha iniciado ";
    private final String NEW_MEDICAMENTO_TIME_END_TITLE = "ALETA!! ES HORA DE TU MEDICAMENTO ";
    private final String NEW_MEDICAMENTO_TIME_END_TEXT = "Debes tomar tu medicamento ";
    private final String MEDICAMENTO_MODIFY_TITLE = "Modificacion de medicamento ";
    private final String MEDICAMENTO_MODIFY_TEXT = "Los datos del medicamento cambiaron ";
    NotificationManagerCompat manager;
    NotificationCompat.Builder notificacion;
    Context context;

    public Notificacion(Context context, int code){
        this.context = context;
        manager = NotificationManagerCompat.from(context);
        switch (code){
            case NEW_MEDICAMENTO_ADD:
                creaNotificacion(NEW_MEDICAMENTO_ADD_TITLE, NEW_MEDICAMENTO_ADD_TEXT);
                break;
            case NEW_MEDICAMENTO_TIME_END:
                creaNotificacion(NEW_MEDICAMENTO_TIME_END_TITLE, NEW_MEDICAMENTO_TIME_END_TEXT);
                break;
            case MEDICAMENTO_MODIFY:
                creaNotificacion(MEDICAMENTO_MODIFY_TITLE, MEDICAMENTO_MODIFY_TITLE);
                break;
            default:
                break;
        }
    }

    private void creaNotificacion(String title, String text){
        notificacion =new NotificationCompat.Builder(context, "normal")
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_MAX);
                //.setContentIntent(resultPendingIntent)
                //.setAutoCancel(true);
    }

    public void dispararNotificacion(){
        manager.notify();
    }
}


