package designpattern_mvp.calculator.Model;

public class NumbersModel implements NumbersModelInterface {
    private double nr1, nr2;

    public NumbersModel(
    double number1, double number2)

    {

        this.nr1 = number1;
        this.nr2 = number2;
    }


    @Override
    public double number1() {
        return 0;
    }

    @Override
    public double number2() {
        return 0;
    }

    @Override
    public double getNumber1(double number1) {
        return number1;
    }

    @Override
    public double getNumber2(double number2) {
        return number2;
    }
}
