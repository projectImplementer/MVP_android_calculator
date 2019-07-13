package designpattern_mvp.calculator.Presenter;

import designpattern_mvp.calculator.Model.Calculator;
import designpattern_mvp.calculator.View.AdditionViewInterface;

public class AdditionPresenter implements AdditionPresenterInterface {

    AdditionViewInterface additionView;

    public AdditionPresenter(AdditionViewInterface additionView) {
        this.additionView = additionView;
    }

    @Override
    public void onAddition(double addition) {
        Calculator calculator = new Calculator(addition);
    }

}
