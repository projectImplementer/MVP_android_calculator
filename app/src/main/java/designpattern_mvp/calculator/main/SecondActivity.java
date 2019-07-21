package designpattern_mvp.calculator.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import designpattern_mvp.calculator.R;

public class SecondActivity extends AppCompatActivity {
    private static final int MAIN_ACTIVITY_REQUEST_CODE = 0;

    @BindView (R.id.showResults) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        textView.setMovementMethod(new ScrollingMovementMethod());

        String savedExtra = getIntent().getStringExtra("resultKey");
        textView.setText(savedExtra);
    }

}
