package designpattern_mvp.calculator.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import designpattern_mvp.calculator.Presenter.AdditionPresenter;
import designpattern_mvp.calculator.R;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private AdditionPresenter additionView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            EditText nr1 = findViewById(R.id.number1Show);
            EditText nr2 = findViewById(R.id.number2Show);

            additionView = new AdditionPresenter();
            switch (item.getItemId()) {
                case R.id.history:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.generate_results:
                    mTextMessage.setText(R.string.title_dashboard);
                    additionView.multipleResult(Double.valueOf(nr1.getText().toString()), Double.valueOf(nr2.getText().toString()));
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

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @SuppressLint("DefaultLocale")
    public void ShowData_Click(View v) {
      // digit1 = findViewById(R.id.Digit1);
    }

}
