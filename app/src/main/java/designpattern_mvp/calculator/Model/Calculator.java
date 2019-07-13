package designpattern_mvp.calculator.Model;

public class Calculator implements CalculatorInterface {

    private double addition, subtraction, multiplication, division;

    public Calculator(double addition, double subtraction, double multiplication, double division) {
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
        this.division = division;
    }

    @Override
    public double getAddition() {
        return addition;
    }

    @Override
    public double getSubtract() {
        return subtraction;
    }

    @Override
    public double getMultiplication() {
        return multiplication;
    }

    @Override
    public double getDivision() {
        return division;
    }
}
