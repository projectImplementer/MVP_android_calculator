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
    private TextView mTextMessage;
    private ResultPresenter resultPresenterView;
    EditText nr1;
    EditText nr2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.generate_results:
                    mTextMessage.setText(R.string.title_dashboard);
                    //resultPresenterView.multipleResult(Double.valueOf(nr1.getText().toString()), Double.valueOf(nr2.getText().toString()));
                    //TODO: set logic in Presenter
                    return true;
                case R.id.logs:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        resultPresenterView = new ResultPresenter(this);

        nr1 = findViewById(R.id.number1Show);
        nr2 = findViewById(R.id.number2Show);

        nr1.setInputType(InputType.TYPE_NULL);
        nr2.setInputType(InputType.TYPE_NULL);

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
