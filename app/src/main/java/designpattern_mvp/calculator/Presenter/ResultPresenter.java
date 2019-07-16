package designpattern_mvp.calculator.Presenter;

import android.widget.Button;
import android.widget.EditText;

import designpattern_mvp.calculator.Model.NumbersModel;
import designpattern_mvp.calculator.View.AdditionViewInterface;

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
        String concatenatedText = "" + editTextToChange.getText().toString() + pressedButton.getText().toString();
        editTextToChange.setText(concatenatedText);
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
}
