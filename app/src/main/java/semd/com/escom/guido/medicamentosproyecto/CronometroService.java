package semd.com.escom.guido.medicamentosproyecto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CronometroService extends Service {
    public CronometroService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
