package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private EditText cardinalPoints;
    private Button northBtn, eastBtn, southBtn, westBtn, secondActivity;

    private String cardnialsClicked = "cardinalsClicked";
    private Integer buttonsClicked = 0;
    private int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private boolean once = true;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String tmp = cardinalPoints.getText().toString();
            if (!tmp.isEmpty() && view.getId() != R.id.secondary_activity) {
                tmp = tmp.concat(",");
            }

            switch(view.getId()) {
                case R.id.north:
                    tmp = tmp.concat(northBtn.getText().toString());
                    buttonsClicked++;
                    break;
                case R.id.east:
                    tmp = tmp.concat(eastBtn.getText().toString());
                    buttonsClicked++;
                    break;
                case R.id.south:
                    tmp = tmp.concat(southBtn.getText().toString());
                    buttonsClicked++;
                    break;
                case R.id.west:
                    tmp = tmp.concat(westBtn.getText().toString());
                    buttonsClicked++;
                    break;
                case R.id.secondary_activity:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                    intent.putExtra(cardnialsClicked, buttonsClicked.toString());
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
            // for excercise D
            if (buttonsClicked >= 4) {
                if (once) {
                    once = false;

                    Intent serviceIntent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                    serviceIntent.putExtra(cardnialsClicked, tmp);
                    getApplicationContext().startService(serviceIntent);

                }
            }

            cardinalPoints.setText(tmp);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        cardinalPoints = (EditText)findViewById(R.id.cardinal);

        northBtn = (Button)findViewById(R.id.north);
        eastBtn = (Button)findViewById(R.id.east);
        southBtn = (Button)findViewById(R.id.south);
        westBtn = (Button)findViewById(R.id.west);
        secondActivity = (Button)findViewById(R.id.secondary_activity);

        northBtn.setOnClickListener(buttonClickListener);
        eastBtn.setOnClickListener(buttonClickListener);
        southBtn.setOnClickListener(buttonClickListener);
        westBtn.setOnClickListener(buttonClickListener);
        secondActivity.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            cardinalPoints.setText(savedInstanceState.getString(cardnialsClicked));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(cardnialsClicked, String.valueOf(buttonsClicked));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        cardinalPoints.setText(savedInstanceState.getString(cardnialsClicked));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Registered!", Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver , new IntentFilter("BROADCAST_ACTION"));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
