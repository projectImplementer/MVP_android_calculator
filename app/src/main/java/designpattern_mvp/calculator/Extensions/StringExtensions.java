package designpattern_mvp.calculator.Extensions;

public final class StringExtensions {

    public static final String EMPTY_STRING = "";
    public static final String NEW_LINE = "\n";
    public static final String DOUBLE_LINE = "\n\n";


    public static boolean isNullOrEmpty(String myString){
        return myString != null && myString.isEmpty();
    }
}
