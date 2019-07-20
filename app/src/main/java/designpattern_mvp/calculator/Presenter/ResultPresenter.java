package designpattern_mvp.calculator.Presenter;

import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

import javax.inject.Inject;

import designpattern_mvp.calculator.Model.NumbersModel;
import designpattern_mvp.calculator.Model.NumbersModelInterface;
import designpattern_mvp.calculator.View.AdditionViewInterface;

import static designpattern_mvp.calculator.Extensions.Messages.EMPTY_FIELDS_MESSAGE;
import static designpattern_mvp.calculator.Extensions.StringExtensions.DOUBLE_LINE;
import static designpattern_mvp.calculator.Extensions.StringExtensions.NEW_LINE;
import static designpattern_mvp.calculator.Extensions.StringExtensions.isNullOrEmpty;
import static java.lang.Integer.parseInt;

public class ResultPresenter implements ResultPresenterInterface {

    private AdditionViewInterface additionView;
    private NumbersModelInterface numbersModel;

    public void setEmptyEditTexts(EditText... textValues) {
        for (EditText text : textValues) {
            text.setText("");
        }
    }

    public void writeToTextField(Button pressedButton, EditText editTextToChange){
        if(editTextToChange != null) {
            String concatenatedText = "" + editTextToChange.getText().toString() + pressedButton.getText().toString();
            editTextToChange.setText(concatenatedText);
        }
    }

    @Override
    public String multipleResult(String number1, String number2) {
        String result = EMPTY_FIELDS_MESSAGE;

        DecimalFormat df = new DecimalFormat("#.##");

        if(!isNullOrEmpty(number1) && !isNullOrEmpty(number2)) {
            double nr1 = Double.valueOf(number1), nr2 = Double.valueOf(number2);

            result = "Addition: nr1 + nr2" + NEW_LINE + (nr1 + nr2) + DOUBLE_LINE;
            result += "Subtraction: nr1 - nr2" + NEW_LINE + (nr1 - nr2) + DOUBLE_LINE;
            result += "Multiplication: nr1 * nr2" + NEW_LINE + (nr1 * nr2) + DOUBLE_LINE;
            result += "Division: nr1 / nr2" + NEW_LINE + (nr1 / nr2) + DOUBLE_LINE;
            result += "Percentage: nr1 % nr2" + NEW_LINE + (nr1 % nr2) + DOUBLE_LINE;
        }

        return result;
    }
}
