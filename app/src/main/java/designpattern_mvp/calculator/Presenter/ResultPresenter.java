package designpattern_mvp.calculator.Presenter;

import android.widget.Button;
import android.widget.EditText;

import designpattern_mvp.calculator.Model.NumbersModel;
import designpattern_mvp.calculator.View.AdditionViewInterface;

import static designpattern_mvp.calculator.Extensions.Messages.EMPTY_FIELDS_MESSAGE;
import static designpattern_mvp.calculator.Extensions.StringExtensions.isNullOrEmpty;

public class ResultPresenter implements ResultPresenterInterface {

    private AdditionViewInterface additionViewInterface;
    private NumbersModel numbersModel;


    public ResultPresenter(AdditionViewInterface additionViewInterface) {
        this.numbersModel = new NumbersModel();
        this.additionViewInterface = additionViewInterface;
    }

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

        if(!isNullOrEmpty(number1) && !isNullOrEmpty(number2)) {
            double nr1 = Double.valueOf(number1), nr2 = Double.valueOf(number2);
            System.out.println("Addition:");
            result = (nr1 + nr2) + "\n" + "\n";
            System.out.println("Subtraction:");
            result += (nr1 - nr2) + "\n" + "\n";
            System.out.println("Multiplication:");
            result += (nr1 * nr2) + "\n" + "\n";
            System.out.println("Division:");
            result += (nr1 / nr2) + "\n" + "\n";
            System.out.println("Percentage:");
            result += (nr1 % nr2) + "\n" + "\n";
        }

        return result;
    }
}
