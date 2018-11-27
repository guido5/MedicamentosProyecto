package semd.com.escom.guido.medicamentosproyecto;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class CronometroService extends Service {
    private static final int FINAL = 0;
    String checkpoints;
    String inits;
    String num_days;
    public CronometroService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        checkpoints = bundle.getString(DatabaseSchema.Medicamentos.COLUMN_NAME_CHECKPOINT);
        inits = bundle.getString(DatabaseSchema.Medicamentos.COLUMN_NAME_INIT_FECHA);
        num_days = bundle.getString(DatabaseSchema.Medicamentos.COLUMN_NAME_CUANTOS_DIAS);
        System.out.print(checkpoints);
        System.out.print(inits);
        System.out.print(num_days);
        return FINAL;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
