package designpattern_mvp.calculator.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        String s = "";
        View et = getCurrentFocus();
        EditText e = (EditText) et;
        s += e.getText().toString() + ((Button) v).getText().toString();
        e.setText(s); //TODO: set logic in presenter
    }

    public void clearInput_Click(View v) {
        resultPresenterView.setEmptyEditTexts(nr1, nr2);
    }



    @Override
    public double[] multipleResult(double number1, double number2) {
        return new double[]
                {
                        number1 + number2,
                        number1 - number2,
                        number1 * number2,
                        number1 / number2,
                        number1 % number2
                };
    }

    @Override
    public void getNumber1() {

    }
}
