package ro.pub.cs.systems.eim.colocviu1_13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BROADCAST_RECEIVER_TAG", intent.getStringExtra("BROADCAST_RECEIVER_EXTRA"));
    }

}