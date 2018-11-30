package semd.com.escom.guido.medicamentosproyecto;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


import static java.lang.Thread.sleep;

public class CronometroService extends IntentService {
    private static final int FINAL = 0;
    String checkpoints;
    String inits;
    String num_days;
    public CronometroService() {
        super("CronometroService");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle = intent.getExtras();
        checkpoints = bundle.getString(DatabaseSchema.Medicamentos.COLUMN_NAME_CHECKPOINT);
        inits = bundle.getString(DatabaseSchema.Medicamentos.COLUMN_NAME_INIT_FECHA);
        num_days = bundle.getString(DatabaseSchema.Medicamentos.COLUMN_NAME_CUANTOS_DIAS);
        String[] init_data = inits.split(" ");
        int cont = 0;
        int check = Integer.parseInt(checkpoints);
        int dias = Integer.parseInt(num_days);
        //long time = 3600000*check;
        long time = 1000*check;
        while(((dias*24)/check) >= cont){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cont += 1;
            Notificacion notificacion = new Notificacion(this, Notificacion.NEW_MEDICAMENTO_TIME_END);
            notificacion.dispararNotificacion(cont);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Termino el servicio", Toast.LENGTH_SHORT).show();
    }
}
