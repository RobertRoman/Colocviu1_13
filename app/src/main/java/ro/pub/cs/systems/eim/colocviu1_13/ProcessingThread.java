package ro.pub.cs.systems.eim.colocviu1_13;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private String cardinalPoints;
    public ProcessingThread(Context context, String buttonsClicked) {
        this.context = context;
        cardinalPoints = buttonsClicked;
    }

    @Override
    public void run() {
        Log.d("PROCESSING_THREAD_TAG", "Thread has started! PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        sendMessage();
        sleep();
        Log.d("PROCESSING_THREAD_TAG", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.putExtra("BROADCAST_RECEIVER_EXTRA",
                new Date(System.currentTimeMillis()) + " " + cardinalPoints);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}