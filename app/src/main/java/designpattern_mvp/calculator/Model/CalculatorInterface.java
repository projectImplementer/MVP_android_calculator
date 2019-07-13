package designpattern_mvp.calculator.Model;

public interface CalculatorInterface {

    double getAddition();

    double getSubtract();

    double getMultiplication();

    double getDivision();

    double addition = 0;
    double subtraction = 0;
    double multiplication = 0;
    double division = 0;
}
