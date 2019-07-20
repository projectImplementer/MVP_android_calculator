package designpattern_mvp.calculator.Presenter;

import android.widget.Button;
import android.widget.EditText;

public interface ResultPresenterInterface {

    String multipleResult(String number1, String number2);
    void writeToTextField(Button pressedButton, EditText editTextToChange);
    void setEmptyEditTexts(EditText... textValues);
}
