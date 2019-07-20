package designpattern_mvp.calculator.main;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import designpattern_mvp.calculator.Extensions.Modules;
import designpattern_mvp.calculator.Presenter.ResultPresenterInterface;
import designpattern_mvp.calculator.R;
import designpattern_mvp.calculator.View.AdditionViewInterface;
import toothpick.Scope;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity implements AdditionViewInterface {

    @Inject ResultPresenterInterface resultPresenter;
    @BindView(R.id.number1Show) EditText nr1;
    @BindView(R.id.number2Show) EditText nr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(Modules.getInstancesPerLifetimeScope());
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, scope);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        nr1.setInputType(InputType.TYPE_NULL);
        nr2.setInputType(InputType.TYPE_NULL);
    }

    public void showResultInSecondScreen_Click(View v){
        Intent goToSecondPage = new Intent(getBaseContext(), SecondActivity.class);

        goToSecondPage.putExtra("resultKey", resultPresenter.multipleResult(nr1.getText().toString(),
                nr2.getText().toString()));

        startActivity(goToSecondPage);
    }

    public void writeData_Click(View v) {
        resultPresenter.writeToTextField((Button) v, (EditText) getCurrentFocus());
    }

    public void clearInput_Click(View v) {
        resultPresenter.setEmptyEditTexts(nr1, nr2);
    }
}
