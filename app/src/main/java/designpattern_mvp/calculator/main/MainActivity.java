package designpattern_mvp.calculator.main;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import designpattern_mvp.calculator.Presenter.ResultPresenter;
import designpattern_mvp.calculator.R;
import designpattern_mvp.calculator.View.AdditionViewInterface;

public class MainActivity extends AppCompatActivity implements AdditionViewInterface {
    private ResultPresenter resultPresenterView;
    EditText nr1;
    EditText nr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultPresenterView = new ResultPresenter(this);

        nr1 = findViewById(R.id.number1Show);
        nr2 = findViewById(R.id.number2Show);

        nr1.setInputType(InputType.TYPE_NULL);
        nr2.setInputType(InputType.TYPE_NULL);
    }

    public void writeData_Click(View v) {
        resultPresenterView.writeToTextField((Button) v, (EditText) getCurrentFocus());
    }

    public void clearInput_Click(View v) {
        resultPresenterView.setEmptyEditTexts(nr1, nr2);
    }
}
