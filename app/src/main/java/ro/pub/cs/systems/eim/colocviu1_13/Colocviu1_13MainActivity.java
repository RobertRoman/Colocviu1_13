package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private EditText cardinalPoints;
    private Button northBtn, eastBtn, southBtn, westBtn, secondActivity;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String tmp = cardinalPoints.getText().toString();
            if (!tmp.isEmpty()) {
                tmp = tmp.concat(",");
            }

            switch(view.getId()) {
                case R.id.north:
                    tmp = tmp.concat("North");
                    break;
                case R.id.east:
                    tmp = tmp.concat("East");
                    break;
                case R.id.south:
                    tmp = tmp.concat("South");
                    break;
                case R.id.west:
                    tmp = tmp.concat("West");
                    break;
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
    }
}
