package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private EditText cardinalPoints;
    private Button northBtn, eastBtn, southBtn, westBtn, secondActivity;

    private String cardnialsClicked = "cardinalsClicked";
    private Integer buttonsClicked = 0;

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

}
