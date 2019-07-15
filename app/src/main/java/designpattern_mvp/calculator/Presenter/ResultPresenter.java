package designpattern_mvp.calculator.Presenter;

import android.view.View;

import designpattern_mvp.calculator.Model.NumbersModel;
import designpattern_mvp.calculator.View.AdditionViewInterface;

public class ResultPresenter implements AdditionViewInterface {

    private AdditionViewInterface additionViewInterface;
    private NumbersModel numbersModel;


    public ResultPresenter(AdditionViewInterface additionViewInterface) {
        this.numbersModel = new NumbersModel();
        this.additionViewInterface = additionViewInterface;
    }

    @Override
    public double[] multipleResult(double number1, double number2) {
        VerifyVariables(number1, number2); //TODO: verify string numbers

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

    private boolean VerifyVariables(double number1, double number2) {
        return true;

    }
}
