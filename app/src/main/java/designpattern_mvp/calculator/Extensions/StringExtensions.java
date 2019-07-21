package designpattern_mvp.calculator.Extensions;

public final class StringExtensions {

    public static final String NEW_LINE = "\n";
    public static final String DOUBLE_LINE = "\n\n";

    public static final String ADDITION_START = "Addition: nr1 + nr2";
    public static final String SUBSTRACTION_START = "Subtraction: nr1 - nr2";
    public static final String MULTIPLICATION_START = "Multiplication: nr1 * nr2";
    public static final String DIVISION_START = "Division: nr1 / nr2";
    public static final String PERCENTAGE_START = "Percentage: nr1 % nr2";
    public static final String POWER_START = "Power: nr1 pow nr2";
    public static final String SQUAREROOT_START = "Nth Root of numbers: nr1 nr2";

    public static boolean isNullOrEmpty(String myString){
        return myString != null && myString.isEmpty();
    }
}
