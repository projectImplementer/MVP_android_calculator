package designpattern_mvp.calculator.Model;

public class NumbersModel implements NumbersModelInterface {
    private double nr1, nr2;

    public NumbersModel() {

    }


    @Override
    public void setNumber1(double number1) {
        nr1 = number1;
    }

    @Override
    public void setNumber2(double number2) {
        nr2 = number2;
    }

    @Override
    public double getNumber1() {
        return nr1;
    }

    @Override
    public double getNumber2() {
        return nr2;
    }
}
