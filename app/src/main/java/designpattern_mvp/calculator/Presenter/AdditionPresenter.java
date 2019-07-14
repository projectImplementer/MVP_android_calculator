package designpattern_mvp.calculator.Presenter;

import designpattern_mvp.calculator.Model.NumbersModel;
import designpattern_mvp.calculator.View.AdditionViewInterface;

public class AdditionPresenter extends NumbersModel implements AdditionViewInterface {

    AdditionViewInterface additionView;
    NumbersModel numbersModel;


    public NumbersModel NumbersModelPresenter(NumbersModel nrsModel) {
        return nrsModel;
    }


    public AdditionPresenter() {
        super(1, 2);
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
