package designpattern_mvp.calculator.Model;

public class Calculator implements CalculatorInterface {

    @Override
    public double getaddition() {
        return addition;
    }

    @Override
    public double getSubstraction() {
        return substraction;
    }

    @Override
    public double getMultiplicaiton() {
        return multiplication;
    }

    @Override
    public double getDivision() {
        return division;
    }

}
