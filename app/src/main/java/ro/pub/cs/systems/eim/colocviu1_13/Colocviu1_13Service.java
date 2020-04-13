package ro.pub.cs.systems.eim.colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviu1_13Service extends Service {
    public Colocviu1_13Service() {
    }

    private String cardnialsClicked = "cardinalsClicked";
    private String buttonsClicked = "";

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        buttonsClicked = intent.getStringExtra(cardnialsClicked);
        processingThread = new ProcessingThread(this, buttonsClicked);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
