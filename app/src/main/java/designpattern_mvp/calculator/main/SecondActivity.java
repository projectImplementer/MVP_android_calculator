package designpattern_mvp.calculator.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import designpattern_mvp.calculator.R;

public class SecondActivity extends AppCompatActivity {
    private static final int MAIN_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String returnString = data.getStringExtra("resultKey");

                TextView textView = findViewById(R.id.showResults);

                textView.setText(returnString);
            }
        }
    }
}
