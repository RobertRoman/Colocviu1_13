package ro.pub.cs.systems.eim.colocviu1_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    private EditText buttonsPressed;
    private Button registerBtn, cancelBtn;

    private String cardnialsClicked = "cardinalsClicked";
    private String buttonsClicked;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.register:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        buttonsPressed = (EditText)findViewById(R.id.buttons_pressed);

        Intent intent = getIntent();
        if (intent != null) {
            buttonsClicked = intent.getStringExtra(cardnialsClicked);
            buttonsPressed.setText(buttonsClicked);
        }


        registerBtn = (Button)findViewById(R.id.register);
        cancelBtn = (Button)findViewById(R.id.cancel);

        registerBtn.setOnClickListener(buttonClickListener);
        cancelBtn.setOnClickListener(buttonClickListener);
    }
}
