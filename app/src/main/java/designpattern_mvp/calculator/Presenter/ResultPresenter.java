package designpattern_mvp.calculator.Presenter;

import android.view.View;
import android.widget.EditText;

import designpattern_mvp.calculator.Model.NumbersModel;
import designpattern_mvp.calculator.View.AdditionViewInterface;

public class ResultPresenter {

    private AdditionViewInterface additionViewInterface;
    private NumbersModel numbersModel;


    public ResultPresenter(AdditionViewInterface additionViewInterface) {
        this.numbersModel = new NumbersModel();
        this.additionViewInterface = additionViewInterface;
    }

    public void setEmptyEditTexts(EditText... textValues) {
        for (EditText text:textValues) {
            text.setText("");

        }
    }
}
