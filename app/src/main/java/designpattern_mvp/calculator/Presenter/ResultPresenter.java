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

public class ResultPresenter extends Calculation implements ResultPresenterInterface {

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
        StringBuilder result = new StringBuilder(EMPTY_FIELDS_MESSAGE);

        if(!isNullOrEmpty(number1) && !isNullOrEmpty(number2))
        {
            result = calculatedString(Double.valueOf(number1), Double.valueOf(number2));
        }

        return result.toString();
    }

}
